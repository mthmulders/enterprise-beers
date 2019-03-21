package com.infosupport.beers.security;

import com.mockrunner.mock.web.MockHttpServletRequest;
import org.junit.Test;

import java.util.Set;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresent;
import static com.infosupport.beers.security.OpenAuthenticationService.CLIENT_NAME;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class OpenAuthenticationServiceTest {
    private final OpenAuthenticationService sut = new OpenAuthenticationService();

    @Test
    public void determinePrincipal_shouldReturnEmpty() {
        // Act
        var result = sut.determinePrincipal(new MockHttpServletRequest());

        // Assert
        assertThat(result, is(isPresent()));
        assertThat(result.get().getName(), is(CLIENT_NAME));
    }

    @Test
    public void determineGroups_withPrincipal_shouldReturnEmptySet() {
        // Arrange

        // Act
        final Set<String> result = sut.determineGroups(() -> "", new MockHttpServletRequest());

        // Assert
        assertThat(result, hasItems("api-client"));
    }
}