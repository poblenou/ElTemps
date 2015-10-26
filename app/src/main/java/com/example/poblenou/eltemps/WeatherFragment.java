package com.example.poblenou.eltemps;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.poblenou.eltemps.json.Forecast;
import com.example.poblenou.eltemps.json.List;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    public WeatherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        String[] data = {
                "Lun 23/6 - Soleado - 31/17",
                "Mar 24/6 - Niebla - 21/8",
                "Mier 25/6 - Nublado - 22/17",
                "Jue 26/6 - Lluvioso - 18/11",
                "Vie 27/6 - Niebla - 21/10",
                "Sab 28/6 - Soleado - 23/18",
                "Dom 29/6 - Soleado - 20/7"
        };

        items = new ArrayList<>(Arrays.asList(data));
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.listview_forecasts_row,
                R.id.tvForecast,
                items
        );

        ListView lvForecast = (ListView) rootView.findViewById(R.id.lvForecasts);
        lvForecast.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_weather_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_refresh) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        DownloadWeatherTask task = new DownloadWeatherTask();
        task.execute();
    }

    public class DownloadWeatherTask extends AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            final String OWMURL = "http://api.openweathermap.org/data/2.5/forecast/daily?";

            String QUERY_PARAM = "q";
            final String FORMAT_PARAM = "mode";
            final String UNITS_PARAM = "units";
            final String DAYS_PARAM = "cnt";
            final String APPID_PARAM = "appid";

            final String MODE = "json";
            final int COUNT = 14;
            final String APPID = "bd82977b86bf27fb59a04b61b657fb6f";

            String city = "Barcelona";
            String units = "metric";

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            ArrayList<String> dailyForecastsStrings = new ArrayList<>();
            try {

                Uri builtUri = Uri.parse(OWMURL).buildUpon()
                        .appendQueryParameter(QUERY_PARAM, city)
                        .appendQueryParameter(FORMAT_PARAM, MODE)
                        .appendQueryParameter(UNITS_PARAM, units)
                        .appendQueryParameter(DAYS_PARAM, String.valueOf(COUNT))
                        .appendQueryParameter(APPID_PARAM, APPID)
                        .build();

                URL url = new URL(builtUri.toString());

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                Gson gson = new Gson();
                Forecast forecast = gson.fromJson(reader, Forecast.class);

                ArrayList<List> dailyForecasts = (ArrayList<List>) forecast.getList();

                for (List dailyForecast : dailyForecasts) {
                    dailyForecastsStrings.add(dailyForecast.getForecastString());
                }

            } catch (IOException e) {
                Log.e(null, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attempting
                // to parse it.
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(null, "Error closing stream", e);
                    }
                }
            }
            return dailyForecastsStrings;
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(ArrayList<String> forecasts) {
            super.onPostExecute(forecasts);

            adapter.clear();
            adapter.addAll(forecasts);
        }

    }

}