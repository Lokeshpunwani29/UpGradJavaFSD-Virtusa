package com.java.agentservice.Controller;

import com.java.agentservice.model.Agent;
import com.java.agentservice.repo.AgentRepository;
import com.java.agentservice.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/agent")
public class AgentController {
    @Autowired
    private AgentService agentService;

    @GetMapping("/showAgent")
    public List<Agent> showAgent(){
        return agentService.showAgent();
    }

    @PostMapping("/addAgent")
    public String addAgent(@RequestBody Agent agent){
        return agentService.addAgent(agent);
    }

    @GetMapping("/searchAgent/{agentId}")
    public Agent addAgent(@PathVariable("agentId") int agentId){
        return agentService.getAgent(agentId);
    }

    @DeleteMapping("/deleteAgent/{agentId}")
    public String deleteAgent(@PathVariable("agentId") int agentId){
        return agentService.deleteAgent(agentId);
    }

}
