package com.ititon.datacraw.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "legal_statuses")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true, exclude = "patents")
public class LegalStatus extends LongIdEntity{

    @Column(name = "p_status")
    @Getter
    @Setter
    private String status;

    @OneToMany(mappedBy = "legalStatus")
    @Getter
    @Setter
    private Set<Patent> patents = new HashSet<>();
}
