package com.melt.mongo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "language")
public class LanguagePostgres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String creator;
    private String feature;

    public LanguagePostgres() {
    }

    public LanguagePostgres(String name, String creator, String feature) {
        this.name = name;
        this.creator = creator;
        this.feature = feature;
    }
}
