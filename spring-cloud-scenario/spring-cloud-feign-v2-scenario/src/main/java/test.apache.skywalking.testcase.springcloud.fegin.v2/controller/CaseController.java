package test.apache.skywalking.testcase.springcloud.fegin.v2.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author caoyixiong
 */
@Controller
@RequestMapping("/case")
public class CaseController {

    private Logger logger = LogManager.getLogger(CaseController.class);

    @Resource
    private IFeignInterface feignInterface;

    @ResponseBody
    @GetMapping("/spring-cloud-feign-v2")
    public String springCloudFeignCase() {
        String stringData = feignInterface.get(1);
        String stringData2 = feignInterface.send(1, "test", "test");
        String stringData1 = feignInterface.delete(1);
        String stringData3 = feignInterface.update(1, "test-content");
        logger.info("stringData :" + stringData);
        logger.info("stringData1 :" + stringData1);
        logger.info("stringData2 :" + stringData2);
        logger.info("stringData3 :" + stringData3);
        return "Hello World";
    }

}
