package com.java.agentservice.service;

import com.java.agentservice.model.Agent;
import com.java.agentservice.repo.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    public String addAgent(Agent agent){
        agentRepository.save(agent);
        return "Agent added successfully";
    }

    public Agent getAgent(int agentId){
        return agentRepository.findByAgentId(agentId);
    }

    public List<Agent> showAgent(){
        return agentRepository.findAll();
    }

    public String deleteAgent(int  agentId){
        agentRepository.delete(agentRepository.findByAgentId(agentId));
        return "Agent deleted successfully";
    }
}
