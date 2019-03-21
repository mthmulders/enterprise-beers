package com.infosupport.beers.services;

import com.infosupport.beers.model.Beer;
import com.infosupport.beers.model.BeerRepository;
import lombok.NonNull;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAnd;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BeerServiceTest {
    private final Beer beer = Beer.builder().id(UUID.randomUUID()).brewery(UUID.randomUUID()).build();
    private final BeerRepository repository = new BeerRepository() {
        @Override
        public Collection<Beer> findBeersByBrewery(@NonNull UUID breweryId) {
            return beer.getBrewery().equals(breweryId) ? Collections.singleton(beer) : Collections.emptySet();
        }

        @Override
        public Optional<Beer> findBeer(@NonNull UUID id) {
            return beer.getId().equals(id) ? Optional.of(beer) : Optional.empty();
        }
    };

    private final BeerService sut = new BeerService(repository);

    @Test
    public void findBeer_withExistingBeer_shouldReturnBeer() {
        // Arrange

        // Act
        var result = sut.findBeer(beer.getId());

        // Assert
        assertThat(result, isPresentAnd(is(beer)));
    }

    @Test
    public void findBeersByBrewery_withExistingBrewery_shouldReturnBeers() {
        // Arrange

        // Act
        var result = sut.findBeersByBrewery(beer.getBrewery());

        // Assert
        assertThat(result, hasItem(beer));
    }
}