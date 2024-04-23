{{ define "bettingAppChart.baseMicroservices.service.tpl" }}
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
{{- end }}