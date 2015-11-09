package com.example.poblenou.eltemps.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.poblenou.eltemps.BuildConfig;
import com.example.poblenou.eltemps.provider.location.LocationColumns;
import com.example.poblenou.eltemps.provider.weather.WeatherColumns;

public class EltempsSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_FILE_NAME = "eltemps.db";
    // @formatter:off
    public static final String SQL_CREATE_TABLE_LOCATION = "CREATE TABLE IF NOT EXISTS "
            + LocationColumns.TABLE_NAME + " ( "
            + LocationColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LocationColumns.LOCATION_SETTING + " INTEGER, "
            + LocationColumns.CITY_NAME + " TEXT, "
            + LocationColumns.COORD_LAT + " REAL, "
            + LocationColumns.COORD_LONG + " REAL "
            + " );";
    public static final String SQL_CREATE_TABLE_WEATHER = "CREATE TABLE IF NOT EXISTS "
            + WeatherColumns.TABLE_NAME + " ( "
            + WeatherColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WeatherColumns.LOCATION_ID + " INTEGER, "
            + WeatherColumns.DATE + " INTEGER, "
            + WeatherColumns.SHORT_DESC + " TEXT, "
            + WeatherColumns.MIN + " REAL, "
            + WeatherColumns.MAX + " REAL, "
            + WeatherColumns.HUMIDITY + " REAL, "
            + WeatherColumns.PRESSURE + " REAL, "
            + WeatherColumns.WIND + " REAL, "
            + WeatherColumns.DEGREES + " REAL "
            + ", CONSTRAINT fk_location_id FOREIGN KEY (" + WeatherColumns.LOCATION_ID + ") REFERENCES location (_id) ON DELETE CASCADE"
            + " );";
    private static final String TAG = EltempsSQLiteOpenHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static EltempsSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final EltempsSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:on

    private EltempsSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new EltempsSQLiteOpenHelperCallbacks();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private EltempsSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new EltempsSQLiteOpenHelperCallbacks();
    }

    public static EltempsSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static EltempsSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }

    /*
     * Pre Honeycomb.
     */
    private static EltempsSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new EltempsSQLiteOpenHelper(context);
    }

    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static EltempsSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new EltempsSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_LOCATION);
        db.execSQL(SQL_CREATE_TABLE_WEATHER);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
