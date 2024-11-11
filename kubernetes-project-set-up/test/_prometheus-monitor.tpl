# Everything necessary to have a service work with prometheus
{{ define "bettingAppChart.prometheus.ServiceMonitor" }}
#defines a service monitoring to make Prometheus aware of the service
apiVersion: monitoring.coreos.com/v1
kind: ServiceMonitor
metadata:
  name: {{ .serviceName }}-service-monitor
  labels:
    release: prometheus-stack #SEE PROMETHEUS-CRD.YAML FOR UNDER SCRAPECONFIGSELECTOR
    app: prometheus
spec:
  jobLabel: job #will make this available in prometheus with label 'job'
  endpoints:
    - interval: 30s
      port: {{ .webPortName }}
      path: {{ .contextPath }}/actuator/prometheus
  selector:
    matchLabels:
      app: {{ .appLabel }}
{{- end }}
