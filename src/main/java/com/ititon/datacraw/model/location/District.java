package com.ititon.datacraw.model.location;

import com.ititon.datacraw.model.Applicant;
import com.ititon.datacraw.model.LongIdEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "districts")
@ToString(callSuper = true, exclude = "applicants")
@AllArgsConstructor
@NoArgsConstructor
public class District extends LongIdEntity {

    @Column(name = "name_en")
    @Getter
    @Setter
    private String nameEn;

    @Column(name = "name_native")
    @Getter
    @Setter
    private String nameNative;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @Getter
    @Setter
    private City city;

    @OneToMany(mappedBy = "district")
    @Getter
    @Setter
    private Set<Applicant> applicants = new HashSet<>();



}
