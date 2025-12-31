# Spring Boot 4.0.1 + Java 21 + Gradle 멀티스테이지 빌드
# 1단계에서 빌드(Gradle), 2단계에서 런타임 이미지 생성으로 최적화
FROM gradle:8.10-jdk21-alpine AS builder
WORKDIR /app
COPY . .
RUN gradle clean build -x test --no-daemon

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
