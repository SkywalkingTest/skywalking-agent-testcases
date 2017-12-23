package com.huwei.devcloud.httpasyncclient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class BackController {
    private static Logger logger = Logger.getLogger(BackController.class);

    @RequestMapping("/back")
    public String back() throws ClassNotFoundException, SQLException {

        String mysql_url = "jdbc:mysql://127.0.0.1:3306/sky?useUnicode=true&characterEncoding=UTF-8";
        String name = "com.mysql.jdbc.Driver";
        String sql = "SELECT person.id,person.`name`FROM person ";
        Class.forName(name);
        Connection conn = DriverManager.getConnection(mysql_url, "root", null);
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t");
        }
        conn.close();
        pst.close();
        return "Hello back";

    }

}
