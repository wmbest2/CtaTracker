package com.wmbest.cta.modules;

import dagger.*;
import com.squareup.otto.*;

import javax.inject.Singleton;

import com.vokal.volley.*;

import com.wmbest.cta.Tracker;

@Module(
    complete=false,
    injects={
        Tracker.class,
    }
)
public class OttoModule {
    @Provides @Singleton public Bus ottoBus() {
        return new Bus();
    }
}
