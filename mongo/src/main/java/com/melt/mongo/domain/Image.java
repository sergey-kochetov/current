package com.melt.mongo.domain;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @Lob
    //@Type(type = "org.hibernate.type.ImageType")
    private byte[] image;

    public Image(String description, byte[] image) {
        this.description = description;
        this.image = image;
    }

    public Image() {
    }
}
