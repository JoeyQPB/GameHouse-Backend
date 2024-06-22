FROM azul/zulu-openjdk:17

WORKDIR /app

COPY target/gamehouse-sugestion-service-1.0.0.jar /app/suggestion-ms.jar

EXPOSE 8082

CMD ["java", "-jar", "/app/suggestion-ms.jar"]