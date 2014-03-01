package com.wmbest.cta;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.wmbest.dagger.DaggerApp;

import dagger.Module;

import com.jakewharton.timber.*;

public class TrackerApp extends DaggerApp {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.HollowTree());
        DatabaseHelper.registerModel(this, Route.class);
    }

    @Override
    public Module[] getModules() {
        return new Module[] {
            new MainModule(),
            new FixedVolleyModule(this),
            new OttoModule()
        };
    }
}
