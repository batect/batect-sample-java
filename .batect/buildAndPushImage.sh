#! /usr/bin/env sh

set -euo pipefail

TAG=$(git rev-parse HEAD)

echo $DOCKER_PASSWORD | docker login --username $DOCKER_USER --password-stdin
docker build -t batect/batect-sample-java:latest .batect/international-transfers-service
docker tag batect/batect-sample-java:latest batect/batect-sample-java:$TAG
docker push batect/batect-sample-java:latest
docker push batect/batect-sample-java:$TAG
