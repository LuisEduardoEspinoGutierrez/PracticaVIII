# Imagen base ligera para Java 17
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el jar
COPY target/*.jar app.jar

# Puerto que usa tu app
EXPOSE 8080

# Comando para ejecutar tu API
ENTRYPOINT ["java", "-jar", "app.jar"]
