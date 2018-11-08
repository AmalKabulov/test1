package com.ititon.datacraw.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patent_types")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true, exclude = "patents")
public class PatentType extends LongIdEntity {

    @Column(name = "p_type")
    @Getter
    @Setter
    private String type;


    @OneToMany(mappedBy = "patentType")
    @Getter
    @Setter
    private Set<Patent> patents = new HashSet<>();
}
