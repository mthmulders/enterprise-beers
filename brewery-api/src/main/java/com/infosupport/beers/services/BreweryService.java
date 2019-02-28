package com.infosupport.beers.services;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.infosupport.beers.model.Brewery;
import com.infosupport.beers.model.BreweryRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor(onConstructor = @__(@Inject))
@Stateless
public class BreweryService {
    private final BreweryRepository repository;

    public Collection<Brewery> getBreweries() {
        return repository.getBreweries();
    }

    public Optional<Brewery> findBrewery(@NonNull final UUID id) {
        return repository.findBrewery(id);
    }
}
