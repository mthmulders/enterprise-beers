package com.infosupport.beers.common;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

@Stateless
@Log
public class SystemInformationService {
    @Inject
    private ServletContext servletContext;

    private String containerName;

    private String hostName;

    public String getApplicationServer() {
        return servletContext.getServerInfo();
    }

    public Optional<String> getContainerName() {
        return Optional.ofNullable(this.containerName);
    }

    public Optional<String> getHostName() {
        return Optional.ofNullable(this.hostName);
    }

    @PostConstruct
    public void populateFields() {
        this.determineHostName();
        this.determineContainerName();

        if (this.hostName == null) {
            this.hostName = this.containerName;
            this.containerName = null;
        }
    }

    private void determineHostName() {
        var path = Path.of("/etc","docker-host-name");
        if (!Files.exists(path)) {
            log.log(INFO, "File {0} does not exist");
            return;
        } else if (!Files.isReadable(path)) {
            log.log(INFO, "File {0} is not readable");
            return;
        }
        try {
            var lines = Files.readAllLines(path, Charset.defaultCharset());
            this.hostName = lines.get(0);
        } catch (IOException ioe) {
            log.log(SEVERE, "Could not determine host name", ioe);
        }
    }

    private void determineContainerName() {
        try {
            this.containerName = InetAddress.getLocalHost().getHostName();
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
