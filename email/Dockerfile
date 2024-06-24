FROM azul/zulu-openjdk:17

# Define argumentos de construção
ARG SPRING_MAIL_USERNAME
ARG SPRING_MAIL_PASSWORD

# Defina variáveis de ambiente para uso durante a construção da imagem
ENV SPRING_MAIL_USERNAME=${SPRING_MAIL_USERNAME}
ENV SPRING_MAIL_PASSWORD=${SPRING_MAIL_PASSWORD}

WORKDIR /app

COPY target/email-1.0.0.jar /app/email-welcome-ms.jar

# Copia o arquivo de configuração
COPY src/main/resources/application.properties /app/application.properties

# Substitua as variáveis no arquivo application.properties
RUN sed -i "s|spring.mail.username=.*|spring.mail.username=${SPRING_MAIL_USERNAME}|g" /app/application.properties && \
    sed -i "s|spring.mail.password=.*|spring.mail.password=${SPRING_MAIL_PASSWORD}|g" /app/application.properties
    
EXPOSE 8084

CMD ["java", "-jar", "/app/email-welcome-ms.jar"]