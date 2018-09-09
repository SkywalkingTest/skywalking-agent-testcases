#!/bin/bash

RABBITMQ_SCENARIO_HOME=/usr/local/rabbitmq-scenario


export AGENT_FILE_PATH=${RABBITMQ_SCENARIO_HOME}/agent
if [ -f "${AGENT_FILE_PATH}/skywalking-agent.jar" ]; then
    RABBITMQ_SCENARIO_OPTS=" -javaagent:${AGENT_FILE_PATH}/skywalking-agent.jar -Dskywalking.collector.grpc_channel_check_interval=2 -Dskywalking.collector.app_and_service_register_check_interval=2 -Dcollector.discovery_check_interval=2 -Dskywalking.collector.servers=${COLLECTOR_SERVER} -Dskywalking.agent.application_code=rabbitmq-scenario "
fi

java ${RABBITMQ_SCENARIO_OPTS} -jar ${RABBITMQ_SCENARIO_HOME}/rabbitmq-scenario.jar
