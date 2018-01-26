/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.skywaking.apm.testcase.servicecomb.consumer;

import io.servicecomb.foundation.common.utils.BeanUtils;
import io.servicecomb.foundation.common.utils.Log4jUtils;
import io.servicecomb.provider.pojo.RpcReference;
import org.apache.log4j.Logger;
import org.apache.skywaking.apm.testcase.servicecomb.schemma.Hello;
import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

@Component
public class CodeFirstConsumerMain {
    private static Logger logger = Logger.getLogger(CodeFirstConsumerMain.class);
    @RpcReference(microserviceName = "codefirst", schemaId = "codeFirstJaxrsHello")
    private static Hello jaxrsHello;

    @RpcReference(microserviceName = "codefirst", schemaId = "codeFirstSpringmvcHello")
    private static Hello springmvcHello;

    @RpcReference(microserviceName = "codefirst", schemaId = "codeFirstHello")
    private static Hello hello;

    public static void main(String[] args) throws Exception {
        init();
        for(int i=0; i<20;i++)
        {
            sleep(1000);
            logger.info( hello.sayHi("Java Chassis"));

        }
    }

    public static void init() throws Exception {
        Log4jUtils.init();
        BeanUtils.init();
    }
}
