package test.apache.skywalking.testcase.springcloud.fegin.v2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author caoyixiong
 */
@Controller
@RequestMapping("/case")
public class CaseController {

    @ResponseBody
    @RequestMapping("/spring-cloud-feign")
    public String springCloudFeignCase() {
        return "Hello World";
    }

}
