package com.infosupport.beers.model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.ejb.Stateless;

import lombok.NonNull;

import static com.infosupport.beers.model.Style.*;
import static java.util.stream.Collectors.toList;

@Stateless
public class InMemoryBeerRepository implements BeerRepository {
    private static Collection<Beer> ACHOUFFE_BEERS = Arrays.asList(
            Beer.builder()
                    .id(UUID.fromString("f03ba0de-af57-456c-8257-19f8be835547"))
                    .name("La Chouffe Blond")
                    .style(BELGIAN_STRONG_GOLDEN_ALE)
                    .abv(8.0)
                    .ibu(20.0)
                    .brewery(UUID.fromString("884343db-0a54-4add-b7ff-bb32dbad3731"))
                    .build(),
            Beer.builder()
                    .id(UUID.fromString("16887c30-b3c8-4d4a-91ac-6ff0c8426dda"))
                    .name("Houblon Chouffe")
                    .style(IPA)
                    .abv(9.0)
                    .ibu(47.0)
                    .brewery(UUID.fromString("884343db-0a54-4add-b7ff-bb32dbad3731"))
                    .build(),
            Beer.builder()
                    .id(UUID.fromString("fda5a7fb-7ea9-4c21-bb02-597e258cf329"))
                    .name("N'Ice Chouffe")
                    .style(WINTER_ALE)
                    .abv(10.0)
                    .ibu(25.0)
                    .brewery(UUID.fromString("884343db-0a54-4add-b7ff-bb32dbad3731"))
                    .build(),
            Beer.builder()
                    .id(UUID.fromString("42a3c974-a44d-40ae-a4e5-da9df3a4c43e"))
                    .name("Chouffe Soleil")
                    .style(BELGIAN_STRONG_GOLDEN_ALE)
                    .abv(6.0)
                    .ibu(20.0)
                    .brewery(UUID.fromString("884343db-0a54-4add-b7ff-bb32dbad3731"))
                    .build(),
            Beer.builder()
                    .id(UUID.fromString("03c1fbd5-1765-4257-87b7-6e85bfd5ccb8"))
                    .name("Mc Chouffe")
                    .style(BELGIAN_STRONG_DARK_ALE)
                    .abv(8.0)
                    .ibu(20.0)
                    .brewery(UUID.fromString("884343db-0a54-4add-b7ff-bb32dbad3731"))
                    .build()
    );
    private static Collection<Beer> BOSTEELS_BEERS = Arrays.asList(
            Beer.builder()
                    .id(UUID.fromString("57b3e86d-6615-49a1-9332-28860d5bae6d"))
                    .name("Tripel Karmeliet")
                    .style(BELGIAN_TRIPEL)
                    .abv(8.4)
                    .ibu(16.0)
                    .brewery(UUID.fromString("2c537ca9-6547-4a99-8fdd-1f28acf9b4f4"))
                    .build(),
            Beer.builder()
                    .id(UUID.fromString("2083cad7-dd43-4144-9a78-9db925370eac"))
                    .name("Pauwel Kwak")
                    .style(BELGIAN_STRONG_GOLDEN_ALE)
                    .abv(8.4)
                    .brewery(UUID.fromString("2c537ca9-6547-4a99-8fdd-1f28acf9b4f4"))
                    .build()
    );
    private static Collection<Beer> MAREDSOUS_BEERS = Arrays.asList(
            Beer.builder()
                    .id(UUID.fromString("8993da3e-93ef-4029-98ab-e63c0f584ff2"))
                    .name("Maredsous 6° Blonde")
                    .style(BELGIAN_BLONDE)
                    .abv(6.0)
                    .brewery(UUID.fromString("e409f98d-df4e-4ef7-9ca8-9c4f1ca669ef"))
                    .build(),
            Beer.builder()
                    .id(UUID.fromString("31da7f7d-b935-4cf2-9c0f-a4d97a5e41ac"))
                    .name("Maredsous 8° Brune")
                    .style(BELGIAN_BLONDE)
                    .abv(6.0)
                    .brewery(UUID.fromString("e409f98d-df4e-4ef7-9ca8-9c4f1ca669ef"))
                    .build(),
            Beer.builder()
                    .id(UUID.fromString("28e1c0df-9376-4ffb-aa31-47d02ad868bd"))
                    .name("Maredsous 10° Triple")
                    .style(BELGIAN_TRIPEL)
                    .abv(8.0)
                    .ibu(24.0)
                    .brewery(UUID.fromString("e409f98d-df4e-4ef7-9ca8-9c4f1ca669ef"))
                    .build()
    );

    private static final List<Beer> BEERS = new ArrayList<>();
    static {
        BEERS.addAll(ACHOUFFE_BEERS);
        BEERS.addAll(BOSTEELS_BEERS);
        BEERS.addAll(MAREDSOUS_BEERS);
    }

    @Override
    public Collection<Beer> findBeersByBrewery(@NonNull final UUID breweryId) {
        return BEERS.stream()
                .filter(beer -> breweryId.equals(beer.getBrewery()))
                .collect(toList());
    }

    @Override
    public Optional<Beer> findBeer(@NonNull final UUID id) {
        return BEERS.stream()
                .filter(beer -> id.equals(beer.getId()))
                .findFirst();
    }

}
