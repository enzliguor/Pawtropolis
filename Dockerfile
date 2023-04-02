FROM postgres:alpine

LABEL author="Vincenzo Liguori"
LABEL description="Postgres Image for Pawtropolis"
LABEL version="1.0"

ENV POSTGRES_USER root
ENV POSTGRES_PASSWORD admin
ENV POSTGRES_DB pawtropolis

EXPOSE 5432

COPY app/src/main/resources/sql/*.sql /docker-entrypoint-initdb.d/