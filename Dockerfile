FROM openjdk:8
ADD target/jstackticketing.jar jstackticketing.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "jstackticketing.jar"]