package com.example.poblenou.eltemps.provider.location;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.poblenou.eltemps.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code location} table.
 */
public class LocationContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return LocationColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where           The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable LocationSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where           The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable LocationSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public LocationContentValues putLocationSetting(@Nullable Long value) {
        mContentValues.put(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public LocationContentValues putLocationSettingNull() {
        mContentValues.putNull(LocationColumns.LOCATION_SETTING);
        return this;
    }

    public LocationContentValues putCityName(@Nullable String value) {
        mContentValues.put(LocationColumns.CITY_NAME, value);
        return this;
    }

    public LocationContentValues putCityNameNull() {
        mContentValues.putNull(LocationColumns.CITY_NAME);
        return this;
    }

    public LocationContentValues putCoordLat(@Nullable Double value) {
        mContentValues.put(LocationColumns.COORD_LAT, value);
        return this;
    }

    public LocationContentValues putCoordLatNull() {
        mContentValues.putNull(LocationColumns.COORD_LAT);
        return this;
    }

    public LocationContentValues putCoordLong(@Nullable Double value) {
        mContentValues.put(LocationColumns.COORD_LONG, value);
        return this;
    }

    public LocationContentValues putCoordLongNull() {
        mContentValues.putNull(LocationColumns.COORD_LONG);
        return this;
    }
}
