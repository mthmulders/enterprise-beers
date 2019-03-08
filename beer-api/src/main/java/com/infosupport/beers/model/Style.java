package com.infosupport.beers.model;

import javax.json.bind.annotation.JsonbTypeSerializer;

import com.infosupport.beers.common.DisplayableEnum;
import com.infosupport.beers.common.DisplayableEnumSerializer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@JsonbTypeSerializer(DisplayableEnumSerializer.class)
public enum Style implements DisplayableEnum {
    BELGIAN_BLONDE("Belgian Blonde"),
    BELGIAN_STRONG_DARK_ALE("Belgian Strong Dark Ale"),
    BELGIAN_STRONG_GOLDEN_ALE("Belgian Strong Golden Ale"),
    BELGIAN_TRIPEL("Belgian Tripel"),
    IPA("IPA"),
    WINTER_ALE("Winter Ale");

    private final String displayName;
}
