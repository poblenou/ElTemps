package com.example.poblenou.eltemps;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.poblenou.eltemps.json.Forecast;

import java.util.Arrays;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

interface OpenWeatherMapService {
    @GET("forecast/daily")
    Call<Forecast> dailyForecast(
            @Query("q") String city,
            @Query("mode") String format,
            @Query("units") String units,
            @Query("cnt") Integer num,
            @Query("appid") String appid);
}

public class OwmApiClient {
    private final OpenWeatherMapService service;
    private final String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private final String APPID = "375ed3b68e2560968dc4e2f1b180f2ef";
    private final String FORMAT = "json";

    public OwmApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FORECAST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(OpenWeatherMapService.class);
    }

    public void updateForecasts(final WeatherAdapter adapter, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String city = preferences.getString("city", "Barcelona");
        String units = preferences.getString("units", "metric");

        Call<Forecast> forecastCall = service.dailyForecast(
                city, FORMAT, units, 14, APPID
        );
        forecastCall.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Response<Forecast> response, Retrofit retrofit) {
                Forecast forecast = response.body();

                adapter.clear();
                adapter.addAll(forecast.getList());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Update Forecasts", Arrays.toString(t.getStackTrace()));
            }
        });

    }
}

