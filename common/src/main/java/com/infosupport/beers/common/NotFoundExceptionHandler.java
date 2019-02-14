package com.infosupport.beers.common;

import lombok.Getter;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * Converts {@link NotFoundException} to a simple JSON message.
 */
@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {
    @Getter
    public static class Message {
        private String message = "The requested entity does not exist";
    }

    private static final Message message = new Message();

    @Context
    private HttpHeaders headers;

    @Override
    public Response toResponse(final NotFoundException exception) {
        return Response.status(NOT_FOUND)
                .entity(message)
                .type(APPLICATION_JSON)
                .build();
    }
}
