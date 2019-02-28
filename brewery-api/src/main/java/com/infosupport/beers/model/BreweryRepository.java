package com.infosupport.beers.model;

import lombok.NonNull;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface BreweryRepository {
    Collection<Brewery> getBreweries();
    Optional<Brewery> findBrewery(@NonNull final UUID id);
}
