FROM openjdk:16-ea-14-alpine

WORKDIR /

COPY /target/quarkus-app/quarkus-run.jar /home/application.jar

EXPOSE 9090

CMD ["java", "-jar", "/home/application.jar"]