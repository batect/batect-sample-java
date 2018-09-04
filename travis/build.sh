#!/usr/bin/env bash

set -euo pipefail

echo "Building..."
./batect build
echo

echo "Running unit tests..."
./batect unitTest
echo

echo "Running integration tests..."
./batect integrationTest
echo

echo "Running journey tests..."
./batect journeyTest
echo

if [ -z "${DOCKER_USER:+x}" ] || [ -z "${DOCKER_PASSWORD:+x}"  ]; then
    echo "DOCKER_USER or DOCKER_PASSWORD not set, not pushing image to Docker Hub."
else
    echo "Pushing image..."
    ./batect pushImage
fi

echo
