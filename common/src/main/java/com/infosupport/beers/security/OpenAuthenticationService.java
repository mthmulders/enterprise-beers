package com.infosupport.beers.security;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * Disclaimer: this is not production-quality software.
 * The code is here for demonstration purposes.
 */
public class OpenAuthenticationService implements AuthenticationService {
    static final String CLIENT_NAME = "API Client";

    public Optional<Principal> determinePrincipal(final HttpServletRequest request) {
        return Optional.of(() -> CLIENT_NAME);
    }

    public Set<String> determineGroups(final Principal principal, final HttpServletRequest request) {
        return Collections.singleton("api-client");
    }
}
