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

echo "Pushing image..."
./batect pushImage
echo
