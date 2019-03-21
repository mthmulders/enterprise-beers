package com.infosupport.beers.security;

import java.util.Set;

import com.mockrunner.mock.web.MockHttpServletRequest;
import org.junit.Test;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresent;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static com.infosupport.beers.security.ApiKeyAuthenticationService.ALLOWED_API_KEY;
import static com.infosupport.beers.security.ApiKeyAuthenticationService.CLIENT_NAME;
import static com.infosupport.beers.security.ApiKeyAuthenticationService.HEADER_NAME;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class ApiKeyAuthenticationServiceTest {
    private ApiKeyAuthenticationService sut = new ApiKeyAuthenticationService();

    @Test
    public void determinePrincipal_withoutHeader_shouldReturnEmpty() {
        // Act
        var result = sut.determinePrincipal(new MockHttpServletRequest());

        // Assert
        assertThat(result, is(isEmpty()));
    }

    @Test
    public void determinePrincipal_withHeaderIncorrectValue_shouldReturnEmpty() {
        // Arrange
        var request = new MockHttpServletRequest();
        request.addHeader(HEADER_NAME, "foo-bar-baz");

        // Act
        var result = sut.determinePrincipal(request);

        // Assert
        assertThat(result, is(isEmpty()));
    }

    @Test
    public void determinePrincipal_withHeaderCorrectValue_shouldReturnPrincipal() {
        // Arrange
        var request = new MockHttpServletRequest();
        request.addHeader(HEADER_NAME, ALLOWED_API_KEY);

        // Act
        var result = sut.determinePrincipal(request);

        // Assert
        assertThat(result, is(isPresent()));
        assertThat(result.get().getName(), is(CLIENT_NAME));
    }

    @Test
    public void determineGroups_withoutPrincipal_shouldReturnEmptySet() {
        // Act
        var result = sut.determineGroups(null, new MockHttpServletRequest());

        // Assert
        assertThat(result, is(empty()));
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