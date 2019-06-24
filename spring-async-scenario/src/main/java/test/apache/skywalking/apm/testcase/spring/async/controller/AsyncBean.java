package test.apache.skywalking.apm.testcase.spring.async.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * @author zhaoyuguang
 */
@Component
public class AsyncBean {

    @Autowired
    private HttpBean httpBean;


    @Async
    public void sendVisit() throws IOException {
        httpBean.visit("http://skywalking.apache.org/?k=v");
    }

    @Async
    public Future<String> sendVisit(String k) throws IOException {
        httpBean.visit("http://skywalking.apache.org/?k=v");
        return new AsyncResult<>(k);
    }
}
