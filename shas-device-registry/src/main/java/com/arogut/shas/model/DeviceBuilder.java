package com.arogut.shas.model;

public class DeviceBuilder<T extends Device> {

    private T device;

    public DeviceBuilder(T t) {
        device = t;
    }

    public DeviceBuilder<T> withId(String id) {
        device.setId(id);
        return this;
    }

    public DeviceBuilder<T> withName(String name) {
        device.setName(name);
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

    public DeviceBuilder<T> withPost(int port) {
        device.setPort(port);
        return this;
    }

    public T build() {
        return device;
    }
}
