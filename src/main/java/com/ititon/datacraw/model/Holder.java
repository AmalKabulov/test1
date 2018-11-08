package com.ititon.datacraw.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "holders")
@ToString(callSuper = true, exclude = "patents")
@AllArgsConstructor
@NoArgsConstructor
public class Holder extends LongIdEntity {

    @Column(name = "h_name")
    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "holder")
    @Getter
    @Setter
    private Set<Patent> patents = new HashSet<>();
}
