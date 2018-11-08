package com.ititon.datacraw.service.impl;

import com.ititon.datacraw.model.Patent;
import com.ititon.datacraw.repository.PatentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PatentServiceImpl {
    private PatentRepository patentRepository;


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PatentServiceImpl(PatentRepository patentRepository) {
        this.patentRepository = patentRepository;
    }


    public void saveAll(Collection<Patent> patents) {
        List<Patent> collect = patents.stream()
                .filter(p -> Objects.equals(null, patentRepository.findByPublicationNum(p.getPublicationNum())))
                .collect(Collectors.toList());

        patentRepository.saveAll(collect);
    }


    @Transactional
    public void smth() {
        System.out.println();
        Query nativeQuery = entityManager.createNativeQuery("select \n" +
                "\tapplicant_id, \n" +
                "    app.ap_name, \n" +
                "    c.name_en,\n" +
                "    count(*) as countOf \n" +
                "from patents \n" +
                "\tleft join applicants as app \n" +
                "    on app.id = patents.applicant_id\n" +
                "    join districts as d on d.id = app.district_id\n" +
                "    join cities as c on c.id = d.city_id\n" +
                "group by applicant_id \n" +
                "having count(*) > 1 \n" +
                "order by countOf desc" +
                "limit 100;");

        List<Object[]> resultList = nativeQuery.getResultList();

    }


    @PreDestroy
    private void close() {
        entityManager.flush();
        entityManager.close();
    }
}
