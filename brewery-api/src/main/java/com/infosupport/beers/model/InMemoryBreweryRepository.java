package com.infosupport.beers.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.ejb.Stateless;

import lombok.NonNull;

@Stateless
public class InMemoryBreweryRepository implements BreweryRepository {
    private static final List<Brewery> BREWERIES = Arrays.asList(
            new Brewery(UUID.fromString("884343db-0a54-4add-b7ff-bb32dbad3731"), "Brasserie d'Achouffe", "Achouffe", "BE"),
            new Brewery(UUID.fromString("f1002882-149f-40fc-abcd-f0def6a7763d"), "Jopen", "Haarlem", "NL"),
            new Brewery(UUID.fromString("487597eb-5fa9-46c1-aa34-49a66ad1d7e4"), "Duvel Moortgat", "Antwerpen", "BE"),
            new Brewery(UUID.fromString("2c537ca9-6547-4a99-8fdd-1f28acf9b4f4"), "Bosteels", "Buggenhout", "BE"),
            new Brewery(UUID.fromString("11cb03e7-6439-46d4-a141-577cda5ace36"), "Lupulus", "Bovigny", "BE"),
            new Brewery(UUID.fromString("2b174239-67c9-46a4-b149-8a8a0f0e9cd1"), "Hertog Jan", "Arcen", "NL"),
            new Brewery(UUID.fromString("9028c14e-7d3a-44e2-bbbb-c99120bfebcb"), "De Leckere", "De Meern", "NL"),
            new Brewery(UUID.fromString("e409f98d-df4e-4ef7-9ca8-9c4f1ca669ef"), "Abbaye de Maredsous", "Antwerpen", "BE")
    );

    @Override
    public Collection<Brewery> getBreweries() {
        return Collections.unmodifiableCollection(BREWERIES);
    }

    @Override
    public Optional<Brewery> findBrewery(@NonNull final UUID id) {
        return BREWERIES.stream()
                .filter(brewery -> id.equals(brewery.getId()))
                .findFirst();
    }
}
