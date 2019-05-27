FROM openjdk:alpine

ADD startup.sh /usr/local/hessian-scenario/
ADD hessian-client.jar /usr/local/hessian-scenario/


ADD docker-entrypoint.sh /

RUN chmod +x /usr/local/hessian-scenario/startup.sh && chmod +x /docker-entrypoint.sh

EXPOSE 4000

VOLUME /usr/local/hessian-scenario/agent
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["/usr/local/hessian-scenario/startup.sh"]