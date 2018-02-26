package org.apache.skywalking.testcase.hystrix.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixInvokable;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/case")
public class CaseController {

    @PostConstruct
    public void setUp() {
        HystrixPlugins.getInstance().registerCommandExecutionHook(new HystrixCommandExecutionHook() {
            @Override public <T> void onStart(HystrixInvokable<T> commandInstance) {
                System.out.println("[hookA] onStart: " + Thread.currentThread().getId());
                super.onStart(commandInstance);
            }

            @Override public <T> void onExecutionStart(HystrixInvokable<T> commandInstance) {
                System.out.println("[hookA] onExecutionStart: " + Thread.currentThread().getId());
                super.onExecutionStart(commandInstance);
            }

            @Override public <T> void onExecutionSuccess(HystrixInvokable<T> commandInstance) {
                System.out.println("[hookA] onExecutionSuccess: " + Thread.currentThread().getId());
                super.onExecutionSuccess(commandInstance);
            }

            @Override public <T> Exception onExecutionError(HystrixInvokable<T> commandInstance, Exception e) {
                System.out.println("[hookA] onExecutionError: " + Thread.currentThread().getId());
                return super.onExecutionError(commandInstance, e);
            }

            @Override public <T> Exception onRunError(HystrixCommand<T> commandInstance, Exception e) {
                System.out.println("[hookA] onRunError: " + Thread.currentThread().getId());
                return super.onRunError(commandInstance, e);
            }
        });
    }

    @RequestMapping("/hystrix-case")
    @ResponseBody
    public String hystrixCase() throws InterruptedException, ExecutionException {
        List<Future<String>> fs = new ArrayList<Future<String>>();

        fs.add(new TestBCommand("World").queue());
        System.out.println(new TestACommand("World").execute());
        for (Future<String> f : fs) {
            System.out.println(f.get());
        }
        return "Success";
    }
}
