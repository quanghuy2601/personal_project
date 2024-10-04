FROM maven:3.9.9-eclipse-temurin-21-alpine as build
WORKDIR /app
COPY . .
RUN mvn clean package -Dmaven.test.failure.ignore=true

FROM eclipse-temurin:21-jdk
COPY --from=build /app/target/personal-project.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
