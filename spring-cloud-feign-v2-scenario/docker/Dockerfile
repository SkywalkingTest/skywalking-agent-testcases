FROM openjdk:8-jdk

ENV COLLECTOR_SERVER=127.0.0.1:12800 \
    PROJECTB_URL=127.0.0.1:18080 \
    PROJECTC_URL=127.0.0.1:28080

ADD startup.sh /usr/local/spring-cloud-feign-v2-scenario/
ADD spring-cloud-feign-v2-scenario.jar /usr/local/spring-cloud-feign-v2-scenario/

ADD docker-entrypoint.sh /
RUN chmod +x /usr/local/spring-cloud-feign-v2-scenario/startup.sh && chmod +x /docker-entrypoint.sh

EXPOSE 5005
EXPOSE 8080

VOLUME /usr/local/spring-cloud-feign-v2-scenario/agent
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["/usr/local/spring-cloud-feign-v2-scenario/startup.sh"]
