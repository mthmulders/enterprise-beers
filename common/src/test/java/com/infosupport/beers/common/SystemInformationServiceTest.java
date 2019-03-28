package com.infosupport.beers.common;

import org.junit.Before;
import org.junit.Test;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresent;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

public class SystemInformationServiceTest {
    private final SystemInformationService sut = new SystemInformationService();

    @Before
    public void setup() {
        sut.populateFields();
    }

    @Test
    public void getHostName() {
        assertThat(sut.getHostName(), isPresent());
    }

    @Test
    public void getJavaRuntime() {
        assertThat(sut.getJavaRuntime(), not(isEmptyString()));
    }

    @Test
    public void getOperatingSystem() {
        assertThat(sut.getOperatingSystem(), not(isEmptyString()));
    }
}