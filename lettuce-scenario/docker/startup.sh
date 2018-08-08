#!/bin/sh

export AGENT_FILE_PATH=/usr/local/lettuce-scenario/agent
if [ -f "${AGENT_FILE_PATH}/skywalking-agent.jar" ]; then
    LETTUCE_SCENARIO_OPTS=" -javaagent:${AGENT_FILE_PATH}/skywalking-agent.jar -Dskywalking.collector.grpc_channel_check_interval=2 -Dskywalking.collector.app_and_service_register_check_interval=2 -Dcollector.discovery_check_interval=2 -Dskywalking.collector.servers=${COLLECTOR_SERVER} -Dskywalking.agent.application_code=lettuce-scenario -Xms256m -Xmx256m -XX:PermSize=64M -XX:MaxPermSize=64"
fi

java $LETTUCE_SCENARIO_OPTS -Dredis.host=${REDIS_HOST}  -jar /usr/local/skywalking-lettuce-scenario/lettuce-scenario.jar
