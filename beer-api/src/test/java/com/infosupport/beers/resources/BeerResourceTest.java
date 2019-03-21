package com.infosupport.beers.resources;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import javax.ws.rs.NotFoundException;

import com.infosupport.beers.model.Beer;
import com.infosupport.beers.services.BeerService;
import org.junit.Before;
import org.junit.Test;

import static com.infosupport.beers.model.Style.BELGIAN_STRONG_GOLDEN_ALE;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BeerResourceTest {
    private final Beer beer = Beer.builder()
            .id(UUID.fromString("f03ba0de-af57-456c-8257-19f8be835547"))
            .name("La Chouffe Blond")
            .style(BELGIAN_STRONG_GOLDEN_ALE)
            .abv(8.0)
            .ibu(20.0)
            .brewery(UUID.fromString("884343db-0a54-4add-b7ff-bb32dbad3731"))
            .build();

    private BeerService service = mock(BeerService.class);

    private final BeerResource sut = new BeerResource();

    @Before
    public void setup() {
        sut.service = service;
    }

    @Test
    public void getBeerByBrewery_shouldReturnBeers() {
        // Arrange
        when(service.findBeersByBrewery(any())).thenReturn(Collections.singleton(this.beer));

        // Act
        final Collection<Beer> beers = sut.getBeerByBrewery(beer.getId());

        // Assert
        assertThat(beers, containsInAnyOrder(this.beer));
    }

    @Test
    public void getBeerById_shouldReturnExistingBrewery() {
        // Arrange
        when(service.findBeer(any())).thenReturn(Optional.of(beer));

        // Act
        final Beer beer = sut.getBeerById(this.beer.getId());

        // Assert
        assertThat(beer, is(this.beer));
    }

    @Test(expected = NotFoundException.class)
    public void getBeerById_shouldThrowExceptionWhenNotFound() {
        // Arrange
        when(service.findBeer(any())).thenReturn(Optional.empty());

        // Act
        sut.getBeerById(UUID.fromString("2b174239-67c9-46a4-b149-8a8a0f0e9cd1"));
    }

}