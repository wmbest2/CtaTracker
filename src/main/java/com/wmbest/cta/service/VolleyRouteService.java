package com.wmbest.cta.service;

import javax.inject.Inject;

import com.android.volley.*;
import com.android.volley.Response.Listener;

import com.vokal.volley.BaseUrl;

import timber.log.*;

import com.wmbest.cta.models.*;
import com.wmbest.cta.network.SimpleXmlRequest;

public class VolleyRouteService implements RouteService {

    public static final String ROUTE_LIST = "getroutes";

    @Inject @ApiKey String mKey;

    @Inject RequestQueue mNetwork;
    @Inject @BaseUrl String mBase;

    public void fetchRoutes() {
        mNetwork.cancelAll(ROUTE_LIST);
        mNetwork.add(new RouteListRequest(mBase + ROUTE_LIST + "?key=" + mKey));
    }

    private static final class RouteListRequest extends SimpleXmlRequest<RouteList> {
        public RouteListRequest(String url) {
            super(Method.GET, url, RouteList.class, new Response.Listener<RouteList>() {
                public void onResponse(RouteList response) {
                    for (Route r : response.routes) {
                        Timber.d(r.name);
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError aError) {
                    Timber.e("Error %s", aError.getMessage());
                }
            });

            setTag(ROUTE_LIST);
        }
    }
}
