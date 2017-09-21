#!/usr/bin/env sh

set -e

HOST=${HOST:-localhost}
PORT=${PORT:-6001}

RESPONSE=$(curl "http://$HOST:$PORT/ping" --fail --show-error --silent || exit 1)

if [ "$RESPONSE" == "pong" ]; then
    exit 0
else
    echo "Unexpected response from service: $RESPONSE"
    exit 1
fi
