package com.example.poblenou.eltemps;

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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherFragment extends Fragment
{
    //http://api.openweathermap.org/data/2.5/forecast/daily?q=Barcelona&units=metric&cnt=14&APPID=720f431ee254e6c38e84787031900368
    // key api wet 720f431ee254e6c38e84787031900368
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void refresh ()
    {
        //Conectamos con la api
        final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Creamos el servicio
        ClientWeatherMap service = retrofit.create(ClientWeatherMap.class);

        //Hacemos una llamada
        Call<Forecast> forecastCall=service.dailyForecast();

        forecastCall.enqueue(new Callback<Forecast>() {

            @Override
            public void onResponse(Response<Forecast> response, Retrofit retrofit) {
                Forecast forecast = response.body();
                for(List list : forecast.getList()){
                    Long dt = list.getDt();
                    String description = list.getWeather().get(0).getDescription();
                    Double min = list.getTemp().getMin();
                    Double max = list.getTemp().getMax();

                   // Log.w("list",String.format())

                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.w(null, Arrays.toString(t.getStackTrace()));
            }
        });

    }
    public interface ClientWeatherMap
    {
        @GET("q=Barcelona&units=metric&cnt=14&APPID=720f431ee254e6c38e84787031900368")
        Call<Forecast> dailyForecast();
    }

}
