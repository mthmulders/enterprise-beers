package com.infosupport.beers.security;

import org.junit.After;
import org.junit.Test;

import static com.infosupport.beers.security.AuthenticationServiceFactory.PROP_NAME;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AuthenticationServiceFactoryTest {
    private final AuthenticationServiceFactory sut = new AuthenticationServiceFactory();

    @After
    public void tearDown() {
        System.clearProperty(PROP_NAME);
    }

    @Test
    public void whenSystemPropertyAPI_KEY_shouldReturnApiKeyAuthenticationService() {
        // Arrange
        System.setProperty(PROP_NAME, "API_KEY");

        // Act
        var result = sut.authenticationService();

        // Assert
        assertThat(result, is(instanceOf(ApiKeyAuthenticationService.class)));
    }

    @Test
    public void whenSystemPropertyOPEN_shouldReturnOpenAuthenticationService() {
        // Arrange
        System.setProperty(PROP_NAME, "NONE");

        // Act
        var result = sut.authenticationService();

        // Assert
        assertThat(result, is(instanceOf(OpenAuthenticationService.class)));
    }

    @Test
    public void whenSystemPropertyKONG_shouldReturnKongAuthenticationService() {
        // Arrange
        System.setProperty(PROP_NAME, "KONG");

        // Act
        var result = sut.authenticationService();

        // Assert
        assertThat(result, is(instanceOf(KongAuthenticationService.class)));
    }

    @Test
    public void whenSystemPropertyEmpty_shouldReturnOpenAuthenticationService() {
        // Arrange

        // Act
        var result = sut.authenticationService();

        // Assert
        assertThat(result, is(instanceOf(OpenAuthenticationService.class)));
    }
}