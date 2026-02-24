FROM maven:4.0.0-eclipse-temurin-21 AS build

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

# Definir variable de entorno
ENV JWT_SECRET=c2VjdXJlLWtlLWZvci1qd3QtYXV0aC10b2tlbi1zaWduaW5nLWluLWZsYXNoLWFwcC1leGFtcGxlLXNlcnZlcg==
ENV JWT_ALGORITHM=HS256

ENV DRIVER_CLASS_NAME=org.postgresql.Driver
ENV DATASOURCE_URL=jdbc:postgresql://192.168.1.47:5432/db_store
ENV DATASOURCE_USERNAME=dev_user
ENV DATASOURCE_PASSWORD=dev_123
ENV JPA_HIBERNATE_DDL_AUTO=none

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
