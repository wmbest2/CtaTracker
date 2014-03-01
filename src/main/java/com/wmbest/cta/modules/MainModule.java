package com.wmbest.cta.modules;

import dagger.*;

import com.wmbest.cta.Tracker;
import com.wmbest.cta.service.*;

@Module(
    complete=false,
    injects={
        Tracker.class,
        VolleyRouteService.class
    }
)
public class MainModule {
    @Provides @ApiKey String provideKey() {
        return "VpUpg7uRSNWf9AckF7WY5aBEq";
    }

    @Provides RouteService routeService(VolleyRouteService service) {
        return service;
    }
}
