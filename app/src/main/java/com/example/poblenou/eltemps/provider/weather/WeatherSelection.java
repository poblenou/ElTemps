package com.example.poblenou.eltemps.provider.weather;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.poblenou.eltemps.provider.base.AbstractSelection;
import com.example.poblenou.eltemps.provider.location.LocationColumns;

/**
 * Selection for the {@code weather} table.
 */
public class WeatherSelection extends AbstractSelection<WeatherSelection> {
    @Override
    protected Uri baseUri() {
        return WeatherColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection      A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code WeatherCursor} object, which is positioned before the first entry, or null.
     */
    public WeatherCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new WeatherCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public WeatherCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context    The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code WeatherCursor} object, which is positioned before the first entry, or null.
     */
    public WeatherCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new WeatherCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public WeatherCursor query(Context context) {
        return query(context, null);
    }


    public WeatherSelection id(long... value) {
        addEquals("weather." + WeatherColumns._ID, toObjectArray(value));
        return this;
    }

    public WeatherSelection idNot(long... value) {
        addNotEquals("weather." + WeatherColumns._ID, toObjectArray(value));
        return this;
    }

    public WeatherSelection orderById(boolean desc) {
        orderBy("weather." + WeatherColumns._ID, desc);
        return this;
    }

    public WeatherSelection orderById() {
        return orderById(false);
    }

    public WeatherSelection locationId(Long... value) {
        addEquals(WeatherColumns.LOCATION_ID, value);
        return this;
    }

    public WeatherSelection locationIdNot(Long... value) {
        addNotEquals(WeatherColumns.LOCATION_ID, value);
        return this;
    }

    public WeatherSelection locationIdGt(long value) {
        addGreaterThan(WeatherColumns.LOCATION_ID, value);
        return this;
    }

    public WeatherSelection locationIdGtEq(long value) {
        addGreaterThanOrEquals(WeatherColumns.LOCATION_ID, value);
        return this;
    }

    public WeatherSelection locationIdLt(long value) {
        addLessThan(WeatherColumns.LOCATION_ID, value);
        return this;
    }

    public WeatherSelection locationIdLtEq(long value) {
        addLessThanOrEquals(WeatherColumns.LOCATION_ID, value);
        return this;
    }

    public WeatherSelection orderByLocationId(boolean desc) {
        orderBy(WeatherColumns.LOCATION_ID, desc);
        return this;
    }

    public WeatherSelection orderByLocationId() {
        orderBy(WeatherColumns.LOCATION_ID, false);
        return this;
    }

    public WeatherSelection locationLocationSetting(Long... value) {
        addEquals(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public WeatherSelection locationLocationSettingNot(Long... value) {
        addNotEquals(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public WeatherSelection locationLocationSettingGt(long value) {
        addGreaterThan(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public WeatherSelection locationLocationSettingGtEq(long value) {
        addGreaterThanOrEquals(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public WeatherSelection locationLocationSettingLt(long value) {
        addLessThan(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public WeatherSelection locationLocationSettingLtEq(long value) {
        addLessThanOrEquals(LocationColumns.LOCATION_SETTING, value);
        return this;
    }

    public WeatherSelection orderByLocationLocationSetting(boolean desc) {
        orderBy(LocationColumns.LOCATION_SETTING, desc);
        return this;
    }

    public WeatherSelection orderByLocationLocationSetting() {
        orderBy(LocationColumns.LOCATION_SETTING, false);
        return this;
    }

    public WeatherSelection locationCityName(String... value) {
        addEquals(LocationColumns.CITY_NAME, value);
        return this;
    }

    public WeatherSelection locationCityNameNot(String... value) {
        addNotEquals(LocationColumns.CITY_NAME, value);
        return this;
    }

    public WeatherSelection locationCityNameLike(String... value) {
        addLike(LocationColumns.CITY_NAME, value);
        return this;
    }

    public WeatherSelection locationCityNameContains(String... value) {
        addContains(LocationColumns.CITY_NAME, value);
        return this;
    }

    public WeatherSelection locationCityNameStartsWith(String... value) {
        addStartsWith(LocationColumns.CITY_NAME, value);
        return this;
    }

    public WeatherSelection locationCityNameEndsWith(String... value) {
        addEndsWith(LocationColumns.CITY_NAME, value);
        return this;
    }

    public WeatherSelection orderByLocationCityName(boolean desc) {
        orderBy(LocationColumns.CITY_NAME, desc);
        return this;
    }

    public WeatherSelection orderByLocationCityName() {
        orderBy(LocationColumns.CITY_NAME, false);
        return this;
    }

    public WeatherSelection locationCoordLat(Double... value) {
        addEquals(LocationColumns.COORD_LAT, value);
        return this;
    }

    public WeatherSelection locationCoordLatNot(Double... value) {
        addNotEquals(LocationColumns.COORD_LAT, value);
        return this;
    }

    public WeatherSelection locationCoordLatGt(double value) {
        addGreaterThan(LocationColumns.COORD_LAT, value);
        return this;
    }

    public WeatherSelection locationCoordLatGtEq(double value) {
        addGreaterThanOrEquals(LocationColumns.COORD_LAT, value);
        return this;
    }

    public WeatherSelection locationCoordLatLt(double value) {
        addLessThan(LocationColumns.COORD_LAT, value);
        return this;
    }

    public WeatherSelection locationCoordLatLtEq(double value) {
        addLessThanOrEquals(LocationColumns.COORD_LAT, value);
        return this;
    }

    public WeatherSelection orderByLocationCoordLat(boolean desc) {
        orderBy(LocationColumns.COORD_LAT, desc);
        return this;
    }

    public WeatherSelection orderByLocationCoordLat() {
        orderBy(LocationColumns.COORD_LAT, false);
        return this;
    }

    public WeatherSelection locationCoordLong(Double... value) {
        addEquals(LocationColumns.COORD_LONG, value);
        return this;
    }

    public WeatherSelection locationCoordLongNot(Double... value) {
        addNotEquals(LocationColumns.COORD_LONG, value);
        return this;
    }

    public WeatherSelection locationCoordLongGt(double value) {
        addGreaterThan(LocationColumns.COORD_LONG, value);
        return this;
    }

    public WeatherSelection locationCoordLongGtEq(double value) {
        addGreaterThanOrEquals(LocationColumns.COORD_LONG, value);
        return this;
    }

    public WeatherSelection locationCoordLongLt(double value) {
        addLessThan(LocationColumns.COORD_LONG, value);
        return this;
    }

    public WeatherSelection locationCoordLongLtEq(double value) {
        addLessThanOrEquals(LocationColumns.COORD_LONG, value);
        return this;
    }

    public WeatherSelection orderByLocationCoordLong(boolean desc) {
        orderBy(LocationColumns.COORD_LONG, desc);
        return this;
    }

    public WeatherSelection orderByLocationCoordLong() {
        orderBy(LocationColumns.COORD_LONG, false);
        return this;
    }

    public WeatherSelection date(Integer... value) {
        addEquals(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection dateNot(Integer... value) {
        addNotEquals(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection dateGt(int value) {
        addGreaterThan(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection dateGtEq(int value) {
        addGreaterThanOrEquals(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection dateLt(int value) {
        addLessThan(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection dateLtEq(int value) {
        addLessThanOrEquals(WeatherColumns.DATE, value);
        return this;
    }

    public WeatherSelection orderByDate(boolean desc) {
        orderBy(WeatherColumns.DATE, desc);
        return this;
    }

    public WeatherSelection orderByDate() {
        orderBy(WeatherColumns.DATE, false);
        return this;
    }

    public WeatherSelection shortDesc(String... value) {
        addEquals(WeatherColumns.SHORT_DESC, value);
        return this;
    }

    public WeatherSelection shortDescNot(String... value) {
        addNotEquals(WeatherColumns.SHORT_DESC, value);
        return this;
    }

    public WeatherSelection shortDescLike(String... value) {
        addLike(WeatherColumns.SHORT_DESC, value);
        return this;
    }

    public WeatherSelection shortDescContains(String... value) {
        addContains(WeatherColumns.SHORT_DESC, value);
        return this;
    }

    public WeatherSelection shortDescStartsWith(String... value) {
        addStartsWith(WeatherColumns.SHORT_DESC, value);
        return this;
    }

    public WeatherSelection shortDescEndsWith(String... value) {
        addEndsWith(WeatherColumns.SHORT_DESC, value);
        return this;
    }

    public WeatherSelection orderByShortDesc(boolean desc) {
        orderBy(WeatherColumns.SHORT_DESC, desc);
        return this;
    }

    public WeatherSelection orderByShortDesc() {
        orderBy(WeatherColumns.SHORT_DESC, false);
        return this;
    }

    public WeatherSelection min(Double... value) {
        addEquals(WeatherColumns.MIN, value);
        return this;
    }

    public WeatherSelection minNot(Double... value) {
        addNotEquals(WeatherColumns.MIN, value);
        return this;
    }

    public WeatherSelection minGt(double value) {
        addGreaterThan(WeatherColumns.MIN, value);
        return this;
    }

    public WeatherSelection minGtEq(double value) {
        addGreaterThanOrEquals(WeatherColumns.MIN, value);
        return this;
    }

    public WeatherSelection minLt(double value) {
        addLessThan(WeatherColumns.MIN, value);
        return this;
    }

    public WeatherSelection minLtEq(double value) {
        addLessThanOrEquals(WeatherColumns.MIN, value);
        return this;
    }

    public WeatherSelection orderByMin(boolean desc) {
        orderBy(WeatherColumns.MIN, desc);
        return this;
    }

    public WeatherSelection orderByMin() {
        orderBy(WeatherColumns.MIN, false);
        return this;
    }

    public WeatherSelection max(Double... value) {
        addEquals(WeatherColumns.MAX, value);
        return this;
    }

    public WeatherSelection maxNot(Double... value) {
        addNotEquals(WeatherColumns.MAX, value);
        return this;
    }

    public WeatherSelection maxGt(double value) {
        addGreaterThan(WeatherColumns.MAX, value);
        return this;
    }

    public WeatherSelection maxGtEq(double value) {
        addGreaterThanOrEquals(WeatherColumns.MAX, value);
        return this;
    }

    public WeatherSelection maxLt(double value) {
        addLessThan(WeatherColumns.MAX, value);
        return this;
    }

    public WeatherSelection maxLtEq(double value) {
        addLessThanOrEquals(WeatherColumns.MAX, value);
        return this;
    }

    public WeatherSelection orderByMax(boolean desc) {
        orderBy(WeatherColumns.MAX, desc);
        return this;
    }

    public WeatherSelection orderByMax() {
        orderBy(WeatherColumns.MAX, false);
        return this;
    }

    public WeatherSelection humidity(Double... value) {
        addEquals(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection humidityNot(Double... value) {
        addNotEquals(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection humidityGt(double value) {
        addGreaterThan(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection humidityGtEq(double value) {
        addGreaterThanOrEquals(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection humidityLt(double value) {
        addLessThan(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection humidityLtEq(double value) {
        addLessThanOrEquals(WeatherColumns.HUMIDITY, value);
        return this;
    }

    public WeatherSelection orderByHumidity(boolean desc) {
        orderBy(WeatherColumns.HUMIDITY, desc);
        return this;
    }

    public WeatherSelection orderByHumidity() {
        orderBy(WeatherColumns.HUMIDITY, false);
        return this;
    }

    public WeatherSelection pressure(Double... value) {
        addEquals(WeatherColumns.PRESSURE, value);
        return this;
    }

    public WeatherSelection pressureNot(Double... value) {
        addNotEquals(WeatherColumns.PRESSURE, value);
        return this;
    }

    public WeatherSelection pressureGt(double value) {
        addGreaterThan(WeatherColumns.PRESSURE, value);
        return this;
    }

    public WeatherSelection pressureGtEq(double value) {
        addGreaterThanOrEquals(WeatherColumns.PRESSURE, value);
        return this;
    }

    public WeatherSelection pressureLt(double value) {
        addLessThan(WeatherColumns.PRESSURE, value);
        return this;
    }

    public WeatherSelection pressureLtEq(double value) {
        addLessThanOrEquals(WeatherColumns.PRESSURE, value);
        return this;
    }

    public WeatherSelection orderByPressure(boolean desc) {
        orderBy(WeatherColumns.PRESSURE, desc);
        return this;
    }

    public WeatherSelection orderByPressure() {
        orderBy(WeatherColumns.PRESSURE, false);
        return this;
    }

    public WeatherSelection wind(Double... value) {
        addEquals(WeatherColumns.WIND, value);
        return this;
    }

    public WeatherSelection windNot(Double... value) {
        addNotEquals(WeatherColumns.WIND, value);
        return this;
    }

    public WeatherSelection windGt(double value) {
        addGreaterThan(WeatherColumns.WIND, value);
        return this;
    }

    public WeatherSelection windGtEq(double value) {
        addGreaterThanOrEquals(WeatherColumns.WIND, value);
        return this;
    }

    public WeatherSelection windLt(double value) {
        addLessThan(WeatherColumns.WIND, value);
        return this;
    }

    public WeatherSelection windLtEq(double value) {
        addLessThanOrEquals(WeatherColumns.WIND, value);
        return this;
    }

    public WeatherSelection orderByWind(boolean desc) {
        orderBy(WeatherColumns.WIND, desc);
        return this;
    }

    public WeatherSelection orderByWind() {
        orderBy(WeatherColumns.WIND, false);
        return this;
    }

    public WeatherSelection degrees(Double... value) {
        addEquals(WeatherColumns.DEGREES, value);
        return this;
    }

    public WeatherSelection degreesNot(Double... value) {
        addNotEquals(WeatherColumns.DEGREES, value);
        return this;
    }

    public WeatherSelection degreesGt(double value) {
        addGreaterThan(WeatherColumns.DEGREES, value);
        return this;
    }

    public WeatherSelection degreesGtEq(double value) {
        addGreaterThanOrEquals(WeatherColumns.DEGREES, value);
        return this;
    }

    public WeatherSelection degreesLt(double value) {
        addLessThan(WeatherColumns.DEGREES, value);
        return this;
    }

    public WeatherSelection degreesLtEq(double value) {
        addLessThanOrEquals(WeatherColumns.DEGREES, value);
        return this;
    }

    public WeatherSelection orderByDegrees(boolean desc) {
        orderBy(WeatherColumns.DEGREES, desc);
        return this;
    }

    public WeatherSelection orderByDegrees() {
        orderBy(WeatherColumns.DEGREES, false);
        return this;
    }
}
