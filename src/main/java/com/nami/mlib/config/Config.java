package com.nami.mlib.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public record Config(File file, Map<String, Object> map) {

    private Config(File file, List<Value> defaults) throws IOException {
        this(file, new TreeMap<>());

        //Load default values
        defaults.forEach(l -> {
            String key = l.key();
            if (!map().containsKey(key))
                map().put(key, l.value());
        });

        //Replace default values
        Properties prop = new Properties();
        prop.load(new FileReader(file));

        List<Value> values = new ArrayList<>();
        prop.forEach((k, v) -> values.add(Value.of((String) k, v)));
        setValues(values);
    }

    public AbstractGetter get(String key) {
        return new AbstractGetter(map().get(key));
    }

    public void setValues(List<Value> values) throws IOException {
        for (Value v : values)
            map().replace(v.key(), v.value());
        write();
    }

    public void write() throws IOException {
        StringBuilder sb = new StringBuilder();
        map().forEach((key, value) -> sb.append(String.format("%s=%s\n", key, value)));

        Files.write(file().toPath(), sb.toString().trim().getBytes());
    }

    @Override
    public String toString() {
        return map().toString();
    }

    public static ConfigBuilder builder() {
        return new ConfigBuilder();
    }

    public static class ConfigBuilder {

        private File file;
        private List<Value> defaults;

        public ConfigBuilder() {
            this.file = new File("config.prop");
            this.defaults = new ArrayList<>();
        }

        public ConfigBuilder setFile(File file) {
            this.file = file;
            return this;
        }

        public ConfigBuilder setDefaults(Value... defaults) {
            this.defaults = Arrays.asList(defaults);
            return this;
        }

        public Config build() {
            try {
                return new Config(file, defaults);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
