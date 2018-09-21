FROM openjdk:8-jdk

ENV COLLECTOR_SERVER=127.0.0.1:12800

WORKDIR /usr/local/rabbitmq-scenario



# copy required packages
ADD start_up.sh /usr/local/rabbitmq-scenario/
ADD rabbitmq-scenario.jar /usr/local/rabbitmq-scenario
ADD docker-entrypoint.sh /

RUN chmod +x /docker-entrypoint.sh

VOLUME /usr/local/rabbitmq-scenario/agent

# port
EXPOSE 8080

#
#ENTRYPOINT /docker-entrypoint.sh

CMD sh /usr/local/rabbitmq-scenario/start_up.sh
