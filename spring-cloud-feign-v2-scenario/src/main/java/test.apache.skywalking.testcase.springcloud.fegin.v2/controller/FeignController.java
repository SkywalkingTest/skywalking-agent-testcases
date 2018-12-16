package test.apache.skywalking.testcase.springcloud.fegin.v2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/case")
public class FeignController {
    private static final Map<Integer, String> issueMap = new ConcurrentHashMap<>();

    @GetMapping(value = "/get/{userId}")
    @ResponseBody
    public String get(@PathVariable("userId") Integer userId) {
        String data = issueMap.get(userId);
        return data == null ? "No data" : data;
    }

    @PostMapping(value = "/send")
    @ResponseBody
    public String send(@RequestParam("userId") Integer userId, @RequestParam("content") String content) {
        issueMap.put(userId, content);
        return "success";
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public String delete(@RequestParam("id") int id) {
        issueMap.remove(id);
        return "success";
    }

    @PutMapping(value = "/update")
    @ResponseBody
    public String update(@RequestParam("id") Integer id, @RequestParam("content") String content) {
        issueMap.put(id, content);
        return "success";
    }
}
