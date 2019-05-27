package org.apache.skywalking.plugin.hessian.client;

import org.apache.skywalking.plugin.hessian.client.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alan Lau
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService service;

    @RequestMapping("/hessian-case/hello")
    public String hello(String message) {
        return service.hello("hessina plugin");
    }

}
