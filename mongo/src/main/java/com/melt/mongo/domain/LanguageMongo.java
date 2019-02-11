package com.melt.mongo.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "language")
@Data
public class LanguageMongo {

    @Id
    private String id;

    private String name;
    private String creator;
    private String feature;

    public LanguageMongo() {
    }

    public LanguageMongo(String name, String creator, String feature) {
        this.name = name;
        this.creator = creator;
        this.feature = feature;
    }
}
