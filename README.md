# ItemCatalogue

## Kubernetes:
kubectl version\
kubectl --help\
kubectl get nodes\
kubectl create -f item-catalogue-deployment.yaml \ 
kubectl apply -f item-catalogue-deployment.yaml \
kubectl get services \
kubectl get deployments\
kubectl get pods\
kubectl logs image-catalog-deployment-6f59c5d96c-rjz46\
kubectl delete pod image-catalog-deployment-6f59c5d96c-rjz46\

#Consul
consul is commented in main and api pom (uncomment if you want to run)

#API
/v1/items -> list of all items
/demo/break -> sets configurations so that custom health check imulates failure
/health/live -> Custom health check and data source health check

#OPENAPI
http://localhost:8080/api-specs/ui

raw:\
http://20.120.67.65/notifications/api-specs/v1/openapi.json

ui(for item catalogue):\
http://20.120.66.133:3333/api-specs/ui/?url=http://http://20.120.66.133:3333//api-specs/v1/openapi.json

#Filter query
http://localhost:8080/v1/items?filter=tag:EQ:zimsko

#Ingres
Ingres IP: 20.81.77.21 \
Paths:
* /items
* /rent \
Commands:
* helm install ingress-nginx ingress-nginx/ingress-nginx --create-namespace --namespace climb => create namespace (must be inside helm folder)
* kubectl apply -f ingres.yaml --namespace climb => apply ingress configuration
* kubectl --namespace climb get services -o wide => list services in namespace