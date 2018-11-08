package com.ititon.datacraw.model.location;

import com.ititon.datacraw.model.LongIdEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true, exclude = "territorialDivisions")
public class Country extends LongIdEntity {

    @Column(name = "c_name")
    @Getter
    @Setter
    private String name;


    @OneToMany(mappedBy = "country")
    @Getter
    @Setter
    private Set<TerritorialDivision> territorialDivisions = new HashSet<>();
}
