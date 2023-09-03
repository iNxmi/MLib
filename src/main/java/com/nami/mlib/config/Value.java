package com.nami.mlib.config;

public record Value(String key, Object value) {

    public static Value of(String key, Object value) {
        return new Value(key, value);
    }

}
