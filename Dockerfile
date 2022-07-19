FROM openjdk:18
EXPOSE 8080
ADD target/expense-api.jar expense-api.jar
ENTRYPOINT ["java","-jar","/expense-api.jar"]