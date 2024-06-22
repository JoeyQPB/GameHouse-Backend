FROM azul/zulu-openjdk:17

WORKDIR /app

COPY target/gamehouse-sugestion-service-1.0.0.jar /app/suggestion-service-ms.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/bookApi.jar"]