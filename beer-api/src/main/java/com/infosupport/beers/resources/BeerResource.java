package com.infosupport.beers.resources;

import com.infosupport.beers.model.Beer;
import com.infosupport.beers.services.BeerService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Collection;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api")
@Singleton
@RolesAllowed("api-client")
public class BeerResource {
    @Inject
    BeerService service;

    @GET
    @Path("/brewery/{breweryId}/beers")
    @Produces(APPLICATION_JSON)
    public Collection<Beer> getBeerByBrewery(@PathParam("breweryId") final UUID id) {
        return service.findBeersByBrewery(id);
    }

    @GET
    @Path("/beer/{id}")
    @Produces(APPLICATION_JSON)
    public Beer getBeerById(@PathParam("id") final UUID id) {
        return service.findBeer(id).orElseThrow(NotFoundException::new);
    }
}
