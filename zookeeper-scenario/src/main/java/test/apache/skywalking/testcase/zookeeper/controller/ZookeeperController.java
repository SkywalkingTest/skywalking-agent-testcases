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

package test.apache.skywalking.testcase.zookeeper.controller;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


@Controller
@RequestMapping("/zookeeper")
@PropertySource("classpath:application.properties")
public class ZookeeperController implements Watcher {
    private Logger logger = LoggerFactory.getLogger(ZookeeperController.class);

    @Value(value = "${zookeeper.host}")
    private String address;

    @RequestMapping("/zookeeper-case")
    @ResponseBody
    public String zookeeperCase() throws KeeperException, InterruptedException, IOException {
        ZooKeeper zooKeeper = new ZooKeeper(address, 200000, this);
        zooKeeper.create("/path", "data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zooKeeper.setData("/path", "data".getBytes(), -1);
        zooKeeper.exists("/path", false);
        zooKeeper.delete("/path", -1);
        return "Success";
    }

    @Override
    public void process(WatchedEvent event) {
        logger.info("process");
    }
}
