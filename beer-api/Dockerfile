FROM enterprisebeers.azurecr.io/openliberty-java11:javaee8-19.0.0.1

ARG WAR_FILE

COPY src/main/docker/app.xml /config/configDropins/defaults/app.xml
COPY target/${WAR_FILE} /tmp/app.war