package org.apache.skywalking.testcase.undertow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Copyright @ 2018/8/11
 *
 * @author cloudgc
 */
@Controller
public class CaseForwardController {

     private final static Logger log = LoggerFactory.getLogger(CaseForwardController.class);

    @RequestMapping("/forwardone")
    public String startForwardOne(boolean nodeOne){

        return "nodeOne";
    }

    @RequestMapping("/forwardTwo")
    public String startForwardTwo(){

        return "forwardTwo";
    }
    @RequestMapping("/forwardend")
    public String forardEnd(){
        return "forwardend";
    }



}
