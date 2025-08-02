package com.example.springcore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springcore.common.Coach;
@RestController
public class DemoController {
    //field for dependency

    private Coach myCoach;

    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach myCoach){
        System.out.println("In Constructor : "+ getClass().getSimpleName());
        this.myCoach = myCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }

}
