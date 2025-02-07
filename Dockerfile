FROM gradle:8.11.1-jdk17 as build

WORKDIR /myapp

COPY . /myapp

RUN chmod +x gradlew

RUN ./gradlew clean build --no-daemon -x test

FROM openjdk:17-alpine

WORKDIR /myapp

COPY --from=build /myapp/build/libs/*.jar /myapp/getRand_userservice.jar
EXPOSE 5002
ENTRYPOINT ["java","-jar","/basic_test.jar"]