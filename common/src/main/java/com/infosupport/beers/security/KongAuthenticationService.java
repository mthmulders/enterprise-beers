package com.infosupport.beers.security;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import static java.util.stream.Collectors.toSet;

public class KongAuthenticationService implements AuthenticationService {
    static final String GROUPS_HEADER_NAME = "X-Consumer-Groups";
    static final String USER_HEADER_NAME = "X-Consumer-Username";

    @Override
    public Optional<Principal> determinePrincipal(final HttpServletRequest request) {
        var username = request.getHeader(USER_HEADER_NAME);
        return Optional.ofNullable(username).map(value -> () -> value);
    }

    @Override
    public Set<String> determineGroups(final Principal principal, final HttpServletRequest request) {
        var groups = request.getHeader(GROUPS_HEADER_NAME);

        return Optional.ofNullable(groups)
                .map(this::splitGroups)
                .orElse(Collections.emptySet());
    }

    private Set<String> splitGroups(final String groups) {
        return Arrays.stream(groups.split(",")).map(String::trim).collect(toSet());
    }
}
