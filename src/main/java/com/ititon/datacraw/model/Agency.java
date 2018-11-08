package com.ititon.datacraw.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "agencies")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true, exclude = "patents")
public class Agency extends LongIdEntity{

    @Column(name = "ac_name")
    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "agency")
    @Getter
    @Setter
    private Set<Patent> patents = new HashSet<>();
}
