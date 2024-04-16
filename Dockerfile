FROM openjdk:21
COPY target/StudentsAPI-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
