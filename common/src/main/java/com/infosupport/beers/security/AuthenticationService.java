package com.infosupport.beers.security;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;
import java.util.Set;

/**
 * Interface for classes that determine the {@link Principal} for an HTTP request, and assign groups to that principal.
 */
public interface AuthenticationService {
    /**
     * Determines the {@link Principal} that issues an HTTP request.
     * @param request The HTTP request
     * @return A {@link Principal}, or <code>null</code> if there is none.
     */
    Optional<Principal> determinePrincipal(final HttpServletRequest request);

    /**
     * Determine the groups that can be assigned to an incoming HTTP request.
     * @param principal The user {@link Principal} assigned to this request, or <code>null</code> if there is none.
     * @param request The HTTP request
     * @return The groups that can be assigned to the incoming HTTP request.
     */
    Set<String> determineGroups(final Principal principal, final HttpServletRequest request);
}
