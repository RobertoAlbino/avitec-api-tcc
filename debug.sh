#!/usr/bin/env bash
gradle build -Dorg.gradle.java.home=/usr/lib/jvm/java-8-oracle && gradle bootRun --debug-jvm -Dorg.gradle.java.home=/usr/lib/jvm/java-8-oracle
