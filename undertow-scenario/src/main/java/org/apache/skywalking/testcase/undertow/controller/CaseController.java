package org.apache.skywalking.testcase.undertow.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/case/backup")
public class CaseController {

    private Logger logger = LogManager.getLogger(CaseController.class);

    @ResponseBody
    @RequestMapping("/undertow")
    public String undertowCase(String receiveFlg) {
        logger.info("receive:{}", receiveFlg);
        return "success";
    }
}
