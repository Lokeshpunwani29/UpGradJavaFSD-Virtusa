package com.java.registerservice.repo;

import com.java.registerservice.model.Complaint;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComplaintRepo extends MongoRepository<Complaint, String> {
    Complaint findByComplaintId(Integer complaintId);
}
