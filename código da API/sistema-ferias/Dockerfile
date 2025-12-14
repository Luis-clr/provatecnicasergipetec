# Estágio 1: Build
# Usa Maven com Java 17 (versão estável Eclipse Temurin)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
# Compila o projeto
RUN mvn clean package -DskipTests

# Estágio 2: Run
# AQUI ESTAVA O ERRO: Trocamos 'openjdk' por 'eclipse-temurin'
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copia o .jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]