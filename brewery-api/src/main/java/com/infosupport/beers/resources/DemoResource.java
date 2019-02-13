package com.infosupport.beers.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("demo")
public class DemoResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DemoEntity listBeers() {
        return new DemoEntity("example");
    }
}
