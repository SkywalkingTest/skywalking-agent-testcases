package org.apache.skywalking.testcase.nutz;

import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Ok;

@Ok("raw") // just return text/plain is ok
@IocBy(args= {"*anno", "org.apache.skywalking.testcase.nutz"})
public class MainModule {}
