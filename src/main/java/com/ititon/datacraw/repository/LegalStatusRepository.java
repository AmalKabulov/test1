package com.ititon.datacraw.repository;

import com.ititon.datacraw.model.LegalStatus;

import java.util.Optional;

public interface LegalStatusRepository extends BaseRepository<LegalStatus> {

    Optional<LegalStatus> findByStatus(String status);
}
