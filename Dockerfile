FROM maven:3.8.5-openjdk-11-slim AS build
WORKDIR /home
#COPY pom.xml /home/
COPY . /home/ 
RUN mvn verify --fail-never
RUN mvn test
RUN mvn package
#RUN mvn -B -DskipTests clean package
#RUN mvn -DartifactId=lib_utilidades -DgroupId=py.com.utilidades -Dversion=2 -Dpackaging=jar -Dfile="lib/lib_utilidades.jar" -DgeneratePom=false install:install-file & mvn -B -DskipTests clean package
#RUN cd /home/ && mvn package
FROM openjdk:11-slim AS base
USER root
FROM base AS final
WORKDIR /home
COPY --from=build /home/target/InventoryAppBackend-*.jar /opt/InventoryAppBackend.jar
#RUN mkdir -p /root/.AMR-Services/etc /root/.AMR-Services/logs/
#COPY usermanagement.properties /root/.AMR-Services/etc
RUN chmod 775 -R /home
EXPOSE 8080
ENTRYPOINT ["/bin/sh", "-c", "java -jar /opt/InventoryAppBackend.jar -Djava.net.preferIPv4Stack=true"]