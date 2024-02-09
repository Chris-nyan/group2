FROM openjdk:latest
COPY ./target/group2.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "group2.jar", "db:33060", "30000"]