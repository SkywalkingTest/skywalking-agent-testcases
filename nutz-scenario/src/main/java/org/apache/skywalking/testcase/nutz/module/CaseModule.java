package org.apache.skywalking.testcase.nutz.module;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;

@IocBean
@At("/case")
public class CaseModule {

    @At
    public String nutz() {
        return "hi";
    }

    @At
    public String ping() {
        return "pong";
    }
}
