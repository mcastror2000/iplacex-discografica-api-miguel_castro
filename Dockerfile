# STAGE 1 - Build stage
FROM gradle:jdk21 as builder
WORKDIR /app
COPY ./build.gradle .
COPY ./settings.gradle .
COPY ./src ./src
RUN gradle build --no-daemon

# STAGE 2 - Run stage
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar discografia.jar
EXPOSE 443
CMD ["java", "-jar", "discografia.jar"]
