package com.techelevator.dao;

import com.techelevator.model.Agent;

import java.util.List;

public interface AgentDao {
    List<Agent> getAllAgents();
    Agent getAgentById(int agentId);
    Agent addAgent(Agent newAgent);
    Agent updateAgent(int id, Agent agent);
    void deleteAgent(int agentId);
}
