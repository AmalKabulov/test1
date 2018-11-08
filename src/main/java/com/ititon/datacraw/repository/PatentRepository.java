package com.ititon.datacraw.repository;

import com.ititon.datacraw.model.Patent;

public interface PatentRepository extends BaseRepository<Patent>{

    Patent findByPublicationNum(String publicationNum);
    Patent findByNameNativeAndApplicationFullNumAndPublicationNum(String nameNative, String applicationFullNum, String publicationNum);
}
