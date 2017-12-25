package org.apache.skywalking.testcase.nutz.module;

import javax.servlet.http.HttpServletRequest;

import org.nutz.http.Http;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;

@IocBean
@At("/case")
public class CaseSenderModule {

    @At
    public String httpget(HttpServletRequest req) {
        // call org.apache.skywalking.testcase.nutz.module.CaseModule.ping()
        return "httpget-"+Http.get("http://127.0.0.1:8080/" + req.getContextPath() + "/case/ping").getContent();
    }
}
