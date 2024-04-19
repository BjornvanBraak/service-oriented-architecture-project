package com.example.balanceService.config;
import io.opentelemetry.context.propagation.TextMapPropagator;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.extension.trace.propagation.JaegerPropagator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Amith Kumar
 */
@Configuration
public class OtelConfiguration {

//    @Bean
//    public OtlpHttpSpanExporter otlpHttpSpanExporter(@Value("${management.oltp.tracing.export.http_endpoint}") String endpoint){
//        return OtlpHttpSpanExporter.builder().setEndpoint(endpoint).build();
//    }

    @Bean
    public OtlpGrpcSpanExporter otlpGrpcSpanExporter(@Value("${management.oltp.tracing.export.gRPC_url}") String url) {
        return OtlpGrpcSpanExporter.builder().setEndpoint(url).build();
    }
}