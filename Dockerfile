FROM openjdk:17
EXPOSE 8080
ADD target/e-bank-app.jar e-bank-app.jar
ENTRYPOINT ["java","-jar","/e-bank-app.jar"]