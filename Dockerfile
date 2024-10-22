# Verwende ein offizielles OpenJDK-Image als Basis
FROM openjdk:17-jdk-slim

# Arbeitsverzeichnis innerhalb des Containers
WORKDIR /app

# Abhängigkeiten für die Gradle-Kompilierung zwischenspeichern
COPY build.gradle /app/
COPY gradlew /app/
COPY gradle /app/gradle

# Baue die Anwendung
RUN ./gradlew build --no-daemon

# Kopiere das erstellte JAR-File in das Arbeitsverzeichnis des Containers
COPY build/libs/*.jar app.jar

# Standardport
EXPOSE 8080

# Starte die Anwendung
ENTRYPOINT ["java", "-jar", "app.jar"]
