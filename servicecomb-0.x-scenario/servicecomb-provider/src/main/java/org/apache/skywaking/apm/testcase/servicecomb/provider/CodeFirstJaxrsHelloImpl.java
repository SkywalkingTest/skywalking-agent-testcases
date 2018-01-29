
package org.apache.skywaking.apm.testcase.servicecomb.provider;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.servicecomb.provider.pojo.RpcSchema;
import org.apache.skywaking.apm.testcase.servicecomb.schemma.Hello;
import org.apache.skywaking.apm.testcase.servicecomb.schemma.models.Person;

@RpcSchema(schemaId = "codeFirstJaxrsHello")
@Path("/codefirstjaxrshello")
@Produces(MediaType.APPLICATION_JSON)
public class CodeFirstJaxrsHelloImpl implements Hello {

  @Path("/sayhi")
  @POST
  @Override
  public String sayHi(String name) {
    return "Jaxrs Hello " + name;
  }

  @Path("/sayhello")
  @POST
  @Override
  public String sayHello(Person person) {
    return "Jaxrs Hello person " + person.getName();
  }
}
