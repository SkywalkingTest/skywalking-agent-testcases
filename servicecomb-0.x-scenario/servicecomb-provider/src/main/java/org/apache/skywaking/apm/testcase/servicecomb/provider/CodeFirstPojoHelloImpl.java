
package org.apache.skywaking.apm.testcase.servicecomb.provider;

import io.servicecomb.provider.pojo.RpcSchema;
import org.apache.skywaking.apm.testcase.servicecomb.schemma.Hello;
import org.apache.skywaking.apm.testcase.servicecomb.schemma.models.Person;

@RpcSchema(schemaId = "codeFirstHello")
public class CodeFirstPojoHelloImpl implements Hello {

  @Override
  public String sayHi(String name) {
    return "Pojo Hello " + name;
  }

  @Override
  public String sayHello(Person person) {
    return "Pojo Hello person " + person.getName();
  }
}
