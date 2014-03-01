package com.wmbest.cta.modules;

import dagger.*;

import com.vokal.volley.*;

@Module(
    complete=false,
    injects={
        VolleyBallDebug.class,
    },
    includes=OttoModule.class
)
public class DebugOttoModule {
    
}
