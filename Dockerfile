FROM alpine:latest
RUN apk --update add openjdk8-jre && rm /var/cache/apk/*

ENV MS_FILE helloEureka.jar
ENV MS_HOME /usr/ms

EXPOSE 8761

COPY target/$MS_FILE $MS_HOME/

WORKDIR $MS_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java -Xmx512m -jar $MS_FILE"]
