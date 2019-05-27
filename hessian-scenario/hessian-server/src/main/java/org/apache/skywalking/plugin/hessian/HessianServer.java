package org.apache.skywalking.plugin.hessian;

import org.apache.skywalking.plugin.hessian.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;

/**
 * Hessian Server
 *
 * @author Alan Lau
 */
@SpringBootApplication
public class HessianServer
{

    @Autowired
    private HelloService helloService;

    public static void main( String[] args )
    {
        SpringApplication.run(HessianServer.class, args);
    }

    @Bean(name="/hessian-case/HelloService")
    public HessianServiceExporter getExporter() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setServiceInterface(HelloService.class);
        exporter.setService(helloService);
        return exporter;
    }

}

