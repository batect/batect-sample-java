FROM andyrbell/mountebank:2.1.0

RUN apk add --no-cache curl
RUN mkdir -p /tools
COPY health-check.sh /tools/
HEALTHCHECK --interval=2s CMD /tools/health-check.sh

ADD imposters /imposters

CMD ["mb", "--configfile", "/imposters/config.ejs"]
EXPOSE 6000
