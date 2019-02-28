package com.infosupport.beers.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Brewery {
    private UUID id;
    private String name;
    private String location;
    private String country;
}
