FROM openjdk:21-jdk-slim

WORKDIR /vg-store

COPY target/vg-store.jar vg-store.jar

# Definir variable de entorno
ENV JWT_SECRET=c2VjdXJlLWtlLWZvci1qd3QtYXV0aC10b2tlbi1zaWduaW5nLWluLWZsYXNoLWFwcC1leGFtcGxlLXNlcnZlcg==
ENV JWT_ALGORITHM=HS256

ENV DRIVER_CLASS_NAME=org.postgresql.Driver
ENV DATASOURCE_URL=jdbc:postgresql://192.168.1.47:5432/db_store
ENV DATASOURCE_USERNAME=dev_user
ENV DATASOURCE_PASSWORD=dev_123
ENV JPA_HIBERNATE_DDL_AUTO=none

ENTRYPOINT ["java","-jar","vg-store.jar"]
