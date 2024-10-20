package com.techelevator.controller;

import com.techelevator.dao.AgentDao;
import com.techelevator.dao.AthleteDao;
import com.techelevator.model.Agent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/agents")
/*@PreAuthorize("isAuthenticated()")*/
public class AgentController {

    private final AgentDao agentDao;

    private final AthleteDao athleteDao;

    public AgentController(AgentDao agentDao, AthleteDao athleteDao) {
        this.agentDao = agentDao;
        this.athleteDao = athleteDao;
    }

    //Lists all Agents in project
    @GetMapping
    public List<Agent> getAllAgents() {
        return agentDao.getAllAgents();
    }

    //Gets an agent by their ID
    @GetMapping("/{agentId}")
    public Agent getAgentById(@PathVariable int agentId) {
        Agent agent = agentDao.getAgentById(agentId);
        if (agent == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agent not found");
        }
        return agent;
    }

    //adds an agent but can only be done by an ADMIN
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Agent addAgent(@RequestBody Agent newAgent) {
        return agentDao.addAgent(newAgent);

    }

    // This method updates an Agents profile but can only be done by an ADMIN
    @PutMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN')")
    public Agent updateAgent(@PathVariable int id, @RequestBody Agent updatedAgent) {
        return agentDao.updateAgent(id, updatedAgent);

    }

    //Deletes an Agent. can ONLY be done by an ADMIN.
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAgent(@PathVariable int id) {
        agentDao.deleteAgent(id);

    }
}
