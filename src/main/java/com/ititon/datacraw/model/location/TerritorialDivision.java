package com.ititon.datacraw.model.location;

import com.ititon.datacraw.model.LongIdEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "adm_ter_divisions")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true, exclude = "cities")
public class TerritorialDivision extends LongIdEntity {

    @Column(name = "name_en")
    @Getter
    @Setter
    private String nameEn;

    @Column(name = "name_native")
    @Getter
    @Setter
    private String nameNative;

    @OneToMany(mappedBy = "territorialDivision", fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Set<City> cities;


    @ManyToOne
    @JoinColumn(name = "country_id")
    @Getter
    @Setter
    private Country country;


    @Column(name = "t_type")
    @Enumerated(value = EnumType.STRING)
    @Getter
    @Setter
    private TerritorialDivisionType territorialDivisionType;
}
