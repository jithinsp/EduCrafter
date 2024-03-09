FROM openjdk:17
ADD target/finance-service.jar finance-service.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","/finance-service.jar"]