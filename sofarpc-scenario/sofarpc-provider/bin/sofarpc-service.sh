#!/usr/bin/env bash

PRG="$0"
PRGDIR=`dirname "$PRG"`
[ -z "$SOFARPC_PROVIDER_HOME" ] && SOFARPC_PROVIDER_HOME=`cd "$PRGDIR/.." >/dev/null; pwd`

AGENT_FILE_PATH=$SOFARPC_PROVIDER_HOME/agent

if [ -f "$AGENT_FILE_PATH/skywalking-agent.jar" ];then
    SOFARPC_PROVIDER_OPTS=" -javaagent:$AGENT_FILE_PATH/skywalking-agent.jar -Dconfig=/usr/local/sofarpc-provider/agent-config"
fi

_RUNJAVA=${JAVA_HOME}/bin/java
[ -z "$JAVA_HOME" ] && _RUNJAVA=`java`

CLASSPATH="$SOFARPC_PROVIDER_HOME/config:$CLASSPATH"
for i in "$SOFARPC_PROVIDER_HOME"/libs/*.jar
do
    CLASSPATH="$i:$CLASSPATH"
done

JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -Dskywalking.collector.grpc_channel_check_interval=2 -Dskywalking.collector.app_and_service_register_check_interval=2 -Dskywalking.collector.discovery_check_interval=2 -Dskywalking.collector.backend_service=${COLLECTOR_SERVER} -Dcom.alipay.sofa.rpc.registry.address=zookeeper://${ZK_ADDRESS} -Dskywalking.agent.application_code=sofarpc-provider -Xms256m -Xmx256m -XX:PermSize=64M -XX:MaxPermSize=64"

$JAVA_HOME/bin/java $JAVA_OPTS -classpath "$CLASSPATH" $SOFARPC_PROVIDER_OPTS test.apache.skywalking.apm.testcase.sofarpc.provider.ServerApplication
