package com.wmbest.cta;

import android.app.Activity;
import android.app.FragmentTransaction;
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

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ObjectGraph graph = ((DaggerApp) getApplication()).graph();
        Barstool.with(graph).wrap(this);

        FragmentTransaction t = getFragmentManager().beginTransaction();
        t.replace(R.id.root, new RoutesFragment());
        t.commit();
    }

}
