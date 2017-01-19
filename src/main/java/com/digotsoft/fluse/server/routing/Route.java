package com.digotsoft.fluse.server.routing;

/**
 * @author Digot
 * @version 1.0
 */
public class Route {

    private String uri;
    private String target;
    private RouteType type;

    public Route( String uri, String target ) {
        this.uri = uri;
        this.target = target;

        this.setType();
    }

    private void setType() {
        char[] chars = this.uri.toCharArray();

        if ( chars[chars.length - 1] == '*' || chars[chars.length - 2] == '*' ) {
            this.type = RouteType.WILDCARD;
        }
        else {
            this.type = RouteType.NORMAL;
        }
    }

    public RouteType getType() {
        return type;
    }

    public String getTarget() {
        return target;
    }

    public String getUri() {
        return uri;
    }
}
