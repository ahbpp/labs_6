#!/bin/bash

minikube delete
minikube start
kubectl config use-context minikube

eval $(minikube docker-env -u)

kubectl create -f postgres.yaml

# cd ../rbc
# docker build -t rbc:0.9.0 .

# cd ../weather
# docker build -t weather:0.4.0 .

# cd ../predict
# docker build -t predict:0.3.0 .

# cd ../kube

# kubectl create -f deployment-weather.yaml

# kubectl create -f deployment-rbc.yaml

# kubectl create -f deployment-predict.yaml

minikube service list