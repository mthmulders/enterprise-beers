package com.infosupport.beers.services;

import com.infosupport.beers.model.Beer;
import com.infosupport.beers.model.BeerRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor(onConstructor = @__(@Inject))
@Stateless
public class BeerService {
    private final BeerRepository repository;

    public Collection<Beer> findBeersByBrewery(@NonNull UUID breweryId) {
        return repository.findBeersByBrewery(breweryId);
    }

    public Optional<Beer> findBeer(@NonNull UUID id) {
        return repository.findBeer(id);
    }
}
