package com.digotsoft.fluse.server.routing;

/**
 * @author Digot
 * @version 1.0
 */
public class RouteContext {

    private Route route;
    private String[] splittedKey;
    private String[] splittedUri;
    private int index;

    public RouteContext( Route route, String[] splittedKey, String[] splittedUri, int index ) {
        this.index = index;
        this.route = route;
        this.splittedKey = splittedKey;
        this.splittedUri = splittedUri;
    }

    public Route getRoute() {
        return route;
    }

    public String[] getSplittedKey() {
        return splittedKey;
    }

    public String[] getSplittedUri() {
        return splittedUri;
    }

    public int getIndex() {
        return index;
    }
}
