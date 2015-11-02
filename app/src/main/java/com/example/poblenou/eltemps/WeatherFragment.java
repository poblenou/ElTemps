package com.example.poblenou.eltemps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.poblenou.eltemps.json.List;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherFragment extends Fragment {

    private ArrayList<List> items;
    private WeatherAdapter adapter;

    public WeatherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();

        refresh();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        items = new ArrayList<>();
        adapter = new WeatherAdapter(
                getContext(),
                R.layout.listview_forecasts_row,
                items
        );

        ListView lvForecast = (ListView) rootView.findViewById(R.id.lvForecasts);
        lvForecast.setAdapter(adapter);

        lvForecast.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List item = (List) parent.getItemAtPosition(position);

                Intent i = new Intent(getContext(), DetailActivity.class);
                i.putExtra("item", item);
                startActivity(i);
            }
        });

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
        OwmApiClient apiClient = new OwmApiClient();
        apiClient.updateForecasts(adapter, getContext());
    }
}
