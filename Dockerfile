FROM openjdk:17
EXPOSE 8080
ADD target/E-bank-0.0.1-SNAPSHOT.jar e-bank-app.jar
ENTRYPOINT ["java","-jar","/e-bank-app.jar"]