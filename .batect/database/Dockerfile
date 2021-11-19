FROM postgres:14.1

RUN mkdir -p /tools
COPY health-check.sh /tools/

HEALTHCHECK --interval=1s --retries=10 CMD /tools/health-check.sh

COPY create-structure.sql /docker-entrypoint-initdb.d/
