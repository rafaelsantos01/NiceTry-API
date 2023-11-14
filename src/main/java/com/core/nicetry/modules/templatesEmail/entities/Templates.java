package com.core.nicetry.modules.templatesEmail.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;


@Getter
@Setter

@Entity
@Table(name = "templates")
public class Templates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "template")
    private String template;

    @Column(name = "title")
    private String title;

    @Column(name = "template_type")
    private int templateType;

    @Column(name = "status")
    private boolean status;
}
