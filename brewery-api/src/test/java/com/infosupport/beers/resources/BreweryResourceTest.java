package com.infosupport.beers.resources;

import com.infosupport.beers.model.Brewery;
import com.infosupport.beers.services.BreweryService;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BreweryResourceTest {
    private final Brewery brewery = new Brewery(
            UUID.fromString("884343db-0a54-4add-b7ff-bb32dbad3731"),
            "Brasserie d'Achouffe",
            "Achouffe",
            "BE"
    );

    private BreweryService service = mock(BreweryService.class);

    private final BreweryResource sut = new BreweryResource();

    @Before
    public void setup() {
        sut.service = service;
    }

    @Test
    public void getBreweries_shouldReturnBreweries() {
        // Arrange
        when(service.getBreweries()).thenReturn(Collections.singleton(brewery));

        // Act
        final Collection<Brewery> breweries = sut.getBreweries();

        // Assert
        assertThat(breweries, containsInAnyOrder(brewery));
    }

    @Test
    public void getBreweryById_shouldReturnExistingBrewery() {
        // Arrange
        when(service.findBrewery(any(UUID.class))).thenReturn(Optional.of(brewery));

        // Act
        final Brewery brewery = sut.getBreweryById(this.brewery.getId());

        // Assert
        assertThat(brewery, is(this.brewery));
    }

    @Test(expected = NotFoundException.class)
    public void getBreweryById_shouldThrowExceptionWhenNotFound() {
        // Arrange
        when(service.findBrewery(any(UUID.class))).thenReturn(Optional.empty());

        // Act
        final Brewery brewery = sut.getBreweryById(UUID.fromString("2b174239-67c9-46a4-b149-8a8a0f0e9cd1"));
    }


}