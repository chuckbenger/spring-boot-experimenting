# Build stage
FROM maven:3.8.2-openjdk-17 as build

COPY src /home/app/src
COPY frontend /home/app/frontend
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Package stage
FROM openjdk:17-jdk

COPY --from=build /home/app/target/*.jar /usr/local/lib/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]