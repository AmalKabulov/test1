package com.ititon.datacraw.repository;

import com.ititon.datacraw.model.Agent;

import java.util.Optional;

public interface AgentRepository extends BaseRepository<Agent> {
    Optional<Agent> findByName(String name);
}
