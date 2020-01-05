package com.arogut.homex.config;

import com.arogut.homex.rest.DeviceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> routes(DeviceHandler handler) {
        return route(GET("/device"), handler::getDevices)
                .andRoute(GET("/device/{id}"), handler::getDeviceById)
                .andRoute(POST("/device"), handler::addDevice);
    }
}
