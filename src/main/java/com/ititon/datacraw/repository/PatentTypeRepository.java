package com.ititon.datacraw.repository;

import com.ititon.datacraw.model.PatentType;

import java.util.Optional;

public interface PatentTypeRepository extends BaseRepository<PatentType> {

    Optional<PatentType> findByType(String type);
}
