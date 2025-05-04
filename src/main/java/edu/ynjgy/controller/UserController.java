package edu.ynjgy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/loginmanager")
    public String loginmanager() {
        System.out.println("管理员访问");
        return "login";
    }
    @RequestMapping("/loginstudent")
    public String loginstudent() {
        System.out.println("学生访问请求");
        return "login";
    }
    @RequestMapping("/logintourist")
    public String logintourist() {
        System.out.println("游客请求访问");
        return "login";
    }

}
