package com.example.poblenou.eltemps.provider.location;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.poblenou.eltemps.provider.base.AbstractSelection;

/**
 * Selection for the {@code location} table.
 */
public class LocationSelection extends AbstractSelection<LocationSelection> {
    @Override
    protected Uri baseUri() {
        return LocationColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection      A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code LocationCursor} object, which is positioned before the first entry, or null.
     */
    public LocationCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new LocationCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public LocationCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context    The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code LocationCursor} object, which is positioned before the first entry, or null.
     */
    public LocationCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new LocationCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public LocationCursor query(Context context) {
        return query(context, null);
    }


    public LocationSelection id(long... value) {
        addEquals("location." + LocationColumns._ID, toObjectArray(value));
        return this;
    }

    public LocationSelection idNot(long... value) {
        addNotEquals("location." + LocationColumns._ID, toObjectArray(value));
        return this;
    }

    public LocationSelection orderById(boolean desc) {
        orderBy("location." + LocationColumns._ID, desc);
        return this;
    }

    public LocationSelection orderById() {
        return orderById(false);
    }

    public LocationSelection locationSetting(Long... value) {
        addEquals(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public LocationSelection locationSettingNot(Long... value) {
        addNotEquals(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public LocationSelection locationSettingGt(long value) {
        addGreaterThan(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public LocationSelection locationSettingGtEq(long value) {
        addGreaterThanOrEquals(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public LocationSelection locationSettingLt(long value) {
        addLessThan(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public LocationSelection locationSettingLtEq(long value) {
        addLessThanOrEquals(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public LocationSelection orderByLocationSetting(boolean desc) {
        orderBy(LocationColumns.LOCATION_SETTING, desc);
        return this;
    }

    public LocationSelection orderByLocationSetting() {
        orderBy(LocationColumns.LOCATION_SETTING, false);
        return this;
    }

    public LocationSelection cityName(String... value) {
        addEquals(LocationColumns.CITY_NAME, value);
        return this;
    }

    public LocationSelection cityNameNot(String... value) {
        addNotEquals(LocationColumns.CITY_NAME, value);
        return this;
    }

    public LocationSelection cityNameLike(String... value) {
        addLike(LocationColumns.CITY_NAME, value);
        return this;
    }

    public LocationSelection cityNameContains(String... value) {
        addContains(LocationColumns.CITY_NAME, value);
        return this;
    }

    public LocationSelection cityNameStartsWith(String... value) {
        addStartsWith(LocationColumns.CITY_NAME, value);
        return this;
    }

    public LocationSelection cityNameEndsWith(String... value) {
        addEndsWith(LocationColumns.CITY_NAME, value);
        return this;
    }

    public LocationSelection orderByCityName(boolean desc) {
        orderBy(LocationColumns.CITY_NAME, desc);
        return this;
    }

    public LocationSelection orderByCityName() {
        orderBy(LocationColumns.CITY_NAME, false);
        return this;
    }

    public LocationSelection coordLat(Double... value) {
        addEquals(LocationColumns.COORD_LAT, value);
        return this;
    }

    public LocationSelection coordLatNot(Double... value) {
        addNotEquals(LocationColumns.COORD_LAT, value);
        return this;
    }

    public LocationSelection coordLatGt(double value) {
        addGreaterThan(LocationColumns.COORD_LAT, value);
        return this;
    }

    public LocationSelection coordLatGtEq(double value) {
        addGreaterThanOrEquals(LocationColumns.COORD_LAT, value);
        return this;
    }

    public LocationSelection coordLatLt(double value) {
        addLessThan(LocationColumns.COORD_LAT, value);
        return this;
    }

    public LocationSelection coordLatLtEq(double value) {
        addLessThanOrEquals(LocationColumns.COORD_LAT, value);
        return this;
    }

    public LocationSelection orderByCoordLat(boolean desc) {
        orderBy(LocationColumns.COORD_LAT, desc);
        return this;
    }

    public LocationSelection orderByCoordLat() {
        orderBy(LocationColumns.COORD_LAT, false);
        return this;
    }

    public LocationSelection coordLong(Double... value) {
        addEquals(LocationColumns.COORD_LONG, value);
        return this;
    }

    public LocationSelection coordLongNot(Double... value) {
        addNotEquals(LocationColumns.COORD_LONG, value);
        return this;
    }

    public LocationSelection coordLongGt(double value) {
        addGreaterThan(LocationColumns.COORD_LONG, value);
        return this;
    }

    public LocationSelection coordLongGtEq(double value) {
        addGreaterThanOrEquals(LocationColumns.COORD_LONG, value);
        return this;
    }

    public LocationSelection coordLongLt(double value) {
        addLessThan(LocationColumns.COORD_LONG, value);
        return this;
    }

    public LocationSelection coordLongLtEq(double value) {
        addLessThanOrEquals(LocationColumns.COORD_LONG, value);
        return this;
    }

    public LocationSelection orderByCoordLong(boolean desc) {
        orderBy(LocationColumns.COORD_LONG, desc);
        return this;
    }

    public LocationSelection orderByCoordLong() {
        orderBy(LocationColumns.COORD_LONG, false);
        return this;
    }
}
