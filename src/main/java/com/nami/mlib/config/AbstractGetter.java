package com.nami.mlib.config;

public record AbstractGetter(Object object) {

    public Object asObject() {
        return object();
    }

    public String asString() {
        return asObject().toString();
    }

    public byte asByte() {
        return Byte.parseByte(asString());
    }

    public short asShort() {
        return Short.parseShort(asString());
    }

    public int asInt() {
        return Integer.parseInt(asString());
    }

    public long asLong() {
        return Long.parseLong(asString());
    }

    public float asFloat() {
        return Float.parseFloat(asString());
    }

    public double asDouble() {
        return Double.parseDouble(asString());
    }

    public boolean asBoolean() {
        return Boolean.parseBoolean(asString());
    }

}
