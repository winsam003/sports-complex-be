# 1단계: 빌드 환경 (메이븐 공식 이미지 사용)
FROM maven:3.8.6-openjdk-11-slim AS build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

# 2단계: 실행 환경 (도커 공식 인증을 받는 테무린 JDK 11 가벼운 버전으로 변경)
FROM eclipse-temurin:11-jre-focal
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

# 스프링 부트 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]