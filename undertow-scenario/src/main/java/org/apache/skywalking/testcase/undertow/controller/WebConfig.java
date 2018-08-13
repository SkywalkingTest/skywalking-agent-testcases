package org.apache.skywalking.testcase.undertow.controller;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright @ 2018/8/13
 *
 * @author cloudgc
 */
@Configuration
public class WebConfig {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new CaseServlet(), "/case/undertow");
    }
}
