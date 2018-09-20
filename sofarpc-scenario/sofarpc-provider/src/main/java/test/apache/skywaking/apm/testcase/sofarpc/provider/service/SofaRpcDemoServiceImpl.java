package test.apache.skywaking.apm.testcase.sofarpc.provider.service;

import test.apache.skywaking.apm.testcase.sofarpc.interfaces.SofaRpcDemoService;

public class SofaRpcDemoServiceImpl implements SofaRpcDemoService {
    @Override
    public String hello(String name) {
        return "hello, " + name;
    }
}
