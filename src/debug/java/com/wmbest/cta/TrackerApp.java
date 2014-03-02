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
import com.wmbest.cta.widget.FontIconTypefaceHolder;

import dagger.Module;

import com.newrelic.agent.android.NewRelic;

public class TrackerApp extends DaggerApp {

    @Override
    public void onCreate() {
        super.onCreate();

        NewRelic.withApplicationToken("AA80b4cae7b0a18a80911feb6572a42c846d04a0e7").start(this);

        Timber.plant(new Timber.DebugTree());
        DatabaseHelper.registerModel(this, Route.class);
        FontIconTypefaceHolder.init(this.getAssets(), "cta-font.ttf");
    }

    @Override
    public Object[] getModules() {

        VolleyBall networkModule = new VolleyBall(this);
        networkModule.forEnv("Production").addServer("http://www.ctabustracker.com/bustime/api/v1/");
        networkModule.addMock(R.xml.routes);

        return new Object[] {
            new MainModule(this),
            networkModule,
            new VolleyBallPlugin(),
            new DebugOttoModule()
        };
    }
}
