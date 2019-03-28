package com.infosupport.beers.common;

import javax.json.stream.JsonGenerator;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DisplayableEnumSerializerTest {
    static class Dummy implements DisplayableEnum {
        @Override
        public String getDisplayName() {
            return "example";
        }
    }

    private final DisplayableEnumSerializer sut = new DisplayableEnumSerializer();
    private final JsonGenerator generator = mock(JsonGenerator.class);

    @Test
    public void serialize_shouldUseResultOfGetDisplayName() {
        // Arrange
        var input = new Dummy();

        // Act
        sut.serialize(input, generator, null);

        // Assert
        verify(generator).write("example");
    }
}