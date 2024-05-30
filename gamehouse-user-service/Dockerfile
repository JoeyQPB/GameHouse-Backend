FROM azul/zulu-openjdk:17

WORKDIR /app

COPY target/gamehouse-user-service-1.0.0.jar /app/user-ms.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/user-ms.jar"]