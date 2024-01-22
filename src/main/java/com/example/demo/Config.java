package com.example.demo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(Otel.class)
public class Config {
    private final Otel otel;

    public Config(Otel otel) {
        this.otel = otel;
    }

    public Otel getOtel() {
        return otel;
    }
}
