package com.wmbest.cta.modules;

import android.content.Context;

import dagger.*;

import com.android.volley.*;

import javax.inject.Singleton;

import com.wmbest.cta;

@Module(
    complete=false,
    injects={
        Tracker.class,
    }
)
public class FixedVolleyModule {
    private Context mContext;

    public FixedVolleyModule(Context aContext) {
        mContext = aContext.getApplicationContext();
    }
    
    @Provides @BaseUrl String base() {
        return "http://www.ctabustracker.com/bustime/api/v1/"
    }

    @Provides @Singleton RequestQueue getQueue() {
        return Volley.newRequestQueue(mContext);
    }
}
