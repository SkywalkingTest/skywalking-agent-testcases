package org.apache.skywalking.testcase.lettuce;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import com.lambdaworks.redis.api.sync.RedisCommands;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConfigurationProperties
@PropertySource("classpath:application.properties")
public class BackController {
    private static Logger logger = Logger.getLogger(BackController.class);
    @Value(value = "${redis.host:100.100.145.125:6379}")
    private String host;
    @RequestMapping("/sync")
    public String singleSync() {
        RedisClient redisClient = RedisClient.create("redis://"+host+"/0");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        //Sync invoke
        RedisCommands<String, String> syncCommands = connection.sync();
        syncCommands.set("key", "Hello,sync Redis!");
        logger.info(syncCommands.get("key"));
        //Async invoke
        RedisAsyncCommands<String, String> asyncCommands = connection.async();
        asyncCommands.set("key", "Hello,async Redis!");
        logger.info(asyncCommands.get("key"));
        connection.close();
        redisClient.shutdown();
        return "Hello back";

    }
}
