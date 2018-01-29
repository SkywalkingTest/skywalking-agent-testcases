
package org.apache.skywaking.apm.testcase.servicecomb.consumer;

import io.servicecomb.provider.pojo.RpcReference;
import io.servicecomb.provider.pojo.RpcSchema;
import javax.ws.rs.core.MediaType;
import org.apache.skywaking.apm.testcase.servicecomb.schemma.Hello;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RpcSchema(schemaId = "codeFirstSpringmvcHello")
@RequestMapping(path = "/servicecomb", produces = MediaType.APPLICATION_JSON)
public class CodeFirstPojoHelloImpl {
    @RpcReference(microserviceName = "codefirst", schemaId = "codeFirstHello")
    private static Hello hello;

    @RequestMapping(path = "/case", method = RequestMethod.GET)
    public String say() {
        return hello.sayHi("Java Chassis");
    }
}
