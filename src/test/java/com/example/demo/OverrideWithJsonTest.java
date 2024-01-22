package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OverrideWithJsonTest {

    @Autowired
    private Config config;

    @Autowired
    private Environment env;

    @Test
    void testEndpoint() {
        //set this and env var when running SPRING_APPLICATION_JSON='{"otel":{"processors":[{"batch":{"exporter":{"otlp":{"endpoint":"http://endpoint3","protocol":"grpc"}}}}]}}'

        Otel otel = config.getOtel();
        assertThat(otel.getProcessors()).hasSize(2);
        assertThat(env.getProperty("otel.processors[0].batch.exporter.otlp.endpoint")).isEqualTo("http://endpoint3");
        assertThat(otel.getProcessors().get(0).getBatch().getExporter().getOtlp().getEndpoint()).isEqualTo("http://endpoint3");
        assertThat(otel.getProcessors().get(0).getBatch().getExporter().getOtlp().getProtocol()).isEqualTo("grpc"); // the other property got dropped
        assertThat(otel.getProcessors().get(1).getBatch().getExporter().getOtlp().getEndpoint()).isEqualTo("http://endpoint4");
        assertThat(otel.getProcessors().get(1).getBatch().getExporter().getOtlp().getProtocol()).isEqualTo("grpc"); // the other property got dropped
    }
}
