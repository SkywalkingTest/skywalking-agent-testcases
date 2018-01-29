
package org.apache.skywaking.apm.testcase.servicecomb.consumer;

import io.servicecomb.foundation.common.utils.BeanUtils;
import io.servicecomb.foundation.common.utils.Log4jUtils;
import org.springframework.stereotype.Component;

@Component
public class CodeFirstConsumerMain {

    public static void main(String[] args) throws Exception {
        init();
    }

    public static void init() throws Exception {
        Log4jUtils.init();
        BeanUtils.init();
    }

}
