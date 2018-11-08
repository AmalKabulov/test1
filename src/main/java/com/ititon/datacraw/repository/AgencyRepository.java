package com.ititon.datacraw.repository;

import com.ititon.datacraw.model.Agency;

import java.util.Optional;

public interface AgencyRepository extends BaseRepository<Agency> {
    Optional<Agency> findByName(String name);
}
