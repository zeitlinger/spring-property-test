package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NoOverrideTest {

    @Autowired
    private Config config;

    @Autowired
    private Environment env;

    @Test
    void testEndpoint() {
        Otel otel = config.getOtel();
        assertThat(otel.getProcessors()).hasSize(2);
        assertThat(env.getProperty("otel.processors[0].batch.exporter.otlp.endpoint")).isEqualTo("http://endpoint1");
        assertThat(env.getProperty("otel.processors[1].batch.exporter.otlp.endpoint")).isEqualTo("http://endpoint2");
        assertThat(otel.getProcessors().get(0).getBatch().getExporter().getOtlp().getEndpoint()).isEqualTo("http://endpoint1");
        assertThat(otel.getProcessors().get(1).getBatch().getExporter().getOtlp().getEndpoint()).isEqualTo("http://endpoint2");
        assertThat(otel.getProcessors().get(0).getBatch().getExporter().getOtlp().getProtocol()).isEqualTo("grpc");
        assertThat(otel.getProcessors().get(1).getBatch().getExporter().getOtlp().getProtocol()).isEqualTo("grpc");
    }
}
