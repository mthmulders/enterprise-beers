package com.infosupport.beers.resources;

import java.util.Collection;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.infosupport.beers.model.Brewery;
import com.infosupport.beers.services.BreweryService;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/brewery")
@RolesAllowed("api-client")
public class BreweryResource {
    @Inject
    BreweryService service;

    @GET
    @Produces(APPLICATION_JSON)
    public Collection<Brewery> getBreweries() {
        return service.getBreweries();
    }

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Brewery getBreweryById(@PathParam("id") final UUID id) {
        return service.findBrewery(id).orElseThrow(NotFoundException::new);
    }
}
