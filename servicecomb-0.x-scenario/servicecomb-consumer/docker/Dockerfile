FROM openjdk:8u111-jdk


ADD startup.sh /usr/local/skywalking-servicecomb-scenario/
ADD servicecomb-consumer-scenario.jar /usr/local/skywalking-servicecomb-scenario/

ADD docker-entrypoint.sh /
RUN chmod +x /usr/local/skywalking-servicecomb-scenario/startup.sh && chmod +x /docker-entrypoint.sh

EXPOSE 8080

VOLUME /usr/local/skywalking-servicecomb-scenario/agent
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["/usr/local/skywalking-servicecomb-scenario/startup.sh"]
