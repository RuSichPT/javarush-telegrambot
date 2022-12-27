#!/bin/bash

# Pull new changes
git pull

# Prepare Jar
mvn clean
mvn package

# Ensure, that docker-compose stopped
docker-compose stop

# Add environment variables
export BOT_NAME=test_javarush_rusichpt_bot
export BOT_TOKEN=5152870460:AAFtVQTauWsRoznlEqCkhsHtKaHJXMobjss

# Start new deployment
docker-compose up --build -d