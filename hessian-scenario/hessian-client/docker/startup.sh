#!/bin/sh

export AGENT_FILE_PATH=/usr/local/hessian-scenario/agent
if [ -f "${AGENT_FILE_PATH}/skywalking-agent.jar" ]; then
    HESSIAN_SCENARIO_OPTS=" -javaagent:${AGENT_FILE_PATH}/skywalking-agent.jar -Dskywalking.collector.grpc_channel_check_interval=2 -Dskywalking.collector.app_and_service_register_check_interval=2 -Dcollector.discovery_check_interval=2 -Dskywalking.collector.backend_service=${COLLECTOR_SERVER} -Dskywalking.agent.service_name=hessian-consumer -Dhessian.provider.host=${HESSIAN_PROVIDER_HOST} -Xms256m -Xmx256m -XX:PermSize=64M -XX:MaxPermSize=64"
fi

java $HESSIAN_SCENARIO_OPTS -jar /usr/local/hessian-scenario/hessian-client.jar