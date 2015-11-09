package com.example.poblenou.eltemps.provider.weather;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.example.poblenou.eltemps.provider.base.AbstractCursor;
import com.example.poblenou.eltemps.provider.location.LocationColumns;

/**
 * Cursor wrapper for the {@code weather} table.
 */
public class WeatherCursor extends AbstractCursor implements WeatherModel {
    public WeatherCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(WeatherColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code location_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getLocationId() {
        Long res = getLongOrNull(WeatherColumns.LOCATION_ID);
        return res;
    }

    /**
     * Get the {@code location_setting} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getLocationLocationSetting() {
        Long res = getLongOrNull(LocationColumns.LOCATION_SETTING);
        return res;
    }

    /**
     * Get the {@code city_name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getLocationCityName() {
        String res = getStringOrNull(LocationColumns.CITY_NAME);
        return res;
    }

    /**
     * Get the {@code coord_lat} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getLocationCoordLat() {
        Double res = getDoubleOrNull(LocationColumns.COORD_LAT);
        return res;
    }

    /**
     * Get the {@code coord_long} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getLocationCoordLong() {
        Double res = getDoubleOrNull(LocationColumns.COORD_LONG);
        return res;
    }

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getDate() {
        Integer res = getIntegerOrNull(WeatherColumns.DATE);
        return res;
    }

    /**
     * Get the {@code short_desc} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getShortDesc() {
        String res = getStringOrNull(WeatherColumns.SHORT_DESC);
        return res;
    }

    /**
     * Get the {@code min} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getMin() {
        Double res = getDoubleOrNull(WeatherColumns.MIN);
        return res;
    }

    /**
     * Get the {@code max} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getMax() {
        Double res = getDoubleOrNull(WeatherColumns.MAX);
        return res;
    }

    /**
     * Get the {@code humidity} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getHumidity() {
        Double res = getDoubleOrNull(WeatherColumns.HUMIDITY);
        return res;
    }

    /**
     * Get the {@code pressure} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getPressure() {
        Double res = getDoubleOrNull(WeatherColumns.PRESSURE);
        return res;
    }

    /**
     * Get the {@code wind} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getWind() {
        Double res = getDoubleOrNull(WeatherColumns.WIND);
        return res;
    }

    /**
     * Get the {@code degrees} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getDegrees() {
        Double res = getDoubleOrNull(WeatherColumns.DEGREES);
        return res;
    }
}
