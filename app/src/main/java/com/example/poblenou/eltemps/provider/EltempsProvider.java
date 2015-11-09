package com.example.poblenou.eltemps.provider;

import com.example.poblenou.eltemps.BuildConfig;
import com.example.poblenou.eltemps.Forecast;

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class EltempsProvider extends CupboardContentProvider {
    // The content provider authority is used for building Uri's for the provider
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";


    static {
        cupboard().register(Forecast.class);
    }

    public EltempsProvider() {
        super(AUTHORITY, 1);
    }
}
