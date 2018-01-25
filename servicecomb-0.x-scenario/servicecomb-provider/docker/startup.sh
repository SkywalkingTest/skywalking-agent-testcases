#!/bin/sh

export AGENT_FILE_PATH=/usr/local/servicecomb-consumer/agent
if [ -f "${AGENT_FILE_PATH}/skywalking-agent.jar" ]; then
    SERVICECOMB_PROVIDER_OPTS=" -javaagent:${AGENT_FILE_PATH}/skywalking-agent.jar -Dskywalking.collector.grpc_channel_check_interval=2 -Dskywalking.collector.app_and_service_register_check_interval=2 -Dcollector.discovery_check_interval=2 -Dskywalking.collector.servers=${COLLECTOR_SERVER} -Dskywalking.agent.application_code=servicecomb-provider -Xms256m -Xmx256m -XX:PermSize=64M -XX:MaxPermSize=64"
fi

java $SERVICECOMB_PROVIDER_OPTS  -jar /usr/local/skywalking-servicecomb-scenario/servicecomb-provider.jar
