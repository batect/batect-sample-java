FROM alpine:3.12.0

RUN apk add --no-cache curl

# The explicit '--repository ...' is only necessary while Alpine does not publish a OpenJDK 14 package in the default repository.
RUN apk add --no-cache --repository https://dl-cdn.alpinelinux.org/alpine/edge/testing/ openjdk14-jre-headless

RUN mkdir -p /tools
COPY health-check.sh /tools

RUN mkdir -p /app /tmp/unzip
COPY international-transfers-service.zip /tmp/app.zip
RUN unzip /tmp/app.zip -d /tmp/unzip && cp -rf /tmp/unzip/*/* /app

CMD ["/app/bin/international-transfers-service"]
HEALTHCHECK --interval=2s CMD /tools/health-check.sh

EXPOSE 6001
