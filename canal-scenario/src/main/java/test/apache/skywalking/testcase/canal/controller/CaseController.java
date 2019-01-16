/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package test.apache.skywalking.testcase.canal.controller;


import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetSocketAddress;


@Controller
@RequestMapping("/case")
@PropertySource("classpath:application.properties")
public class CaseController {

    private Logger logger = LogManager.getLogger(CaseController.class);

    @Value(value = "${canal.host}")
    private String address;



    @RequestMapping("/canal-case")
    @ResponseBody
    public String canalCase() {
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(address,
                11111), "example", "", "");
        try{

            int batchSize = 1000;
            connector.connect();
            connector.subscribe(".*\\..*");
            connector.rollback();
             Message message = connector.getWithoutAck(batchSize);
             long batchId = message.getId();

             logger.info(message.getEntries());

             connector.ack(batchId);



        }catch (Exception ex){

            logger.error(ex.toString());

        }finally {
            connector.disconnect();
        }
        return "Success";
    }

}
