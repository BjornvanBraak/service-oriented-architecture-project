<!-- helm repo add jaegertracing https://jaegertracing.github.io/helm-charts
helm repo update jaegertracing
helm repo -->

For the whole tutorial
https://www.jaegertracing.io/docs/1.56/operator/

// src https://faun.pub/how-to-deploy-jaeger-on-kubernetes-69cf48447182

kubectl apply -f https://github.com/cert-manager/cert-manager/releases/download/v1.9.0/cert-manager.yaml

helm install jaeger jaegertracing/jaeger -n monitoring --values ./jaeger/jaeger-values.yaml

kubectl create namespace services

https://nitin-rohidas.medium.com/introduction-to-opentelemetry-distributed-tracing-part-iii-9b1bce59c1bd
