package com.java.registerservice.service;

import com.java.registerservice.model.Complaint;
import com.java.registerservice.repo.ComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepo complaintRepo;

    public String addComplaint(Complaint complaint) {
        complaintRepo.save(complaint);
        return "Complaint added successfully";
    }

    public List<Complaint> showComplaint() {
        return complaintRepo.findAll();
    }

    public Complaint findComplaint(Integer complaintId) {
        return complaintRepo.findByComplaintId(complaintId);
    }

    public String deleteComplaint(Integer complaintId) {
        complaintRepo.delete(complaintRepo.findByComplaintId(complaintId));
        return "Complaint deleted successfully";
    }
}
