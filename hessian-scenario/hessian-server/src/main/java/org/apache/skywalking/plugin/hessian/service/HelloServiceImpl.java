package org.apache.skywalking.plugin.hessian.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override public String hello(String message) {
        return "hello, " + message;
    }
}
