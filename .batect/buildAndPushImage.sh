#! /usr/bin/env sh

set -euo pipefail

TAG=$(git rev-parse HEAD)

echo $DOCKER_PASSWORD | docker login --username $DOCKER_USER --password-stdin
docker build -t charleskorn/batect-sample-java:latest .batect/international-transfers-service
docker tag charleskorn/batect-sample-java:latest charleskorn/batect-sample-java:$TAG
docker push charleskorn/batect-sample-java:latest
docker push charleskorn/batect-sample-java:$TAG
