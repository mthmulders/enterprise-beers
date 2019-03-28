package com.infosupport.beers.common;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import lombok.extern.java.Log;

@Log
@Provider
public class DiagnosticsFilter implements ContainerResponseFilter {
    @Inject
    SystemInformationService informationService;

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) {
        responseContext.getHeaders().putSingle("X-Application-Server", informationService.getApplicationServer());
        responseContext.getHeaders().putSingle("X-Java-Runtime", informationService.getJavaRuntime());
        responseContext.getHeaders().putSingle("X-Operating-System", informationService.getOperatingSystem());

        informationService.getHostName().ifPresent(val -> responseContext.getHeaders().putSingle("X-Host-Name", val));
    }
}
