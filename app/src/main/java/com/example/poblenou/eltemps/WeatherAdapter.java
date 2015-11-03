package com.example.poblenou.eltemps;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poblenou.eltemps.json.List;

public class WeatherAdapter extends ArrayAdapter<List> {
    private final Context context;

    public WeatherAdapter(Context context, int resource, java.util.List<List> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtenim l'objecte en la posició corresponent
        List item = getItem(position);

        // Mirem a veure si la View s'està reusant, si no es així "inflem" la View
        // https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView#row-view-recycling
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_forecasts_row, parent, false);
        }

        // Unim el codi en les Views del Layout
        ImageView ivlistItemIcon = (ImageView) convertView.findViewById(R.id.list_item_icon);
        TextView tvlistItemDateTextview = (TextView) convertView.findViewById(R.id.list_item_date_textview);
        TextView tvlistItemForecastTextview = (TextView) convertView.findViewById(R.id.list_item_forecast_textview);
        TextView tvlistItemHighTextview = (TextView) convertView.findViewById(R.id.list_item_high_textview);
        TextView tvlistItemLowTextview = (TextView) convertView.findViewById(R.id.list_item_low_textview);

        // Fiquem les dades dels objectes (provinents del JSON) en el layout
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String units = preferences.getString("units", "metric");

        tvlistItemDateTextview.setText(item.getFormattedTemp());
        tvlistItemForecastTextview.setText(item.getWeather().get(0).getDescription());
        tvlistItemHighTextview.setText(item.getMaxTemp(units));
        tvlistItemLowTextview.setText(item.getMinTemp(units));

        // Retornem la View replena per a mostrarla
        return convertView;
    }

    private String getIconUrl(int weatherId) {
        String base = "http://openweathermap.org/img/w/";
        String icon = getIconResourceForWeatherCondition(weatherId);

        return base + icon;
    }

    private String getIconResourceForWeatherCondition(int weatherId) {
        // Based on weather code data found at:
        // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
        if (weatherId >= 200 && weatherId <= 232) {
            return "11d";
        } else if (weatherId >= 300 && weatherId <= 321) {
            return "09d";
        } else if (weatherId >= 500 && weatherId <= 504) {
            return "10d.png";
        } else if (weatherId == 511) {
            return "13d.png";
        } else if (weatherId >= 520 && weatherId <= 531) {
            return "09d.png";
        } else if (weatherId >= 600 && weatherId <= 622) {
            return "13d.png";
        } else if (weatherId >= 701 && weatherId <= 761) {
            return "50d.png";
        } else if (weatherId == 761 || weatherId == 781) {
            return "50d.png";
        } else if (weatherId == 800) {
            return "01n.png";
        } else if (weatherId == 801) {
            return "02d.png";
        } else if (weatherId >= 802 && weatherId <= 804) {
            return "04d.png";
        }
        return "01d.png";
    }

}
