#!/bin/sh

export AGENT_FILE_PATH=/usr/local/spring-cloud-feign-v2-scenario/agent
if [ -f "${AGENT_FILE_PATH}/skywalking-agent.jar" ]; then
    RESTTEMPLATE_CASE_OPTS="$CATALINA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -javaagent:${AGENT_FILE_PATH}/skywalking-agent.jar -Dskywalking.collector.grpc_channel_check_interval=2 -Dskywalking.collector.app_and_service_register_check_interval=2 -Dskywalking.collector.discovery_check_interval=2 -Dskywalking.collector.backend_service=${COLLECTOR_SERVER} -Dskywalking.agent.service_name=spring-cloud-feign-v2-scenario"
fi

java ${RESTTEMPLATE_CASE_OPTS} -jar /usr/local/spring-cloud-feign-v2-scenario/spring-cloud-feign-v2-scenario.jar
