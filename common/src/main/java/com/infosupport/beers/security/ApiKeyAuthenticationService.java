package com.infosupport.beers.security;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * Disclaimer: this is not production-quality software. Hard-coding API keys in your code is a Very Bad Idea.
 * The code is here for demonstration purposes.
 */
public class ApiKeyAuthenticationService implements AuthenticationService {
    static final String ALLOWED_API_KEY = "a-s3cr3t-k3y";
    static final String CLIENT_NAME = "API Client";
    static final String HEADER_NAME = "X-API-KEY";

    @Override
    public Optional<Principal> determinePrincipal(final HttpServletRequest request) {
        var key = request.getHeader(HEADER_NAME);
        var isAllowed = ALLOWED_API_KEY.equals(key);

        return isAllowed ? Optional.of(() -> CLIENT_NAME) : Optional.empty();
    }

    @Override
    public Set<String> determineGroups(final Principal principal, final HttpServletRequest request) {
        if (principal == null) {
            return Collections.emptySet();
        } else {
            return Collections.singleton("api-client");
        }
    }
}
