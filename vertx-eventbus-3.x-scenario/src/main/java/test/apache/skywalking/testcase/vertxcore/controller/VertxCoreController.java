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

package test.apache.skywalking.testcase.vertxcore.controller;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import test.apache.skywalking.testcase.vertxcore.util.CustomMessage;
import test.apache.skywalking.testcase.vertxcore.util.CustomMessageCodec;

public class VertxCoreController extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.get("/vertx-eventbus-3-scenario/vertx-eventbus/eventbus-case").handler(this::handleCoreCase);
        router.get("/vertx-eventbus-3-scenario/vertx-eventbus/executeTest").handler(this::executeTest);
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

        vertx.eventBus().registerDefaultCodec(CustomMessage.class, new CustomMessageCodec());
        vertx.deployVerticle(LocalReceiver.class.getName());
    }

    private void handleCoreCase(RoutingContext routingContext) {
        vertx.createHttpClient().getNow(8080, "localhost",
                "/vertx-eventbus-3-scenario/vertx-eventbus/executeTest",
                it -> routingContext.response().setStatusCode(it.statusCode()).end());
    }

    private void executeTest(RoutingContext routingContext) {
        Future<Void> localMessageFuture = Future.future();
        CustomMessage localMessage = new CustomMessage(200,
                "a0000001", "Local message!");
        vertx.eventBus().send("local-message-receiver", localMessage, reply -> {
            if (reply.succeeded()) {
                CustomMessage replyMessage = (CustomMessage) reply.result().body();
                System.out.println("Received local reply: " + replyMessage.getSummary());
                localMessageFuture.complete();
            } else {
                localMessageFuture.fail(reply.cause());
            }
        });

        Future<Void> clusterMessageFuture = Future.future();
        CustomMessage clusterWideMessage = new CustomMessage(200,
                "a00000001", "Message sent from publisher!");
        vertx.eventBus().send("cluster-message-receiver", clusterWideMessage, reply -> {
            if (reply.succeeded()) {
                CustomMessage replyMessage = (CustomMessage) reply.result().body();
                System.out.println("Received reply: " + replyMessage.getSummary());
                clusterMessageFuture.complete();
            } else {
                clusterMessageFuture.fail(reply.cause());
            }
        });

        localMessageFuture.setHandler(localHandler -> {
            if (localHandler.succeeded()) {
                clusterMessageFuture.setHandler(clusterHandler -> {
                    if (clusterHandler.succeeded()) {
                        routingContext.response().setStatusCode(200).end();
                    } else {
                        routingContext.response().setStatusCode(500).end(Json.encodePrettily(clusterHandler.cause()));
                    }
                });
            } else {
                routingContext.response().setStatusCode(500).end(Json.encodePrettily(localHandler.cause()));
            }
        });
    }
}
