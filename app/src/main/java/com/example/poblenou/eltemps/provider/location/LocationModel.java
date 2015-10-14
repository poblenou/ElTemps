package com.example.poblenou.eltemps.provider.location;

import android.support.annotation.Nullable;

import com.example.poblenou.eltemps.provider.base.BaseModel;

/**
 * Data model for the {@code location} table.
 */
public interface LocationModel extends BaseModel {

    /**
     * Get the {@code location_setting} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getLocationSetting();

    /**
     * Get the {@code city_name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getCityName();

    /**
     * Get the {@code coord_lat} value.
     * Can be {@code null}.
     */
    @Nullable
    Double getCoordLat();

    /**
     * Get the {@code coord_long} value.
     * Can be {@code null}.
     */
    @Nullable
    Double getCoordLong();
}
