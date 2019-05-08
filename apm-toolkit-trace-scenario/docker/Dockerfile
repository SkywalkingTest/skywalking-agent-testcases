FROM openjdk:8-jdk

ENV COLLECTOR_SERVER=127.0.0.1:12800 \
    ACTIVEMQ_BOOTSTRAP_SERVERS=127.0.0.1:61616 \
    ACTIVEMQ_VERSION=5.15.8

WORKDIR /usr/local/apm-toolkit-trace-scenario

# copy required packages
ADD start_up.sh /usr/local/apm-toolkit-trace-scenario/
ADD apm-toolkit-trace-scenario.jar /usr/local/apm-toolkit-trace-scenario
ADD docker-entrypoint.sh /

RUN chmod +x /docker-entrypoint.sh

VOLUME /usr/local/apm-toolkit-trace-scenario/agent

# port
EXPOSE 8080

#
#ENTRYPOINT /docker-entrypoint.sh

CMD sh /usr/local/apm-toolkit-trace-scenario/start_up.sh
