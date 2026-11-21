# ---------- Etapa 1: compilación (Maven + JDK 21) ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Se copia primero solo el pom.xml para cachear las dependencias entre builds
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

COPY src ./src
# -DskipTests: LabsoftpApplicationTests (@SpringBootTest) necesita MySQL, que no existe durante el build
RUN mvn -B -q clean package -DskipTests

# ---------- Etapa 2: ejecución (solo JRE 21) ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

RUN useradd --system --no-create-home spring
USER spring

# El plugin de Spring Boot genera labsoftp-0.0.1-SNAPSHOT.jar (el .jar.original no coincide con este patrón)
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
