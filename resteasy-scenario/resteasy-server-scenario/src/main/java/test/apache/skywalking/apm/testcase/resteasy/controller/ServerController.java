package test.apache.skywalking.apm.testcase.resteasy.controller;

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
                    Thread.sleep(500);
                    response.resume(Response.ok("Hello Async!").type(MediaType.TEXT_PLAIN).build());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
