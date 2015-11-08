package com.example.poblenou.eltemps;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.Date;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

interface OpenWeatherMapService {
    @GET("forecast/daily")
    Call<JsonObject> dailyForecast(
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

        Call<JsonObject> forecastCall = service.dailyForecast(
                city, FORMAT, units, 14, APPID
        );
        forecastCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Response<JsonObject> response, Retrofit retrofit) {
                JsonObject apiResult = response.body();
                //11-08 13:57:07.715 21042-21042/? W/XXX: "list":[{"dt":1446980400,"temp":{"day":17.89,"min":15.5,"max":18.06,"night":15.5,"eve":16.53,"morn":17.89},"pressure":1033.12,"humidity":96,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"speed":3.01,"deg":230,"clouds":24},{"dt":1447066800,"temp":{"day":17.61,"min":14.2,"max":18.99,"night":16.97,"eve":17.49,"morn":14.2},"pressure":1034.79,"humidity":97,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"speed":2.41,"deg":248,"clouds":0},{"dt":1447153200,"temp":{"day":19.67,"min":16.19,"max":19.7,"night":16.38,"eve":17.49,"morn":16.19},"pressure":1031.39,"humidity":86,"weather":[{"id":800,"main":"Clear","description":"sky is clear","icon":"01d"}],"speed":1.14,"deg":197,"clouds":0},{"dt":1447239600,"temp":{"day":19.35,"min":15.48,"max":19.35,"night":15.48,"eve":16.92,"morn":15.78},"pressure":1027.78,"humidity":87,"weather":[{"id":800,"main":"Clear","description":"sky is clear","icon":"01d"}],"speed":1.61,"deg":195,"clouds":0},{"dt":1447326000,"temp":{"day":17.94,"min":15.03,"max":17.94,"night":15.03,"eve":15.73,"morn":15.08},"pressure":1028.97,"humidity":96,"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02d"}],"speed":2.4,"deg":96,"clouds":20},{"dt":1447412400,"temp":{"day":16.52,"min":13.81,"max":16.64,"night":15.68,"eve":16.64,"morn":13.81},"pressure":1030.15,"humidity":0,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"speed":3.16,"deg":282,"clouds":11},{"dt":1447498800,"temp":{"day":17.02,"min":14.62,"max":17.02,"night":14.62,"eve":15.9,"morn":14.93},"pressure":1030.62,"humidity":0,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"speed":1.3,"deg":183,"clouds":37,"rain":1.43},{"dt":1447585200,"temp":{"day":16.58,"min":13.66,"max":16.58,"night":14.68,"eve":15.5,"morn":13.66},"pressure":1025.61,"humidity":0,"weather":[{"id":800,"main":"Clear","description":"sky is clear","icon":"01d"}],"speed":3.05,"deg":268,"clouds":18},{"dt":1447671600,"temp":{"day":17.77,"min":14.14,"max":17.77,"night":14.53,"eve":15.85,"morn":14.14},"pressure":1016.93,"humidity":0,"weather":[{"id":800,"main":"Clear","description":"sky is clear","icon":"01d"}],"speed":3.07,"deg":238,"clouds":0},{"dt":1447758000,"temp":{"day":18.34,"min":14.12,"max":18.34,"night":15.32,"eve":16.27,"morn":14.12},"pressure":1014.24,"humidity":0,"weather":[{"id":800,"main":"Clear","description":"sky is clear","icon":"01d"}],"speed":4.96,"deg":233,"clouds":0},{"dt":1447844400,"temp":{"day":18.69,"min":14.58,"max":18.69,"night":15.47,"eve":18.16,"morn":14.58},"pressure":1014.25,"humidity":0,"weather":[{"id":800,"main":"Clear","description":"sky is clear","icon":"01d"}],"speed":7.06,"deg":235,"clouds":1},{"dt":1447930800,"temp":{"day":16.17,"min":13.65,"max":16.6,"night":14.33,"eve":16.6,"morn":13.65},"pressure":1018.51,"humidity":0,"weather":[{"id":800,"main":"Clear","description":"sky is clear","icon":"01d"}],"speed":8.14,"deg":308,"clouds":0},{"dt":1448017200,"temp":{"day":15.64,"min":12.91,"max":15.64,"night":13.54,"eve":14.36,"morn":12.91},"pressure":1025,"humidity":0,"weather":[{"id":800,"main":"Clear","description":"sky is clear","icon":"01d"}],"speed":2.49,"deg":41,"clouds":11},{"dt":1448103600,"temp":{"day":16.6,"min":13.37,"max":16.6,"night":14.53,"eve":15.27,"morn":13.37},"pressure":1023.28,"humidity":0,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"speed":1.27,"deg":149,"clouds":15}]}
                JsonArray list = apiResult.getAsJsonArray("list");
                adapter.clear();
                for (JsonElement element : list) {
                    JsonObject jsonForecast = element.getAsJsonObject();
                    Forecast forecast = new Forecast();

                    JsonObject temp = jsonForecast.get("temp").getAsJsonObject();
                    Double max = temp.get("max").getAsDouble();
                    Double min = temp.get("min").getAsDouble();

                    forecast.setMax(max);
                    forecast.setMin(min);

                    forecast.setDate(new Date(jsonForecast.get("dt").getAsLong()));
                    forecast.setDegrees(jsonForecast.get("deg").getAsDouble());
                    forecast.setHumidity(jsonForecast.get("humidity").getAsDouble());
                    forecast.setWind(jsonForecast.get("speed").getAsDouble());

                    JsonArray weatherArray = jsonForecast.get("weather").getAsJsonArray();
                    JsonObject weatherObject = weatherArray.get(0).getAsJsonObject();
                    forecast.setShort_desc(weatherObject.get("main").getAsString());
                    forecast.setWeatherId(weatherObject.get("id").getAsLong());

                    adapter.addAll(forecast);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Update Forecasts", Arrays.toString(t.getStackTrace()));
            }
        });

    }
}

