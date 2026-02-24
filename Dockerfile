FROM maven:3.9.12-eclipse-temurin-21 AS build

WORKDIR /vg-store

# Copiar archivos de configuración de Maven y descargar dependencias (cacheable)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y compilar el JAR
COPY src ./src
RUN mvn clean package -DskipTests


# Etapa 2: Imagen de ejecución (Runtime)
# Usamos JRE en lugar de JDK para reducir el tamaño y mejorar la seguridad
FROM eclipse-temurin:21-jre-jammy
WORKDIR /vg-store


# Crear un usuario sin privilegios por seguridad
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

# Copiar el archivo JAR generado en la etapa anterior
# Reemplaza 'nombre-de-tu-app' por el nombre real de tu artefacto
COPY --from=build /vg-store/target/*.jar vg-store.jar

# Exponer el puerto estándar de Spring Boot
EXPOSE 8080

# Optimización de flags de JVM para contenedores
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "vg-store.jar"]
