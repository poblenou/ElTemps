package com.example.poblenou.eltemps.provider.location;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.example.poblenou.eltemps.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code location} table.
 */
public class LocationCursor extends AbstractCursor implements LocationModel {
    public LocationCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(LocationColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code location_setting} value.
     * Can be {@code null}.
     */
    @Nullable
    public Long getLocationSetting() {
        Long res = getLongOrNull(LocationColumns.LOCATION_SETTING);
        return res;
    }

    /**
     * Get the {@code city_name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getCityName() {
        String res = getStringOrNull(LocationColumns.CITY_NAME);
        return res;
    }

    /**
     * Get the {@code coord_lat} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getCoordLat() {
        Double res = getDoubleOrNull(LocationColumns.COORD_LAT);
        return res;
    }

    /**
     * Get the {@code coord_long} value.
     * Can be {@code null}.
     */
    @Nullable
    public Double getCoordLong() {
        Double res = getDoubleOrNull(LocationColumns.COORD_LONG);
        return res;
    }
}
