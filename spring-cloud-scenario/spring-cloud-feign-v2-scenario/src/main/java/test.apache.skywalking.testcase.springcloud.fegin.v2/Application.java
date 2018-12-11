package test.apache.skywalking.testcase.springcloud.fegin.v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author caoyixiong
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("test.apache.skywalking.testcase.springcloud.fegin.v2")
public class Application extends SpringApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
        } catch (Exception e) {
            // Never do this
        }
    }
}