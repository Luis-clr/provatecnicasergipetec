# Estágio 1: Build
# Usa uma imagem do Maven compatível com o Java 17 definido no seu pom.xml
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
# Compila o projeto (pula os testes para ser mais rápido)
RUN mvn clean package -DskipTests

# Estágio 2: Run
# Usa uma imagem leve do Java 17 para rodar a aplicação
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copia o .jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]