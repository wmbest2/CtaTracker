package com.wmbest.cta;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.vokal.database.DatabaseHelper;
import com.vokal.volley.*;

import timber.log.*;

import com.wmbest.cta.dagger.DaggerApp;
import com.wmbest.cta.models.*;
import com.wmbest.cta.modules.*;

import dagger.Module;

public class TrackerApp extends DaggerApp {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        DatabaseHelper.registerModel(this, Route.class);
    }

    @Override
    public Object[] getModules() {

        VolleyBall networkModule = new VolleyBall(this);
        networkModule.addServer("Production", "http://www.ctabustracker.com/bustime/api/v1/");
        networkModule.addMock(R.xml.routes);

        return new Object[] {
            new MainModule(),
            networkModule,
            new VolleyBallPlugin(),
            new DebugOttoModule()
        };
    }
}
