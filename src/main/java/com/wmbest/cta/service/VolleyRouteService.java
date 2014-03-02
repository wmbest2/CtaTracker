package com.wmbest.cta.service;

import android.content.ContentResolver;
import android.content.ContentValues;

import javax.inject.Inject;

import com.android.volley.*;
import com.android.volley.Response.Listener;

import com.vokal.volley.BaseUrl;
import com.vokal.database.DatabaseHelper;

import timber.log.*;

import com.wmbest.cta.models.*;
import com.wmbest.cta.network.SimpleXmlRequest;

public class VolleyRouteService implements RouteService {

    public static final String ROUTE_LIST = "getroutes";

    @Inject ContentResolver mResolver;
    @Inject @ApiKey String mKey;

    @Inject RequestQueue mNetwork;
    @Inject @BaseUrl String mBase;

    public void fetchRoutes() {
        mNetwork.cancelAll(ROUTE_LIST);
        mNetwork.add(new RouteListRequest(mBase + ROUTE_LIST + "?key=" + mKey,
            new Listener<RouteList>() {
                public void onResponse(RouteList response) {
                    ContentValues[] values = new ContentValues[response.routes.size()];
                    int i = 0;
                    for (Route r : response.routes) {
                        Timber.d(r.name);
                        values[i] = new ContentValues();
                        r.populateContentValues(values[i++]);
                    }
                    mResolver.bulkInsert(DatabaseHelper.getContentUri(Route.class), values);
                }
            }));
    }

    private static final class RouteListRequest extends SimpleXmlRequest<RouteList> {
        public RouteListRequest(String url, Listener<RouteList> aListener) {
            super(Method.GET, url, RouteList.class, aListener, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError aError) {
                    Timber.e("Error %s", aError.getMessage());
                }
            });

            setTag(ROUTE_LIST);
        }
    }
}
