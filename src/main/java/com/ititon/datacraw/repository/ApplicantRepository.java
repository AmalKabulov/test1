package com.ititon.datacraw.repository;

import com.ititon.datacraw.model.Applicant;

import java.util.Optional;

public interface ApplicantRepository extends BaseRepository<Applicant> {
    Optional<Applicant> findByName(String name);
}
