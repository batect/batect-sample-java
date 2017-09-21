FROM alpine:3.5

RUN apk add --no-cache openjdk8-jre
RUN apk add --no-cache curl

RUN mkdir -p /tools
COPY health-check.sh /tools

RUN mkdir -p /app /tmp/unzip
COPY app.zip /tmp/app.zip
RUN unzip /tmp/app.zip -d /tmp/unzip && cp -rf /tmp/unzip/*/* /app

CMD ["/app/bin/international-transfers-service"]
HEALTHCHECK --interval=2s CMD /tools/health-check.sh

EXPOSE 6001
