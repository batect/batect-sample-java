FROM alpine:3.18.4

RUN apk add --no-cache curl

# The explicit '--repository ...' is only necessary while Alpine does not publish a OpenJDK 17 package in the default repository.
RUN apk add --no-cache --repository https://dl-cdn.alpinelinux.org/alpine/edge/community/ openjdk17-jre-headless

RUN mkdir -p /tools
COPY health-check.sh /tools
HEALTHCHECK --interval=2s CMD /tools/health-check.sh

RUN mkdir -p /app
COPY international-transfers-service.jar /app
CMD ["java", "-jar", "/app/international-transfers-service.jar"]

EXPOSE 6001
