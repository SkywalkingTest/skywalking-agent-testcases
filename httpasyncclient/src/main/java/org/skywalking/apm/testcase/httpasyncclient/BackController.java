package org.skywalking.apm.testcase.httpasyncclient;

import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration()
public class BackController {
    private static Logger logger = Logger.getLogger(BackController.class);

    @RequestMapping("/back")
    public String back() throws ClassNotFoundException, SQLException {

        Utils.query();
        logger.info("Hello back");
        return "Hello back";

    }

}