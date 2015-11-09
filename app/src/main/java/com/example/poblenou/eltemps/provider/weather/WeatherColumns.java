package com.example.poblenou.eltemps.provider.weather;

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.poblenou.eltemps.provider.EltempsProvider;
import com.example.poblenou.eltemps.provider.location.LocationColumns;

/**
 * Columns for the {@code weather} table.
 */
public class WeatherColumns implements BaseColumns {
    public static final String TABLE_NAME = "weather";
    public static final Uri CONTENT_URI = Uri.parse(EltempsProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String LOCATION_ID = "location_id";

    public static final String DATE = "date";

    public static final String SHORT_DESC = "short_desc";

    public static final String MIN = "min";

    public static final String MAX = "max";

    public static final String HUMIDITY = "humidity";

    public static final String PRESSURE = "pressure";

    public static final String WIND = "wind";

    public static final String DEGREES = "degrees";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." + _ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[]{
            _ID,
            LOCATION_ID,
            DATE,
            SHORT_DESC,
            MIN,
            MAX,
            HUMIDITY,
            PRESSURE,
            WIND,
            DEGREES
    };
    // @formatter:on
    public static final String PREFIX_LOCATION = TABLE_NAME + "__" + LocationColumns.TABLE_NAME;

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(LOCATION_ID) || c.contains("." + LOCATION_ID)) return true;
            if (c.equals(DATE) || c.contains("." + DATE)) return true;
            if (c.equals(SHORT_DESC) || c.contains("." + SHORT_DESC)) return true;
            if (c.equals(MIN) || c.contains("." + MIN)) return true;
            if (c.equals(MAX) || c.contains("." + MAX)) return true;
            if (c.equals(HUMIDITY) || c.contains("." + HUMIDITY)) return true;
            if (c.equals(PRESSURE) || c.contains("." + PRESSURE)) return true;
            if (c.equals(WIND) || c.contains("." + WIND)) return true;
            if (c.equals(DEGREES) || c.contains("." + DEGREES)) return true;
        }
        return false;
    }
}
