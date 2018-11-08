package com.ititon.datacraw.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patents")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Patent extends LongIdEntity {

    @Column(name = "name_en")
    @Getter
    @Setter
    private String nameEn;

    @Column(name = "name_native")
    @Getter
    @Setter
    private String nameNative;

    @Column(name = "application_num")
    @Getter
    @Setter
    private String applicationNum;


    @Column(name = "application_num_full    ")
    @Getter
    @Setter
    private String applicationFullNum;

    @Column(name = "application_date")
    @Getter
    @Setter
    private LocalDate applicationDate;

    @Column(name = "publication_num")
    @Getter
    @Setter
    private String publicationNum;

    @Column(name = "publication_date")
    @Getter
    @Setter
    private LocalDate publicationDate;

    @Column(name = "la_num")
    @Getter
    @Setter
    private String laNum;

    @Column(name = "priority_num")
    @Getter
    @Setter
    private String priorityNum;

    @Column(name = "priority_date")
    @Getter
    @Setter
    private LocalDate priorityDate;

    @Column(name = "cpc_num")
    @Getter
    @Setter
    private String cpcNum;

    @Column(name = "source_of")
    @Getter
    @Setter
    private String sourceOf;

    @Column(name = "p_description")
    @Getter
    @Setter
    private String description;

    @Column(name = "ipc_num")
    @Getter
    @Setter
    private String ipcNum;

    @Column(name = "image_path")
    @Getter
    @Setter
    private String imagePath;

    @Column(name = "leagal_status_added")
    @Getter
    @Setter
    private LocalDate legalStatusAdded;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    @Getter
    @Setter
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "holder_id")
    @Getter
    @Setter
    private Holder holder;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    @Getter
    @Setter
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    @Getter
    @Setter
    private Agency agency;


    @ManyToOne
    @JoinColumn(name = "legal_status_id")
    @Getter
    @Setter
    private LegalStatus legalStatus;


    @ManyToOne
    @JoinColumn(name = "patent_type_id")
    @Getter
    @Setter
    private PatentType patentType;

}
