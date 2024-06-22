FROM azul/zulu-openjdk:17

# Define argumentos de construção
ARG AWS_ACCESS_KEY_ID
ARG AWS_SECRET_KEY
ARG AWS_REGION

# Defina variáveis de ambiente para uso durante a construção da imagem
ENV AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID}
ENV AWS_SECRET_KEY=${AWS_SECRET_KEY}
ENV AWS_REGION=${AWS_REGION}

WORKDIR /app

# Copia o arquivo JAR da aplicação
COPY target/gamehouse-sendEmail-service-1.0.0.jar /app/send-email-ms.jar

# Copia o arquivo de configuração
COPY src/main/resources/application.properties /app/application.properties

# Substitua as variáveis no arquivo application.properties
RUN sed -i "s|aws.accessKeyId=.*|aws.accessKeyId=${AWS_ACCESS_KEY_ID}|g" /app/application.properties && \
    sed -i "s|aws.secretKey=.*|aws.secretKey=${AWS_SECRET_KEY}|g" /app/application.properties && \
    sed -i "s|aws.region=.*|aws.region=${AWS_REGION}|g" /app/application.properties

EXPOSE 8083

CMD ["java", "-jar", "/app/send-email-ms.jar"]