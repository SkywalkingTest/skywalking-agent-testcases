package org.apache.skywalking.testcase.httpasyncclient;

import java.io.IOException;
import java.nio.CharBuffer;
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
import org.apache.log4j.Logger;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:application.properties")
public class FrontController {

    private static Logger logger = Logger.getLogger(FrontController.class);

    @RequestMapping("/front")
    @ResponseBody
    public String front() throws Exception {
        String content = asyncRequest3("http://127.0.0.1:8080/httpasyncclient/back");
        return content;
    }

    public static final String asyncRequest3(String url) throws IOException {
        final CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();

        httpclient.start();

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
                logger.info(request3.getRequestLine() + "->" + response3.getStatusLine());
                try {
                    httpclient.close();
                } catch (IOException e) {
                    logger.error("Httpclient  close failed" + e);
                }
                SQLUtils.query();
            }

            public void failed(final Exception ex) {
                logger.error(request3.getRequestLine() + "->" + ex);
            }

            public void cancelled() {
                logger.error(request3.getRequestLine() + " cancelled");
            }

        });

        return "asyncRequest done";
    }

}
