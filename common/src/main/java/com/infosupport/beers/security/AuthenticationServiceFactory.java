package com.infosupport.beers.security;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import lombok.extern.java.Log;

import static java.util.logging.Level.INFO;

/**
 * Factory for instances of {@link AuthenticationService}.
 */
@ApplicationScoped
@Log
public class AuthenticationServiceFactory {
    static final String PROP_NAME = "com.infosupport.beers.security.strategy";
    private static final String API_KEY = "API_KEY";
    private static final String KONG = "KONG";

    /**
     * Determines the strategy for authenticating incoming HTTP requests.
     * @return An instance of {@link AuthenticationService}.
     */
    @Produces
    @ApplicationScoped
    public AuthenticationService authenticationService() {
        var strategy = determineStrategy();
        log.log(INFO, "Using authentication strategy {0}", strategy);
        switch (strategy) {
            case API_KEY:
                return new ApiKeyAuthenticationService();
            case KONG:
                return new KongAuthenticationService();
            default:
                return new OpenAuthenticationService();
        }
    }

    private String determineStrategy() {
        return System.getProperty(PROP_NAME, "NONE");
    }
}
