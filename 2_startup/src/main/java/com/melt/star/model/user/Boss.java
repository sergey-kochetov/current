package com.melt.star.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class Boss {

    private Long id;
    @JsonProperty("name")
    private String name;
    private Integer power;
}
