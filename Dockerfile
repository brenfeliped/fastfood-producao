# Etapa de build
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
WORKDIR /app

# 1) Copia só o pom primeiro para aproveitar cache das dependências
COPY pom.xml .

# Baixa dependências em cache
RUN mvn -B dependency:go-offline

# 2) Agora copia o código fonte
COPY src ./src

# Build do projeto (sem testes por enquanto)
RUN mvn -B clean package -DskipTests


# Etapa de runtime
FROM eclipse-temurin:17-jdk-alpine AS runner
WORKDIR /app

# Copia o jar gerado da etapa de build
COPY --from=builder /app/target/*.jar app.jar

# Porta HTTP e porta do debug
EXPOSE 8082 5005

# ENTRYPOINT com debug remoto
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]
