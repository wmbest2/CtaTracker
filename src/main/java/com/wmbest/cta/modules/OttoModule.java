package com.wmbest.cta.modules;

import dagger.*;
import com.squareup.otto.*;

import javax.inject.Singleton;

import com.vokal.volley.*;

import com.wmbest.cta.RoutesFragment;

@Module(
    complete=false,
    injects={
        RoutesFragment.class
    }
)
public class OttoModule {
    @Provides @Singleton public Bus ottoBus() {
        return new Bus();
    }
}
