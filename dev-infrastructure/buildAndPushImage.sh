#! /usr/bin/env sh

set -euo pipefail

echo $DOCKER_PASSWORD | docker login --username $DOCKER_USER --password-stdin
docker build -t charleskorn/batect-sample dev-infrastructure/international-transfers-service
docker push charleskorn/batect-sample
