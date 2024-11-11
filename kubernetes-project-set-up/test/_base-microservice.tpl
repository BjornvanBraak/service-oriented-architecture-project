
{{- /*
# This template is used as a basis for any service
*/}}
{{ define "bettingAppChart.baseMicroservices.tpl" }}
apiVersion: v1
kind: Pod
metadata:
  name: {{ .serviceName }}
  labels:
    app: {{ .serviceName }}
    app.kubernetes.io/name: {{ .serviceName }}
spec:
  containers:
    - name: test-service-container
      image: bjornvanbraak/soa-project-group-20-test-service:alpha
      imagePullPolicy: Always #for development
      env:
        - name: SPRING_APPLICATION_HOST
          value: localhost #not sure what this does
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
---
apiVersion: v1
kind: Service
metadata:
  name: soa-project-service
  labels:
    job: {{ .serviceName }}
    app: {{ .appLabel }}
spec:
  type: ClusterIP
  selector:
    app.kubernetes.io/name: {{ .serviceName }}
  ports:
    - name: {{ .webPortName }}
      protocol: TCP
      port: 80
      targetPort: 8080
---
#notifies prometheus to watch the target in this case a service
{{ include "bettingAppChart.prometheus.ServiceMonitor" (dict "serviceName" .serviceName "webPortName" .webPortName "contextPath" .contextPath "appLabel" .appLabel) | indent 0 }}
{{- end }}


