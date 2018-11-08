package com.ititon.datacraw.repository;

import com.ititon.datacraw.model.location.City;

import java.util.Optional;

public interface CitiesRepository extends BaseRepository<City>{

    Optional<City> findByNameEn(String nameEn);
}
