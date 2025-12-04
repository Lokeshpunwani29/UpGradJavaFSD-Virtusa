package com.java.virtusa.studentservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/info")
    public String info(){
        return "Good Morning Students, Kindly mark your attendance to your class teacher at time \n Sending from 9002 :)";
    }

    @GetMapping("/task")
    public String task(){
        return "Students must adhere the rules of the Institution and attend the classes daily. \nFocus on improvising yourself from daily studies.";
    }

    @GetMapping("class")
    public String classInfo(){
        return "You are currently in 6th Standard. Your Class teacher is Neha Sharma.";
    }
}
