package com.wmbest.cta.modules;

import android.content.Context;
import android.content.ContentResolver;

import dagger.*;

import com.wmbest.cta.RoutesFragment;
import com.wmbest.cta.service.*;

@Module(
    complete=false,
    injects={
        RoutesFragment.class
    }
)
public class MainModule {
    private Context mAppContext;

    public MainModule(Context aContext) {
        mAppContext = aContext.getApplicationContext();
    }

    //@Provides @App Context getAppContext() {
        //return mAppContext;
    //}

    @Provides ContentResolver getResolver() {
        return mAppContext.getContentResolver();
    }

    @Provides @ApiKey String provideKey() {
        return "VpUpg7uRSNWf9AckF7WY5aBEq";
    }

    @Provides RouteService routeService(VolleyRouteService service) {
        return service;
    }
}
