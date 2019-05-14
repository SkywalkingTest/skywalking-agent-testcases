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

package test.apache.skywalking.apm.testcase.resteasy.controller;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author yan-fucheng
 */
@Path("/case")
public class ServerController {

    @GET
    @Path("sync")
    @Produces("text/plain")
    public String syncRequest() {
        return "Hello Sync!";
    }

    @GET
    @Path("async")
    @Produces("text/plain")
    public void asyncRequest(@Suspended final AsyncResponse response) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(100);
                    response.resume(Response.ok("Hello Async!").type(MediaType.TEXT_PLAIN).build());
                } catch (Exception ignored) {
                }
            }
        }).start();
    }

    @GET
    @Path("call")
    @Produces("text/plain")
    public String callAll() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:18080/resteasy-server-case/case/sync");
        Response response = target.request().get();
        response.close();

        target = client.target("http://localhost:18080/resteasy-server-case/case/async");
        Response response1 = target.request().get();
        response1.close();
        return "success";
    }
}
