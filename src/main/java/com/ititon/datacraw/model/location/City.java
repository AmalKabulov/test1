package com.ititon.datacraw.model.location;

import com.ititon.datacraw.model.LongIdEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cities")
@ToString(callSuper = true, exclude = "districts")
@AllArgsConstructor
@NoArgsConstructor
public class City extends LongIdEntity {

    @Column(name = "name_en")
    @Getter
    @Setter
    private String nameEn;

    @Column(name = "name_native")
    @Getter
    @Setter
    private String nameNative;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Set<District> districts;


    @ManyToOne
    @JoinColumn(name = "adm_ter_div_id")
    @Getter
    @Setter
    private TerritorialDivision territorialDivision;
}
