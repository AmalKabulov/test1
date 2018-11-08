package com.ititon.datacraw.service.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Entity;

public class Some {

    @Column(name = "applicant_id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "applicant_name")
    @Getter
    @Setter
    private String apname;

    @Column(name = "city_name")
    @Getter
    @Setter
    private String cityName;

    @Column(name = "count_of_patents")
    @Getter
    @Setter
    private Long counOf;

}
