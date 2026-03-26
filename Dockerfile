FROM eclipse-temurin:21-jdk-jammy

COPY target/I-RECON-0.0.1-SNAPSHOT.war app.war

ENTRYPOINT ["java","-jar","/app.war"]