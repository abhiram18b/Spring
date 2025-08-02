package com.example.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    @Value("${user.name}")
    public String userName;

    @Value("${framework.name}")
    public String frameworkName;

    @GetMapping("/userinfo")
    public String getUserInfo(){
        return userName+" is learning "+frameworkName;
    }

    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }

    @GetMapping("/workout")
    public String workout(){
        return "Run a hard 5k!";
    }

    @GetMapping("/fortune")
    public String getDailyFortune(){
        return "Today is your lucky day";
    }
}
