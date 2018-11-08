package com.ititon.datacraw.repository;

import com.ititon.datacraw.model.location.City;
import com.ititon.datacraw.model.location.District;

import java.util.Optional;

public interface DistrictRepository extends BaseRepository<District> {

    Optional<District> findByNameNative(String nameNative);
    Optional<District> findByNameEn(String nameEn);
    Optional<District> findByNameEnAndAndCity(String nameEn, City city);
}
