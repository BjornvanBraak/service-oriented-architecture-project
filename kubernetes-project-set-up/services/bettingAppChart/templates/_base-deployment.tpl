{{ define "bettingAppChart.baseMicroservices.deployment.tpl" }}
apiVersion: v1
kind: Pod
metadata:
  name: {{ .serviceName }}
  labels:
    app: {{ .serviceName }}
    app.kubernetes.io/name: {{ .serviceName }}
spec:
  containers:
    - name: {{ .serviceName }}
      image: {{ .serviceImage }}
      imagePullPolicy: Always #for development
      env:
        - name: SPRING_APPLICATION_HOST
          value: localhost #not sure what this does
        - name: SPRING_APPLICATION_NAME
          value: {{ .serviceName }}
        - name: SERVER_PORT
          value: "8080"
        - name: SPRING_APPLICATION_LOGGINGHOST
          value: localhost #not sure what this does
        - name: MANAGEMENT_OLTP_TRACING_EXPORT_GRPC_URL
          value: http://otel-collector.monitoring.svc.cluster.local:4317
        - name: MANAGEMENT_OLTP_TRACING_EXPORT_HTTP_ENDPOINT
          value: http://otel-collector.monitoring.svc.cluster.local:4318/v1/traces
        - name: SERVER_SERVLET_CONTEXT-PATH
          value: {{ .contextPath }}
        - name: SPRING_ACTIVEMQ_BROKER-URL
          value: tcp://broker1.services.svc.cluster.local:61616?wireFormat.maxInactivityDurationInitalDelay=30000
        - name: EXTERNAL-SERVICES_DOMAINADRESSES_GAMESERVICE
          value: soa-project-game-service.services.svc.cluster.local:80/game-service
        - name: EXTERNAL-SERVICES_DOMAINADRESSES_BETTINGSERVICE
          value: soa-project-betting-service.services.svc.cluster.local:80/betting-service
        - name: EXTERNAL-SERVICES_DOMAINADRESSES_IAMSERVICE
          value: soa-project-iam-service.services.svc.cluster.local:80/iam-service
        - name: EXTERNAL-SERVICES_DOMAINADRESSES_BALANCESERVICE
          value: soa-project-balance-service.services.svc.cluster.local:80/balance-service
{{- end }}