FROM maven:3.6.3-openjdk-15 AS build
COPY ./ /app
WORKDIR /app
RUN mvn --show-version --update-snapshots --batch-mode clean package

FROM adoptopenjdk:15-jre-hotspot
RUN mkdir /app
WORKDIR /app
COPY --from=build ./app/api/target/api-1.0.0-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "api-1.0.0-SNAPSHOT.jar"]