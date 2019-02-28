package com.infosupport.beers.services;

import com.infosupport.beers.model.Brewery;
import com.infosupport.beers.model.BreweryRepository;
import lombok.NonNull;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresentAndIs;

public class BreweryServiceTest {
    private final Brewery brewery = new Brewery(
            UUID.fromString("884343db-0a54-4add-b7ff-bb32dbad3731"),
            "Brasserie d'Achouffe",
            "Achouffe",
            "BE"
    );
    private final List<Brewery> breweries = Collections.singletonList(brewery);
    private final BreweryRepository repository = new BreweryRepository() {
        @Override
        public Collection<Brewery> getBreweries() {
            return Collections.unmodifiableCollection(breweries);
        }

        @Override
        public Optional<Brewery> findBrewery(@NonNull UUID id) {
            return brewery.getId().equals(id) ? Optional.of(brewery) : Optional.empty();
        }
    };
    private final BreweryService sut = new BreweryService(repository);

    @Test
    public void getBreweries_shouldReturnBreweries() {
        // Act
        final Collection<Brewery> breweries = sut.getBreweries();

        // Assert
        assertThat(breweries, containsInAnyOrder(brewery));
    }

    @Test
    public void getBrewery_shouldReturnExistingBrewery() {
        // Act
        final Optional<Brewery> brewery = sut.findBrewery(this.brewery.getId());


        // Assert
        assertThat(brewery, isPresentAndIs(this.brewery));
    }

    @Test
    public void getBrewery_shouldNotReturnNonExistingBrewery() {
        // Act
        final Optional<Brewery> brewery = sut.findBrewery(UUID.fromString("2b174239-67c9-46a4-b149-8a8a0f0e9cd1"));


        // Assert
        assertThat(brewery, isEmpty());
    }
}