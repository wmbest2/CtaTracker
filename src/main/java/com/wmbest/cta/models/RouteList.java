package com.wmbest.cta.models;

import java.util.List;

import org.simpleframework.xml.*;

public class RouteList {
    @ElementList(inline=true)
    public List<Route> routes;
}
