package com.example.poblenou.eltemps.provider.location;

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.poblenou.eltemps.provider.EltempsProvider;

/**
 * Columns for the {@code location} table.
 */
public class LocationColumns implements BaseColumns {
    public static final String TABLE_NAME = "location";
    public static final Uri CONTENT_URI = Uri.parse(EltempsProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String LOCATION_SETTING = "location_setting";

    public static final String CITY_NAME = "city_name";

    public static final String COORD_LAT = "coord_lat";

    public static final String COORD_LONG = "coord_long";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." + _ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[]{
            _ID,
            LOCATION_SETTING,
            CITY_NAME,
            COORD_LAT,
            COORD_LONG
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(LOCATION_SETTING) || c.contains("." + LOCATION_SETTING)) return true;
            if (c.equals(CITY_NAME) || c.contains("." + CITY_NAME)) return true;
            if (c.equals(COORD_LAT) || c.contains("." + COORD_LAT)) return true;
            if (c.equals(COORD_LONG) || c.contains("." + COORD_LONG)) return true;
        }
        return false;
    }

}
