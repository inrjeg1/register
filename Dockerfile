FROM openjdk:8-jre-alpine

MAINTAINER Rajasekaran Jeganathan <rajasekaran.jeganathan@gmail.com>

RUN apk update 
RUN apk add openjdk8

VOLUME /tmp
ENV               VOLUME /register_service
RUN        mkdir $VOLUME
ADD        build/libs/*.jar  $VOLUME/register_service.jar
VOLUME           $VOLUME
WORKDIR	         $VOLUME
EXPOSE     3002

CMD ["java","-Djava.security.egd=file:/dev/urandom","-jar","register_service.jar"]
