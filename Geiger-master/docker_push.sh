#!/bin/bash
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker push USER/REPO
# andre docker kommandoer
#
#



docker build . --tag geiger --build-arg JAR_FILE=./target/Geiger-0.0.1-SNAPSHOT.jar

docker tag geiger devops2019exam/geiger
docker push devops2019exam/geiger
