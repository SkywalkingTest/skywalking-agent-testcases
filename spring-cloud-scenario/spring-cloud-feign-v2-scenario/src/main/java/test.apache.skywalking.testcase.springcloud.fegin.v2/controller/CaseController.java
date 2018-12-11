package test.apache.skywalking.testcase.springcloud.fegin.v2.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
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
    @RequestMapping("/spring-cloud-feign-v2")
    public String springCloudFeignCase() {
//        String allItems = feignInterface.getAllItems();
        String allUserItems = feignInterface.getAllUserItems(1);
        String stringData1 = feignInterface.delete(1);
        String stringData2 = feignInterface.send(1, "test", "test");
        String stringData3 = feignInterface.update(1, "test-content");
//        logger.info("allItems :" + allItems);
        logger.info("allUserItems :" + allUserItems);
        logger.info("stringData1 :" + stringData1);
        logger.info("stringData2 :" + stringData2);
        logger.info("stringData3 :" + stringData3);
        return "Hello World";
    }

}
