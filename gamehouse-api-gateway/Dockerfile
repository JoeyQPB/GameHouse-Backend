FROM azul/zulu-openjdk:17

WORKDIR /app

COPY target/gamehouse-api-gateway-1.0.0.jar /app/api-gateway-ms.jar

EXPOSE 8765

CMD ["java", "-jar", "/app/api-gateway-ms.jar"]