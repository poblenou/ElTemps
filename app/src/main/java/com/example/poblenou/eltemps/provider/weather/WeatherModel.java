package com.example.poblenou.eltemps.provider.weather;

import android.support.annotation.Nullable;

import com.example.poblenou.eltemps.provider.base.BaseModel;

/**
 * Data model for the {@code weather} table.
 */
public interface WeatherModel extends BaseModel {

    /**
     * Get the {@code location_id} value.
     * Can be {@code null}.
     */
    @Nullable
    Long getLocationId();

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getDate();

    /**
     * Get the {@code short_desc} value.
     * Can be {@code null}.
     */
    @Nullable
    String getShortDesc();

    /**
     * Get the {@code min} value.
     * Can be {@code null}.
     */
    @Nullable
    Double getMin();

    /**
     * Get the {@code max} value.
     * Can be {@code null}.
     */
    @Nullable
    Double getMax();

    /**
     * Get the {@code humidity} value.
     * Can be {@code null}.
     */
    @Nullable
    Double getHumidity();

    /**
     * Get the {@code pressure} value.
     * Can be {@code null}.
     */
    @Nullable
    Double getPressure();

    /**
     * Get the {@code wind} value.
     * Can be {@code null}.
     */
    @Nullable
    Double getWind();

    /**
     * Get the {@code degrees} value.
     * Can be {@code null}.
     */
    @Nullable
    Double getDegrees();
}
