package com.infosupport.beers.security;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ApplicationScoped
public class AuthenticationMechanism implements HttpAuthenticationMechanism {
    @Inject
    AuthenticationService authenticationService;

    @Override
    public AuthenticationStatus validateRequest(final HttpServletRequest request,
                                                final HttpServletResponse response,
                                                final HttpMessageContext context) {
        return authenticationService.determinePrincipal(request)
                .map(principal -> {
                    var groups = authenticationService.determineGroups(principal, request);
                    return context.notifyContainerAboutLogin(principal, groups);
                })
                .orElse(context.responseUnauthorized());
    }
}
