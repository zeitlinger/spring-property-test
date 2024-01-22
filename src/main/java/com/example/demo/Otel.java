package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "otel")
public class Otel {

    public static class Otlp {
        private String endpoint;

        private String protocol;

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }
    }

    public static class Exporter {
        private Otlp otlp = new Otlp();

        public Otlp getOtlp() {
            return otlp;
        }

        public void setOtlp(Otlp otlp) {
            this.otlp = otlp;
        }
    }

    public static class Batch {
        private Exporter exporter = new Exporter();

        public Exporter getExporter() {
            return exporter;
        }

        public void setExporter(Exporter exporter) {
            this.exporter = exporter;
        }
    }

    public static class Processor {
        private Batch batch = new Batch();

        public Batch getBatch() {
            return batch;
        }

        public void setBatch(Batch batch) {
            this.batch = batch;
        }
    }

    public List<Processor> getProcessors() {
        return processors;
    }

    public void setProcessors(List<Processor> processors) {
        this.processors = processors;
    }

    private List<Processor> processors = new java.util.ArrayList<>();
}
