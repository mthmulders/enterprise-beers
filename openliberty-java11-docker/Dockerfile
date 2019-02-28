FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.2.9_openj9-0.12.1-alpine

COPY docker-server /opt/ol/docker/

RUN apk update \
    && apk add curl unzip \
    && curl -L -s -A UA-Open-Liberty-Docker -o /tmp/wlp.zip https://public.dhe.ibm.com/ibmdl/export/pub/software/openliberty/runtime/release/2019-01-24_2339/openliberty-javaee8-19.0.0.1.zip \
    && unzip -q /tmp/wlp.zip -d /opt/ol \
    && rm /tmp/wlp.zip \
    && apk del curl unzip \
    && rm -f /var/cache/apk/*

# Set Path Shortcuts
ENV PATH=/opt/ol/wlp/bin:/opt/ol/docker/:$PATH \
    LOG_DIR=/logs \
    WLP_OUTPUT_DIR=/opt/ol/wlp/output \
    WLP_SKIP_MAXPERMSIZE=true

RUN mkdir /logs \
    && mkdir -p $WLP_OUTPUT_DIR/defaultServer \
    && ln -s $WLP_OUTPUT_DIR/defaultServer /output \
    && ln -s /opt/ol/wlp/usr/servers/defaultServer /config \
    && ln -s /logs $WLP_OUTPUT_DIR/defaultServer/logs

# Configure Open Liberty
RUN /opt/ol/wlp/bin/server create \
    && rm /config/server.env \
    && rm -rf $WLP_OUTPUT_DIR/.classCache /output/workarea \
    && mkdir /config/configDropins \
    && mkdir /config/configDropins/defaults

# Add Open Liberty configuration
ADD server.xml /opt/ol/wlp/usr/servers/defaultServer/

# Run the server the first time so it is setup correctly
RUN /opt/ol/wlp/bin/server start \
    && /opt/ol/wlp/bin/server stop \
    && rm -rf /output/resources/security/ /logs/*

RUN chmod +x /opt/ol/docker/docker-server

EXPOSE 9080 9443

ENTRYPOINT ["/opt/ol/docker/docker-server"]
CMD ["/opt/ol/wlp/bin/server", "run", "defaultServer"]