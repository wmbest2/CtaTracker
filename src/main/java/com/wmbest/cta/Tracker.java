package com.wmbest.cta;

import android.app.Activity;
import android.os.Bundle;

import dagger.*;

import barstool.*;
import butterknife.*;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import com.vokal.volley.VolleyBallDebug;

import javax.inject.Inject;

import com.wmbest.cta.dagger.DaggerApp;
import com.wmbest.cta.service.RouteService;

public class Tracker extends Activity {

    @Inject Bus mBus;
    @Inject RouteService mService;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ObjectGraph graph = ((DaggerApp) getApplication()).graph();

        graph.inject(this);
        Barstool.with(graph).wrap(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mBus.unregister(this);
    }

    @Subscribe
    public void onServerChange(VolleyBallDebug.Changed aEvent) {
        ObjectGraph graph = ((DaggerApp) getApplication()).graph();
        graph.inject(this);

        mService.fetchRoutes();
    }

}
