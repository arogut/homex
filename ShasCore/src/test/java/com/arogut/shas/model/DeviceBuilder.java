package com.arogut.shas.model;

import java.time.Instant;

public class DeviceBuilder<T extends Device> {

    private T device;

    public DeviceBuilder(T t) {
        device = t;
    }

    public DeviceBuilder<T> withId(String id) {
        device.setId(id);
        return this;
    }

    public DeviceBuilder<T> withIsConnected(boolean isConnected) {
        device.setConnected(isConnected);
        return this;
    }

    public DeviceBuilder<T> withHost(String host) {
        device.setHost(host);
        return this;
    }

    public DeviceBuilder<T> withLastConnection(Instant timestamp) {
        device.setLastConnection(timestamp);
        return this;
    }

    public T build() {
        return device;
    }
}
