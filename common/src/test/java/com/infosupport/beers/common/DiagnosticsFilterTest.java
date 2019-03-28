package com.infosupport.beers.common;

import java.util.Arrays;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DiagnosticsFilterTest {
    private final static String ENDPOINT_ADDRESS = "local://dummy";

    private final DiagnosticsFilter sut = new DiagnosticsFilter();
    private final SystemInformationService systemInformationService = mock(SystemInformationService.class);

    private Server server;

    @Path("/")
    public static class DummyResource {
        @GET
        @Produces(APPLICATION_JSON)
        public String dummy() {
            return "dummy";
        }
    }

    @Before
    public void startServer() {
        when(systemInformationService.getApplicationServer()).thenReturn("AppSrv 0.1");
        when(systemInformationService.getJavaRuntime()).thenReturn("Java 23");
        when(systemInformationService.getOperatingSystem()).thenReturn("Windows 3.1");
        when(systemInformationService.getHostName()).thenReturn(Optional.of("localhost"));

        sut.informationService = systemInformationService;

        var serverFactory = new JAXRSServerFactoryBean();
        serverFactory.setResourceClasses(DummyResource.class);

        serverFactory.setProviders(Arrays.asList(sut));

        serverFactory.setResourceProvider(DummyResource.class,
                new SingletonResourceProvider(new DummyResource(), true));
        serverFactory.setAddress(ENDPOINT_ADDRESS);

        this.server = serverFactory.create();
    }

    @After
    public void destroyServer() {
        server.stop();
        server.destroy();
    }

    @Test
    public void shouldIncludeApplicationServer() {
        // Arrange
        var client = WebClient.create(ENDPOINT_ADDRESS).accept(APPLICATION_JSON);

        // Act
        var response = client.get(Response.class);

        // Assert
        assertThat(response.getHeaders().getFirst("X-Application-Server"), is("AppSrv 0.1"));
    }

    @Test
    public void shouldIncludeJavaRuntime() {
        // Arrange
        var client = WebClient.create(ENDPOINT_ADDRESS).accept(APPLICATION_JSON);

        // Act
        var response = client.get(Response.class);

        // Assert
        assertThat(response.getHeaders().getFirst("X-Java-Runtime"), is("Java 23"));
    }

    @Test
    public void shouldIncludeOperatingSystem() {
        // Arrange
        var client = WebClient.create(ENDPOINT_ADDRESS).accept(APPLICATION_JSON);

        // Act
        var response = client.get(Response.class);

        // Assert
        assertThat(response.getHeaders().getFirst("X-Operating-System"), is("Windows 3.1"));
    }

    @Test
    public void shouldIncludeHostName() {
        // Arrange
        var client = WebClient.create(ENDPOINT_ADDRESS).accept(APPLICATION_JSON);

        // Act
        var response = client.get(Response.class);

        // Assert
        assertThat(response.getHeaders().getFirst("X-Host-Name"), is("localhost"));
    }
}