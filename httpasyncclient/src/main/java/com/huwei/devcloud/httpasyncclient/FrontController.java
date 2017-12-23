package com.huwei.devcloud.httpasyncclient;

import java.io.IOException;
import java.nio.CharBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import org.apache.http.protocol.HttpContext;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
public class FrontController {


    private static final String DEFAULT_CHARSET = "UTF-8";
    @RequestMapping("/front")
    public String front() throws Exception {

        String content=asyncRequest3("http://127.0.0.1:8080/back");
        System.out.println(content);
        return content;
    }


    public static String asyncRequest3(String url) throws IOException {
        String mysql_url = "jdbc:mysql://127.0.0.1:3306/sky?useUnicode=true&characterEncoding=UTF-8";
        String name = "com.mysql.jdbc.Driver";
        String sql = "SELECT\n" +
            "person.id,\n" +
            "person.`name`\n" +
            "FROM\n" +
            "person\n";


        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();

        httpclient.start();
        HttpResponse resp;

        //three
        // In real world one most likely would also want to stream
        // request and response body content
        final HttpGet request3 = new HttpGet(url);
        HttpAsyncRequestProducer producer3 = HttpAsyncMethods.create(request3);
        AsyncCharConsumer<HttpResponse> consumer3 = new AsyncCharConsumer<HttpResponse>() {

            HttpResponse response;

            @Override
            protected void onResponseReceived(final HttpResponse response) {
                this.response = response;

            }

            @Override
            protected void onCharReceived(final CharBuffer buf, final IOControl ioctrl) throws IOException {
                // Do something useful
            }

            @Override
            protected void releaseResources() {
            }

            @Override
            protected HttpResponse buildResult(final HttpContext context) {
                return this.response;
            }

        };
        httpclient.execute(producer3, consumer3, new FutureCallback<HttpResponse>() {

            public void completed(final HttpResponse response3) {
                System.out.println(request3.getRequestLine() + "->" + response3.getStatusLine());
                try {
                    httpclient.close();
                    Class.forName(name);
                    Connection conn = DriverManager.getConnection(mysql_url, "root", null);
                    PreparedStatement pst = conn.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()){
                        System.out.println(rs.getString(1) + "\t");
                    }
                    conn.close();
                    pst.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            public void failed(final Exception ex) {
                System.out.println(request3.getRequestLine() + "->" + ex);
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void cancelled() {
                System.out.println(request3.getRequestLine() + " cancelled");
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });


        return "asyncRequest3 done";
    }


}
