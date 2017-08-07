package com.arogut.shas.repository;

import com.arogut.shas.model.jpa.Device;
import com.arogut.shas.model.DeviceBuilder;
import com.arogut.shas.model.jpa.SourceDevice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class DeviceRepositoryIT {

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    public void repositoryShouldContainAddedDevice() {
        String host = "hohohost";
        Instant lastConnection = Instant.now();
        SourceDevice sourceDevice = new DeviceBuilder<>(new SourceDevice())
                .withId("test")
                .withHost(host)
                .withLastConnection(lastConnection)
                .withIsConnected(true)
                .build();

        deviceRepository.save(sourceDevice);

        Optional<Device> optDevice = deviceRepository.findOneById("test");
        assertThat(optDevice).hasValueSatisfying(device -> {
            assertThat(device.getId()).isEqualTo("test");
            assertThat(device.getHost()).isEqualTo(host);
            assertThat(device.getLastConnection()).isEqualTo(lastConnection);
            assertThat(device.isConnected()).isTrue();
        });
    }
}
