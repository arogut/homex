package com.arogut.homex.edge.service;

import com.arogut.homex.edge.client.AuthorizedGatewayClient;
import com.arogut.homex.edge.config.properties.ContractProperties;
import com.arogut.homex.edge.config.properties.DeviceProperties;
import com.arogut.homex.edge.config.properties.EdgeProperties;
import com.arogut.homex.edge.model.DeviceMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class MeasurementPublisherServiceTest {

    @Mock
    private MeasurementCollectorService collectorService;

    @Mock
    private AuthorizedGatewayClient authorizedGatewayClient;

    private EdgeProperties edgeProperties = new EdgeProperties();

    private ContractProperties contractProperties = new ContractProperties();

    private MeasurementPublisherService publisherService;

    @BeforeEach
    void setUp() {
        publisherService = new MeasurementPublisherService(edgeProperties, contractProperties, collectorService, authorizedGatewayClient);
    }

    @Test
    void shouldProperlyEmitPeriodicEventsAndSendDeviceMessages() {
        String uuid = UUID.randomUUID().toString();
        edgeProperties.setPublishDelay(1000);
        edgeProperties.setPublishPeriod(5000);
        contractProperties.setDevice(DeviceProperties.builder().id(uuid).build());

        Mockito.when(authorizedGatewayClient.sendMessage(Mockito.any(DeviceMessage.class))).thenReturn(Mono.just("1"), Mono.just("2"));

        StepVerifier.withVirtualTime(() -> publisherService.measurementsFlow())
                .expectSubscription()
                .expectNoEvent(Duration.ofMillis(edgeProperties.getPublishDelay()))
                .consumeNextWith(measurements -> {
                    assertNext(1, uuid, measurements);
                })
                .expectNoEvent(Duration.ofMillis(edgeProperties.getPublishPeriod()))
                .consumeNextWith(measurements -> {
                    assertNext(2, uuid, "2");
                })
                .thenCancel()
                .verify();
    }

    private void assertNext(int order, String deviceId, String returnedValue) {
        ArgumentCaptor<DeviceMessage> deviceMessageCaptor = ArgumentCaptor.forClass(DeviceMessage.class);
        Mockito.verify(collectorService, Mockito.times(order)).getMeasurement();
        Mockito.verify(authorizedGatewayClient, Mockito.times(order)).sendMessage(deviceMessageCaptor.capture());
        DeviceMessage sentMessage = deviceMessageCaptor.getValue();
        Assertions.assertAll(
                () -> Assertions.assertEquals(deviceId, sentMessage.getDeviceId()),
                () -> Assertions.assertEquals(returnedValue, String.valueOf(order))
        );
    }
}
