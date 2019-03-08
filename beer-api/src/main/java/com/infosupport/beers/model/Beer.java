package com.infosupport.beers.model;

import java.util.UUID;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Beer {
    private UUID id;
    private String name;
    private Style style;
    private Double abv;
    private Double ibu;
    private UUID brewery;
}
