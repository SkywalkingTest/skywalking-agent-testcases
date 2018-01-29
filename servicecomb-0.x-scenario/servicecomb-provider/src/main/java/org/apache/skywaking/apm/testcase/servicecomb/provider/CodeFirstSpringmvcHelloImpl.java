
package org.apache.skywaking.apm.testcase.servicecomb.provider;


import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.servicecomb.provider.pojo.RpcSchema;
import org.apache.skywaking.apm.testcase.servicecomb.schemma.Hello;
import org.apache.skywaking.apm.testcase.servicecomb.schemma.models.Person;

@RpcSchema(schemaId = "codeFirstSpringmvcHello")
@RequestMapping(path = "/springmvchello", produces = MediaType.APPLICATION_JSON)
public class CodeFirstSpringmvcHelloImpl implements Hello {

  @Override
  @RequestMapping(path = "/sayhi", method = RequestMethod.POST)
  public String sayHi(@RequestParam(name = "name") String name) {
    return "Spring mvc Hello " + name;
  }

  @Override
  @RequestMapping(path = "/sayhello", method = RequestMethod.POST)
  public String sayHello(@RequestBody Person person) {
    return "Spring mvc Hello person " + person.getName();
  }
}
