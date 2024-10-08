FROM gradle:8.10.1-jdk17 AS builder
WORKDIR /work
COPY . /work
RUN ./gradlew bootJar

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /work/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]