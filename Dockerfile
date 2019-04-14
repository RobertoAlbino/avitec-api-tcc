FROM gradle:alpine

EXPOSE 8080
COPY . /build/libs/
WORKDIR /build/libs/
ENTRYPOINT ["gradle avitec-0.0.1-SNAPSHOT.war", "/build/libs/avitec-0.0.1-SNAPSHOT.war",\
            "--server.port=8080",\
            "--spring.config.name=application"]