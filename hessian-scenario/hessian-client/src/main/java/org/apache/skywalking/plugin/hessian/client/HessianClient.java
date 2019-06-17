package org.apache.skywalking.plugin.hessian.client;

import org.apache.skywalking.plugin.hessian.client.service.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class HessianClient
{

    public static final String HESSIAN_PROVIDER_HOST = "hessian.provider.host";

    public static final String HESSIAN_PROVIDER_PORT = "hessian.provider.port";

    public static void main( String[] args )
    {
        SpringApplication.run(HessianClient.class, args);
    }

    @Bean
    public HessianProxyFactoryBean helloService() {
        HessianProxyFactoryBean bean = new HessianProxyFactoryBean();
        String host = System.getProperty(HESSIAN_PROVIDER_HOST);
        String port = System.getProperty(HESSIAN_PROVIDER_PORT);
        String url = new StringBuilder("http://").append(host).append(":").append(port).append("/HelloService").toString();
//        String url = "http://localhost:8080/hessian-case/HelloService";
        bean.setServiceUrl(url);
        bean.setServiceInterface(HelloService.class);
        return bean;
    }

}
