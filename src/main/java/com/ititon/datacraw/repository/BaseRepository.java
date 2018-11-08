package com.ititon.datacraw.repository;

import com.ititon.datacraw.model.LongIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<E extends LongIdEntity>
        extends JpaRepository<E, Long>
{
}
