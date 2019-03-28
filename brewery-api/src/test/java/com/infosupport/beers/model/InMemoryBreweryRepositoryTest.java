package com.infosupport.beers.model;

import org.junit.Test;

import java.util.UUID;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresent;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.*;

public class InMemoryBreweryRepositoryTest {
    private InMemoryBreweryRepository sut = new InMemoryBreweryRepository();

    @Test
    public void getBreweries() {
        // Act
        var result = sut.getBreweries();

        // Assert
        assertThat(result, not(empty()));
    }

    @Test
    public void findBrewery_existingBrewery() {
        // Act
        var result = sut.findBrewery(UUID.fromString("884343db-0a54-4add-b7ff-bb32dbad3731"));

        // Assert
        assertThat(result, isPresent());
    }

    @Test
    public void findBrewery_nonExistingBrewery() {
        // Act
        var result = sut.findBrewery(UUID.fromString("884343db-0a54-4add-b7ff-bb32dd3731ba"));

        // Assert
        assertThat(result, isEmpty());
    }
}