package com.ititon.datacraw.repository;

import com.ititon.datacraw.model.Holder;

import java.util.Optional;

public interface HolderRepository extends BaseRepository<Holder> {
    Optional<Holder> findByName(String name);

}
