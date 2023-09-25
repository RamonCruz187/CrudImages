FROM amazoncorretto:20-alpine-jdk
MAINTAINER RamonCruz
COPY target/repaso-0.0.1-SNAPSHOT.jar crud.jar 
ENTRYPOINT ["java","-jar","/crud.jar"]
EXPOSE 8092 
