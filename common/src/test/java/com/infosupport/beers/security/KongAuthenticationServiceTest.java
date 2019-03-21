package com.infosupport.beers.security;

import com.mockrunner.mock.web.MockHttpServletRequest;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Test;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresent;
import static com.infosupport.beers.security.KongAuthenticationService.GROUPS_HEADER_NAME;
import static com.infosupport.beers.security.KongAuthenticationService.USER_HEADER_NAME;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class KongAuthenticationServiceTest {
    private KongAuthenticationService sut = new KongAuthenticationService();

    @Test
    public void determinePrincipal_withUsername_shouldCopyUsername() {
        // Arrange
        var request = new MockHttpServletRequest();
        request.addHeader(USER_HEADER_NAME, "some-user");

        // Act
        var result = sut.determinePrincipal(request);

        // Assert
        assertThat(result, isPresent());
        assertThat(result.get().getName(), is("some-user"));
    }

    @Test
    public void determineGroups_withoutPrincipal_shouldReturnEmptySet() {
        // Act
        var result = sut.determineGroups(null, new MockHttpServletRequest());

        // Assert
        assertThat(result, is(empty()));
    }

    @Test
    public void determineGroups_withSingleGroup_shouldCopyGroup() {
        // Arrange
        var request = new MockHttpServletRequest();
        request.addHeader(GROUPS_HEADER_NAME, "api-client");

        // Act
        var result = sut.determineGroups(() -> "", request);

        // Assert
        assertThat(result, hasItems("api-client"));
        assertThat(result, hasSize(1));
    }


    @Test
    public void determineGroups_withMultipleGroups_shouldCopyGroups() {
        // Arrange
        var request = new MockHttpServletRequest();
        request.addHeader(GROUPS_HEADER_NAME, "api-client, something-else");

        // Act
        var result = sut.determineGroups(() -> "", request);

        // Assert
        assertThat(result, hasItems("api-client"));
        assertThat(result, hasItems("something-else"));
        assertThat(result, hasSize(2));
    }
}