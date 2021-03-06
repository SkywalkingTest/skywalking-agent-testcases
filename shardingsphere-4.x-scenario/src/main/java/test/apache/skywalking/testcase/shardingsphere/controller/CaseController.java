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

package test.apache.skywalking.testcase.shardingsphere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import test.apache.skywalking.testcase.shardingsphere.service.api.service.CommonService;
import test.apache.skywalking.testcase.shardingsphere.service.config.ShardingDatabasesAndTablesConfigurationPrecise;
import test.apache.skywalking.testcase.shardingsphere.service.repository.jdbc.JDBCOrderItemRepositoryImpl;
import test.apache.skywalking.testcase.shardingsphere.service.repository.jdbc.JDBCOrderRepositoryImpl;
import test.apache.skywalking.testcase.shardingsphere.service.repository.service.RawPojoService;

import javax.sql.DataSource;
import java.sql.SQLException;

@Controller
@RequestMapping("/case")
public class CaseController {
    
    @RequestMapping("/execute")
    @ResponseBody
    public String execute() throws SQLException {
        DataSource dataSource = new ShardingDatabasesAndTablesConfigurationPrecise().getDataSource();
        CommonService commonService = new RawPojoService(new JDBCOrderRepositoryImpl(dataSource), new JDBCOrderItemRepositoryImpl(dataSource));
        commonService.processSuccess(false);
        return "Success";
    }
}
