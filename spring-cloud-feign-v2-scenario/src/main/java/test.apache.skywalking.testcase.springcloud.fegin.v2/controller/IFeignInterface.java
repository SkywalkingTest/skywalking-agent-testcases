package test.apache.skywalking.testcase.springcloud.fegin.v2.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "fegin-case", url = "http://localhost:8080")
public interface IFeignInterface {

    @RequestMapping(value = "/case/get/{userId}", method = RequestMethod.GET)
    public String get(@PathVariable("userId") Integer userId) ;

    @RequestMapping(value = "/case/send", method = RequestMethod.POST)
    public String send(@RequestParam("userId") Integer userId,
                       @RequestParam("title") String title, @RequestParam("content") String content);

    @RequestMapping(value = "/case/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam("id") int id);

    @RequestMapping(value = "/case/update", method = RequestMethod.PUT)
    public String update(@RequestParam("id") Integer id,
                         @RequestParam("content") String content);

}
