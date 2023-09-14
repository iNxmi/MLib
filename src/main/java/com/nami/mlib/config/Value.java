package com.nami.mlib.config;

public class Value {

    private final String key;
    private final Object value;

    public Value(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String key() {
        return key;
    }

    public Object value() {
        return value;
    }

}
