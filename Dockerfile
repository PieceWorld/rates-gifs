FROM openjdk:11

EXPOSE 8080

RUN mkdir ./app

COPY ./out/artifacts/RatesAndGifs_jar/RatesAndGifs.jar ./app

CMD java -jar ./app/RatesAndGifs.jar