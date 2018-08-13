#!/bin/sh

export AGENT_FILE_PATH=/usr/local/undertow-scenario/agent

if [ -f "${AGENT_FILE_PATH}/skywalking-agent.jar" ]; then
    RESTTEMPLATE_CASE_OPTS="-javaagent:${AGENT_FILE_PATH}/skywalking-agent.jar -Dskywalking.collector.grpc_channel_check_interval=2 \
    -Dskywalking.collector.app_and_service_register_check_interval=2 \
    -Dskywalking.collector.discovery_check_interval=2 -Dskywalking.collector.servers=${COLLECTOR_SERVER} \
    -Dskywalking.agent.application_code=undertow-scenario"
fi

java ${RESTTEMPLATE_CASE_OPTS} -jar /usr/local/undertow-scenario/undertow-scenario.jar
