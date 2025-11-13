FROM maven:3.9.9-eclipse-temurin-23-alpine AS build
WORKDIR /build
COPY app/pom.xml pom.xml
RUN mvn dependency:go-offline
COPY app/src src/
RUN mvn clean package

FROM eclipse-temurin:23-jre-alpine
ARG JAR_NAME=hoteljulia-1.0.0
COPY --from=build /build/target/${JAR_NAME}.jar /final/app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/final/app.jar" ]