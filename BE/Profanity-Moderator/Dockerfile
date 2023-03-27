FROM openjdk:8
WORKDIR /app
COPY profanity-0.0.1-SNAPSHOT.jar .
RUN ls
EXPOSE 4000
CMD ["java","-Dspring.conf.location=application.yml","-jar","profanity-0.0.1-SNAPSHOT.jar"]
