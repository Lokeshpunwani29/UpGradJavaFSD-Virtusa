package com.java.spring.repo;


import com.java.spring.model.Employ;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployRepository extends MongoRepository<Employ,String> {
    Employ findByEmpno(int empno);
}
