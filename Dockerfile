# Stage 1: Build ứng dụng
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Chạy ứng dụng
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/social-backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-Xms512m", "-Xmx1024m", "-jar", "app.jar"]
