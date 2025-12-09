package com.java.registerservice.controller;


import com.java.registerservice.model.Complaint;
import com.java.registerservice.repo.ComplaintRepo;
import com.java.registerservice.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/showComplaint")
    public List<Complaint> showComplaint(){
        return complaintService.showComplaint();
    }

    @GetMapping("/searchComplaint/{complaintId}")
    public Complaint searchComplaint(@PathVariable("complaintId") int complaintId){
        return complaintService.findComplaint(complaintId);
    }

    @PostMapping("/addComplaint")
    public String addComplaint(@RequestBody Complaint complaint){
        return complaintService.addComplaint(complaint);
    }

    @DeleteMapping("/deleteComplaint/{complaintId}")
    public String deleteComplaint(@PathVariable("complaintId") int complaintId){
        return complaintService.deleteComplaint(complaintId);
    }
}
