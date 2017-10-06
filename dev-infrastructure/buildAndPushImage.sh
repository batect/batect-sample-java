#! /usr/bin/env sh

set -euo pipefail

TAG=$(git rev-parse HEAD)

echo $DOCKER_PASSWORD | docker login --username $DOCKER_USER --password-stdin
docker build -t charleskorn/batect-sample:latest dev-infrastructure/international-transfers-service
docker tag charleskorn/batect-sample:latest charleskorn/batect-sample:$TAG
docker push charleskorn/batect-sample:latest
docker push charleskorn/batect-sample:$TAG
