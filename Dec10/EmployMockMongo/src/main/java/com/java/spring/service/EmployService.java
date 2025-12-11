package com.java.spring.service;

import com.java.spring.model.Employ;
import com.java.spring.repo.EmployRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployService {

    @Autowired
    private EmployRepository employRepository;

    public List<Employ> showEmploy() {
        return employRepository.findAll();
    }

    public Employ searchEmploy(int empno) {
        return employRepository.findByEmpno(empno);
    }

    public String addEmploy(Employ employ) {
        employRepository.save(employ);
        return "Employ Record Inserted...";
    }
}
