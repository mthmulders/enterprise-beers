package com.infosupport.beers.common;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

/**
 * Custom JSON Binding {@link JsonbSerializer} for Java enums that implement {@link DisplayableEnum}.
 */
public class DisplayableEnumSerializer implements JsonbSerializer<DisplayableEnum> {
    @Override
    public void serialize(final DisplayableEnum obj, final JsonGenerator generator, final SerializationContext ctx) {
        generator.write(obj.getDisplayName());
    }
}
