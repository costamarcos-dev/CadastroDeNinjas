FROM eclipse-temurin:17
LABEL maintainer="marcos.costa010@gmail.com"
WORKDIR /app
COPY target/CadastroDeNinjas-0.0.1-SNAPSHOT.jar /app/cadastroninja.jar
ENTRYPOINT ["java","-jar", "cadastroninja.jar"]
