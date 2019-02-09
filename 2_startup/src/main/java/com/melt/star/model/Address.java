package com.melt.star.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String country;
    private String city;
    private String street;
}
