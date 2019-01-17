#!/bin/sh

export AGENT_FILE_PATH=/usr/local/shardingsphere-scenario/agent
if [ -f "${AGENT_FILE_PATH}/skywalking-agent.jar" ]; then
    SHARDINGSPHERE_SCENARIO_OPTS=" -javaagent:${AGENT_FILE_PATH}/skywalking-agent.jar -Dskywalking.collector.grpc_channel_check_interval=2 -Dskywalking.collector.app_and_service_register_check_interval=2 -Dcollector.discovery_check_interval=2 -Dskywalking.collector.backend_service=${COLLECTOR_SERVER} -Dskywalking.agent.application_code=shardingsphere-scenario -Dmysql.host=${MYSQL_INSTANCE_HOST} -Xms256m -Xmx256m -XX:PermSize=64M -XX:MaxPermSize=64"
fi

java $SHARDINGSPHERE_SCENARIO_OPTS -jar /usr/local/skywalking-shardingsphere-scenario/shardingsphere-scenario.jar
