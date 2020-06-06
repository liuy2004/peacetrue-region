#!/bin/bash

echo "1. gradle bootJar"
gradle bootJar

cd peacetrue-peacetrue-region-docker

echo "2. docker-compose down"
docker-compose down

echo "3. docker-compose up --build -d"
docker-compose up --build -d
