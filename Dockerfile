FROM openjdk:latest
COPY ./target/Group2-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "Group2-1.0-SNAPSHOT-jar-with-dependencies.jar"]