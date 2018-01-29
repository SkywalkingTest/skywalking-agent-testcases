
package org.apache.skywaking.apm.testcase.servicecomb.schemma;


import org.apache.skywaking.apm.testcase.servicecomb.schemma.models.Person;

public interface Hello {

  String sayHi(String name);

  String sayHello(Person person);
}
