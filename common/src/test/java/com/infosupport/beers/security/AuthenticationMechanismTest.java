package com.infosupport.beers.security;

import com.mockrunner.mock.web.MockHttpServletRequest;
import com.mockrunner.mock.web.MockHttpServletResponse;
import org.junit.Before;
import org.junit.Test;

import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class AuthenticationMechanismTest {
    private final AuthenticationMechanism sut = new AuthenticationMechanism();
    private final AuthenticationService authenticationService = mock(AuthenticationService.class);

    @Before
    public void setup() {
        sut.authenticationService = authenticationService;
    }

    @Test
    public void validateRequest_whenAuthenticated_shouldAssignPrincipalAndGroups() {
        // Arrange
        var context = mock(HttpMessageContext.class);
        var principal = mock(Principal.class);
        var groups = Collections.singleton("group");
        when(authenticationService.determinePrincipal(any())).thenReturn(Optional.of(principal));
        when(authenticationService.determineGroups(any(), any())).thenReturn(groups);

        // Act
        sut.validateRequest(new MockHttpServletRequest(), new MockHttpServletResponse(), context);

        // Assert
        verify(authenticationService).determineGroups(eq(principal), any());
        verify(context).notifyContainerAboutLogin(eq(principal), eq(groups));
    }

    @Test
    public void validateRequest_whenNotAuthenticated_shouldAssignNeitherPrincipalNorGroups() {
        // Arrange
        var context = mock(HttpMessageContext.class);
        when(authenticationService.determinePrincipal(any())).thenReturn(Optional.empty());

        // Act
        sut.validateRequest(new MockHttpServletRequest(), new MockHttpServletResponse(), context);

        // Assert
        verify(authenticationService, never()).determineGroups(any(), any());
        verify(context).responseUnauthorized();
    }
}