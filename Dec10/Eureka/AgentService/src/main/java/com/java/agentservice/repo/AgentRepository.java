package com.java.agentservice.repo;

import com.java.agentservice.model.Agent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgentRepository extends MongoRepository<Agent, String> {
    Agent findByAgentId(int agentId);
}
