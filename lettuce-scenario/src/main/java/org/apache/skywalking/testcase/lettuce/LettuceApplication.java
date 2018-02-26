package org.apache.skywalking.testcase.lettuce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("org.apache.skywalking.testcase.lettuce")
public class LettuceApplication {

    public static void main(String[] args) {

        Object[] sources = new Object[] {LettuceApplication.class};
        SpringApplication.run(sources, args);

    }
}
