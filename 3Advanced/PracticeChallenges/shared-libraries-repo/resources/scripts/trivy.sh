#! /bin/bash

echo "Image name: $1"
echo "Severity: $2"
echo "Exit code: $3"

trivy-bin/trivy image $1 --severity $2 --exit-code $3 --format json \
    --output trivy-$2-report.json
