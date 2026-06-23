# 1단계: 빌드 환경 (메이븐과 자바 11 세팅)
FROM maven:3.8.6-openjdk-11-slim AS build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

# 2단계: 실행 환경 (가벼운 JRE 환경)
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

# 스프링 부트 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]