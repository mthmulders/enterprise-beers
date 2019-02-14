package com.infosupport.beers.common;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import static java.util.logging.Level.SEVERE;

@Stateless
@Log
public class SystemInformationService {
    @Inject
    private ServletContext servletContext;

    private String hostName;

    public String getApplicationServer() {
        return servletContext.getServerInfo();
    }

    public Optional<String> getHostName() {
        return Optional.ofNullable(this.hostName);
    }

    @PostConstruct
    public void populateFields() {
        this.determineHostName();
    }

    private void determineHostName() {
        try {
            this.hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException uhe) {
            log.log(SEVERE, "Could not determine container name", uhe);
        }
    }

    public String getJavaRuntime() {
        var vendor = System.getProperty("java.vendor");
        var version = System.getProperty("java.version");
        return String.format("%s %s", vendor, version);
    }

    public String getOperatingSystem() {
        var arch = System.getProperty("os.arch");
        var name = System.getProperty("os.name");
        var version = System.getProperty("os.version");

        return String.format("%s %s (%s)", name, version, arch);
    }
}
