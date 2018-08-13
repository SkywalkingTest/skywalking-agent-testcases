FROM openjdk:8-jdk

ENV COLLECTOR_SERVER=127.0.0.1:12800 \
    PROJECTB_URL=127.0.0.1:18080

ADD startup.sh /usr/local/undertow-scenario/
ADD undertow-scenario.jar /usr/local/undertow-scenario/

ADD docker-entrypoint.sh /
RUN chmod +x /usr/local/undertow-scenario/startup.sh && chmod +x /docker-entrypoint.sh

EXPOSE 8080

VOLUME /usr/local/undertow-scenario/agent
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["/usr/local/undertow-scenario/startup.sh"]
