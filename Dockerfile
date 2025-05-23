FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean install
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*with*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]