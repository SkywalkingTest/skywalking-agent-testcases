package test.apache.skywalking.testcase.springcloud.fegin.v2.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author caoyixiong
 */
@FeignClient(value = "fegin-case", url = "http://localhost:8080")
public interface IFeignInterface {

    @RequestMapping(value = "/case/getAllItems", method = RequestMethod.GET)
    public String getAllItems();

    @RequestMapping(value = "/case/getAllUserItems", method = RequestMethod.GET)
    public String getAllUserItems(@RequestParam("userId") Integer userId) ;

    @RequestMapping(value = "/case/send", method = RequestMethod.POST)
    public String send(@RequestParam("userId") Integer userId,
                       @RequestParam("title") String title, @RequestParam("content") String content);

    @RequestMapping(value = "/case/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") int id);

    @RequestMapping(value = "/case/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") Integer id,
                         @RequestParam("content") String content);

}
