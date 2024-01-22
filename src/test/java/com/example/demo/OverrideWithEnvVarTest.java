package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {"otel.processors[0].batch.exporter.otlp.endpoint=http://endpoint3"})
class OverrideWithEnvVarTest {

    @Autowired
    private Config config;

    @Autowired
    private Environment env;

    @Test
    void testEndpoint() {
        //override replaces the entire list, even if just a single property is set

        Otel otel = config.getOtel();
        assertThat(otel.getProcessors()).hasSize(1); //the other endpoint got dropped
        assertThat(env.getProperty("otel.processors[0].batch.exporter.otlp.endpoint")).isEqualTo("http://endpoint3");
        assertThat(otel.getProcessors().get(0).getBatch().getExporter().getOtlp().getEndpoint()).isEqualTo("http://endpoint3");
        assertThat(otel.getProcessors().get(0).getBatch().getExporter().getOtlp().getProtocol()).isNull(); // the other property got dropped
    }
}
