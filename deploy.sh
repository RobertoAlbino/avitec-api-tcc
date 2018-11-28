#!/usr/bin/env bash
git add --all
git commit -m "Deploying avitec"
git push avi master
gradle build -Dorg.gradle.java.home=/usr/lib/jvm/java-8-oracle
heroku deploy:jar --jar build/libs/avitec-0.0.1-SNAPSHOT.war --app avitec-api



