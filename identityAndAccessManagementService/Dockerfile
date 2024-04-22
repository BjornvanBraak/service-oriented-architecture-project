FROM amazoncorretto:21

ENV JAR_FILE=build/libs/*.jar
ENV PORT=8181

# CHANGE VALUES IN application.yml file
ENV SPRING_APPLICATION_NAME=containerizedTestApp
ENV SPRING_APPLICATION_HOST=host.docker.internal
ENV SERVER_PORT=${PORT}
ENV SERVER_SERVLET_CONTEXT-PATH=/test-service


#configure consul
#ENV SPRING_CLOUD_CONSUL_HOST=host.docker.internal
#configure logging
ENV SPRING_APPLICATION_LOGGINGHOST=host.docker.internal
ENV LOGGING_FILE_PATH=/var/log
LABEL logging=promtail
#configure the oltp endpoint

# DEPRECATED, NOT SURE WHERE IT WENT WRONG
#ENV OTEL_EXPORTER=jaeger
#ENV OTEL_EXPORTER_OTLP_ENDPOINT=http://host.docker.internal:4318
#ENV OTEL_METRICS_EXPORTER=none
#ENV OTEL_LOGGING_EXPORTER=none
#ENV MANAGEMENT_OLTP_METRICS_ENDPOINT=http://host.docker.internal:4318/v1/metrics
#ENV MANAGEMENT_OLTP_TRACING_ENDPOINT=http://host.docker.internal:4318/v1/traces
ENV MANAGEMENT_OLTP_TRACING_EXPORT_GRPC_URL=http://host.docker.internal:4317
ENV MANAGEMENT_OLTP_TRACING_EXPORT_HTTP_ENDPOINT=http://host.docker.internal:4318/v1/traces

ENV SPRING_ACTIVEMQ_BROKER-URL="tcp://host.docker.internal:61616?wireFormat.maxInactivityDurationInitalDelay=30000"

COPY ${JAR_FILE} app.jar

ENTRYPOINT [ "java", "-jar",  "/app.jar"]
EXPOSE ${PORT}

