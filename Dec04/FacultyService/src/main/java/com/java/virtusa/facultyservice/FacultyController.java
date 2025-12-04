package com.java.virtusa.facultyservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @GetMapping("/info")
    public String mail() {
        return "Good Morning Faculty Members. Welcome, Kindly mark your attendance at entrance. Have a Nice day! \n Sending from 9001";
    }

    @GetMapping("/task")
    public String task() {
        return "Focus on improving overall academics of the Institution. By doing engaging and interactive teaching sessions.";
    }

    @GetMapping("/class")
    public String class_() {
        return "You have given responsibility as Class Teacher of 8th Standard Section A";
    }
}
