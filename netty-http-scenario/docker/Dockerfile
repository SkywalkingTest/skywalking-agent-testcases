FROM openjdk:8-jdk-alpine

ENV COLLECTOR_SERVER=127.0.0.1:12800 \
    CANAL_HOST=127.0.0.1

ADD startup.sh /usr/local/skywalking-netty-http-scenario/
ADD netty-http-scenario.jar /usr/local/skywalking-netty-http-scenario/netty-http-scenario.jar

ADD docker-entrypoint.sh /
RUN chmod +x /usr/local/skywalking-netty-http-scenario/startup.sh && chmod +x /docker-entrypoint.sh

EXPOSE 8888

VOLUME /usr/local/skywalking-netty-http-scenario/agent
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["/usr/local/skywalking-netty-http-scenario/startup.sh"]
