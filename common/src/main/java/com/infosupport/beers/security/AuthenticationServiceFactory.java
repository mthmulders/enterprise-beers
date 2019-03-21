package com.infosupport.beers.security;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Factory for instances of {@link AuthenticationService}.
 */
@ApplicationScoped
public class AuthenticationServiceFactory {
    static final String PROP_NAME = "com.infosupport.beers.security.strategy";
    private static final String API_KEY = "API_KEY";

    /**
     * Determines the strategy for authenticating incoming HTTP requests.
     * @return An instance of {@link AuthenticationService}.
     */
    @Produces
    @ApplicationScoped
    public AuthenticationService authenticationService() {
        switch (determineStrategy()) {
            case API_KEY:
                return new ApiKeyAuthenticationService();
            default:
                return new OpenAuthenticationService();
        }
    }

    private String determineStrategy() {
        return System.getProperty(PROP_NAME, "NONE");
    }
}
