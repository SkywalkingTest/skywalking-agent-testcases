package test.apache.skywalking.apm.testcase.resteasy;

import org.jboss.resteasy.plugins.server.netty.NettyContainer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import test.apache.skywalking.apm.testcase.resteasy.controller.ServerController;

import java.util.Collections;

/**
 * @author yan-fucheng
 */
public class Application {

    public static void main(String[] args) throws Exception {
        ResteasyDeployment deployment = new ResteasyDeployment();
        deployment.setResources(Collections.<Object>singletonList(new ServerController()));
        NettyContainer.start("/resteasy-server-case", null, deployment);
    }
}
