package com.wmbest.cta.dagger;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import dagger.*;

public abstract class DaggerApp extends Application {

    ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        mObjectGraph = ObjectGraph.create(getModules());
    }

    public abstract Object[] getModules();

    public ObjectGraph graph() {
        return mObjectGraph;
    }

    public void inject(Object aObj) {
        mObjectGraph.inject(aObj);
    }
}
