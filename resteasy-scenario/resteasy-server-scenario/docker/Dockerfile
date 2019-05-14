FROM openjdk:8-jdk-alpine

ENV COLLECTOR_SERVER=127.0.0.1:12800

ADD startup.sh /usr/local/resteasy-server-scenario/
ADD resteasy-server-scenario.jar /usr/local/resteasy-server-scenario/

ADD docker-entrypoint.sh /
RUN chmod +x /usr/local/resteasy-server-scenario/startup.sh && chmod +x /docker-entrypoint.sh

EXPOSE 8080

VOLUME /usr/local/resteasy-server-scenario/agent
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["/usr/local/resteasy-server-scenario/startup.sh"]
