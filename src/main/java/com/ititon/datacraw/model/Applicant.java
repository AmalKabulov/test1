package com.ititon.datacraw.model;

import com.ititon.datacraw.model.location.District;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "applicants")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true, exclude = "patents")
public class Applicant extends LongIdEntity {

    @Column(name = "ap_name")
    @Getter
    @Setter
    private String name;

    @Column(name = "ap_address")
    @Getter
    @Setter
    private String address;

    @Column(name = "ap_index")
    @Getter
    @Setter
    private String index;

    @ManyToOne
    @JoinColumn(name = "district_id")
    @Getter
    @Setter
    private District district;

    @OneToMany(mappedBy = "applicant")
    @Getter
    @Setter
    private Set<Patent> patents = new HashSet<>();



}
