package com.infosupport.beers.common;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class DiagnosticsFilter implements ContainerResponseFilter {
    @Inject
    private SystemInformationService informationService;

    @Override
    public void filter(final ContainerRequestContext request, final ContainerResponseContext response) {
        response.getHeaders().putSingle("X-Application-Server", informationService.getApplicationServer());
        response.getHeaders().putSingle("X-Java-Runtime", informationService.getJavaRuntime());
        response.getHeaders().putSingle("X-Operating-System", informationService.getOperatingSystem());

        informationService.getHostName().ifPresent(val -> response.getHeaders().putSingle("X-Host-Name", val));
    }
}
