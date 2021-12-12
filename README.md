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

#Filter query
http://localhost:8080/v1/items?filter=tag:EQ:zimsko