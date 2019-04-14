#!/usr/bin/env bash
gradle build -Pswagger -Dorg.gradle.java.home=/usr/lib/jvm/java-8-oracle && gradle -Pswagger -Dorg.gradle.java.home=/usr/lib/jvm/java-8-oracle
