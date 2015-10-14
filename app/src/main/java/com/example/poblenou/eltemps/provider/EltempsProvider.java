package com.example.poblenou.eltemps.provider;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.example.carlesgm.eltemps.BuildConfig;
import com.example.poblenou.eltemps.provider.base.BaseContentProvider;
import com.example.poblenou.eltemps.provider.location.LocationColumns;
import com.example.poblenou.eltemps.provider.weather.WeatherColumns;

import java.util.Arrays;

public class EltempsProvider extends BaseContentProvider {
    public static final String AUTHORITY = "com.example.poblenou.eltemps.provider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;
    private static final String TAG = EltempsProvider.class.getSimpleName();
    private static final boolean DEBUG = BuildConfig.DEBUG;
    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";
    private static final int URI_TYPE_LOCATION = 0;
    private static final int URI_TYPE_LOCATION_ID = 1;

    private static final int URI_TYPE_WEATHER = 2;
    private static final int URI_TYPE_WEATHER_ID = 3;


    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, LocationColumns.TABLE_NAME, URI_TYPE_LOCATION);
        URI_MATCHER.addURI(AUTHORITY, LocationColumns.TABLE_NAME + "/#", URI_TYPE_LOCATION_ID);
        URI_MATCHER.addURI(AUTHORITY, WeatherColumns.TABLE_NAME, URI_TYPE_WEATHER);
        URI_MATCHER.addURI(AUTHORITY, WeatherColumns.TABLE_NAME + "/#", URI_TYPE_WEATHER_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return EltempsSQLiteOpenHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_LOCATION:
                return TYPE_CURSOR_DIR + LocationColumns.TABLE_NAME;
            case URI_TYPE_LOCATION_ID:
                return TYPE_CURSOR_ITEM + LocationColumns.TABLE_NAME;

            case URI_TYPE_WEATHER:
                return TYPE_CURSOR_DIR + WeatherColumns.TABLE_NAME;
            case URI_TYPE_WEATHER_ID:
                return TYPE_CURSOR_ITEM + WeatherColumns.TABLE_NAME;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (DEBUG) Log.d(TAG, "insert uri=" + uri + " values=" + values);
        return super.insert(uri, values);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (DEBUG) Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        return super.bulkInsert(uri, values);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (DEBUG)
            Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.update(uri, values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (DEBUG)
            Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.delete(uri, selection, selectionArgs);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (DEBUG)
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                    + " groupBy=" + uri.getQueryParameter(QUERY_GROUP_BY) + " having=" + uri.getQueryParameter(QUERY_HAVING) + " limit=" + uri.getQueryParameter(QUERY_LIMIT));
        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_LOCATION:
            case URI_TYPE_LOCATION_ID:
                res.table = LocationColumns.TABLE_NAME;
                res.idColumn = LocationColumns._ID;
                res.tablesWithJoins = LocationColumns.TABLE_NAME;
                res.orderBy = LocationColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_WEATHER:
            case URI_TYPE_WEATHER_ID:
                res.table = WeatherColumns.TABLE_NAME;
                res.idColumn = WeatherColumns._ID;
                res.tablesWithJoins = WeatherColumns.TABLE_NAME;
                if (LocationColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + LocationColumns.TABLE_NAME + " AS " + WeatherColumns.PREFIX_LOCATION + " ON " + WeatherColumns.TABLE_NAME + "." + WeatherColumns.LOCATION_ID + "=" + WeatherColumns.PREFIX_LOCATION + "." + LocationColumns._ID;
                }
                res.orderBy = WeatherColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_LOCATION_ID:
            case URI_TYPE_WEATHER_ID:
                id = uri.getLastPathSegment();
        }
        if (id != null) {
            if (selection != null) {
                res.selection = res.table + "." + res.idColumn + "=" + id + " and (" + selection + ")";
            } else {
                res.selection = res.table + "." + res.idColumn + "=" + id;
            }
        } else {
            res.selection = selection;
        }
        return res;
    }
}
