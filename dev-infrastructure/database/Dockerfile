FROM postgres:9.6.2

RUN mkdir -p /tools
COPY health-check.sh /tools/

HEALTHCHECK --interval=2s CMD /tools/health-check.sh

COPY create-structure.sql /docker-entrypoint-initdb.d/
