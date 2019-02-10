package com.melt.star.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Buyer {
    private Long id;
    private String name;
    private String country;

    private Integer token;

    public Buyer(Long id, String name, String country, Integer token) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.token = token;
    }

    public Buyer() {
    }
}
