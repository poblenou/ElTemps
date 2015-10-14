package com.example.poblenou.eltemps.provider.weather;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.poblenou.eltemps.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code weather} table.
 */
public class WeatherContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return WeatherColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where           The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable WeatherSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where           The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable WeatherSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public WeatherContentValues putLocationId(@Nullable Long value) {
        mContentValues.put(WeatherColumns.LOCATION_ID, value);
        return this;
    }

    public WeatherContentValues putLocationIdNull() {
        mContentValues.putNull(WeatherColumns.LOCATION_ID);
        return this;
    }

    public WeatherContentValues putDate(@Nullable Integer value) {
        mContentValues.put(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherContentValues putDateNull() {
        mContentValues.putNull(WeatherColumns.DATE);
        return this;
    }

    public WeatherContentValues putShortDesc(@Nullable String value) {
        mContentValues.put(WeatherColumns.SHORT_DESC, value);
        return this;
    }

    public WeatherContentValues putShortDescNull() {
        mContentValues.putNull(WeatherColumns.SHORT_DESC);
        return this;
    }

    public WeatherContentValues putMin(@Nullable Double value) {
        mContentValues.put(WeatherColumns.MIN, value);
        return this;
    }

    public WeatherContentValues putMinNull() {
        mContentValues.putNull(WeatherColumns.MIN);
        return this;
    }

    public WeatherContentValues putMax(@Nullable Double value) {
        mContentValues.put(WeatherColumns.MAX, value);
        return this;
    }

    public WeatherContentValues putMaxNull() {
        mContentValues.putNull(WeatherColumns.MAX);
        return this;
    }

    public WeatherContentValues putHumidity(@Nullable Double value) {
        mContentValues.put(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherContentValues putHumidityNull() {
        mContentValues.putNull(WeatherColumns.HUMIDITY);
        return this;
    }

    public WeatherContentValues putPressure(@Nullable Double value) {
        mContentValues.put(WeatherColumns.PRESSURE, value);
        return this;
    }

    public WeatherContentValues putPressureNull() {
        mContentValues.putNull(WeatherColumns.PRESSURE);
        return this;
    }

    public WeatherContentValues putWind(@Nullable Double value) {
        mContentValues.put(WeatherColumns.WIND, value);
        return this;
    }

    public WeatherContentValues putWindNull() {
        mContentValues.putNull(WeatherColumns.WIND);
        return this;
    }

    public WeatherContentValues putDegrees(@Nullable Double value) {
        mContentValues.put(WeatherColumns.DEGREES, value);
        return this;
    }

    public WeatherContentValues putDegreesNull() {
        mContentValues.putNull(WeatherColumns.DEGREES);
        return this;
    }
}
