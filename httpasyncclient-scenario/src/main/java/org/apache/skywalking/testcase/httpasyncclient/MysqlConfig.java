package org.apache.skywalking.testcase.httpasyncclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
public class MysqlConfig {

    @Value(value = "${mysql.host}")
    private static String host;
    @Value(value = "${mysql.username}")
    private static String userName;
    @Value(value = "${mysql.password}")
    private static String password;
    private static String url = "jdbc:mysql://" + host + "/sky?useUnicode=true&characterEncoding=UTF-8";

    public static String getUrl() {
        return url;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }
}
