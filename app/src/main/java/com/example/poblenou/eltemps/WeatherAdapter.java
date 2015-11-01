package com.example.poblenou.eltemps;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.poblenou.eltemps.json.List;

public class WeatherAdapter extends ArrayAdapter<List> {
    public WeatherAdapter(Context context, int resource, java.util.List<List> objects) {
        super(context, resource, objects);
    }
}
