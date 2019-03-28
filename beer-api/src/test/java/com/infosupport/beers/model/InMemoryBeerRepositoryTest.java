package com.infosupport.beers.model;

import java.util.UUID;

import org.junit.Test;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresent;
import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class InMemoryBeerRepositoryTest {
    private InMemoryBeerRepository sut = new InMemoryBeerRepository();

    @Test
    public void findBeersByBrewery_shouldReturnBeersForAchouffe() {
        // Act
        var result = sut.findBeersByBrewery(UUID.fromString("884343db-0a54-4add-b7ff-bb32dbad3731"));

        // Assert
        assertThat(result, is(not(empty())));
    }

    @Test
    public void findBeersByBrewery_shouldReturnBeersForNonExistingBrewery() {
        // Act
        var result = sut.findBeersByBrewery(UUID.fromString("884343db-0a54-4add-b7ff-bb32d731bad3"));

        // Assert
        assertThat(result, is(empty()));
    }

    @Test
    public void findBeer_shouldReturnBeerForExistingBeer() {
        // Act
        var result = sut.findBeer(UUID.fromString("28e1c0df-9376-4ffb-aa31-47d02ad868bd"));

        // Assert
        assertThat(result, isPresent());
    }

    @Test
    public void findBeer_shouldReturnBeersForNonExistingBrewery() {
        // Act
        var result = sut.findBeer(UUID.fromString("884343db-0a54-4add-b7ff-bb32d731bad3"));

        // Assert
        assertThat(result, isEmpty());
    }
}