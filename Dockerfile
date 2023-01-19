FROM docker.io/maven:3-eclipse-temurin-11 as BUILD

WORKDIR /usr/src/tasker
COPY pom.xml .
RUN mvn verify clean --fail-never

COPY src ./src
RUN mvn package -DskipTests=true

FROM docker.io/eclipse-temurin:11-jre-focal

LABEL maintainer "dsio"

COPY --from=BUILD /usr/src/tasker/target/tasker-0.0.1-SNAPSHOT.jar /tasker.jar

EXPOSE 8080

VOLUME /data

COPY main.sh /

ENTRYPOINT ["/bin/bash"]

CMD ["-c","/main.sh"]
