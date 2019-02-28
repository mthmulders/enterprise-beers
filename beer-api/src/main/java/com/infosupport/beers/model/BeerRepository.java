package com.infosupport.beers.model;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import lombok.NonNull;

public interface BeerRepository {
    Collection<Beer> findBeersByBrewery(@NonNull final UUID breweryId);
    Optional<Beer> findBeer(@NonNull final UUID id);
}
