FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Démarre ton application Spring Boot
CMD ["java", "-jar", "target/Projet_ASTA-0.0.1-SNAPSHOT.jar"]
