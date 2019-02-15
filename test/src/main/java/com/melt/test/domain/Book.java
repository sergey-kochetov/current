package com.melt.test.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String author;

    private Timestamp createTime;
    private Timestamp updateTime;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public Book(Long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
        Timestamp time = new Timestamp(System.currentTimeMillis());
        this.createTime = time;
        this.updateTime = time;
    }

    public Book() {
    }
}
