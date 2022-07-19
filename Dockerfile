FROM openjdk:17

EXPOSE 8080

RUN mkdir ./app

COPY ./demo1.jar ./app

CMD java -jar ./app/demo1.jar