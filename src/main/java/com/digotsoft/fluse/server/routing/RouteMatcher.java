package com.digotsoft.fluse.server.routing;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Digot
 * @version 1.0
 */
public class RouteMatcher {

    private Set<Route> registeredRoutes;
    private Set<Route> wildcardRoutes;

    public RouteMatcher( Set<Route> registeredRoutes ) {
        this.registeredRoutes = registeredRoutes;
        this.wildcardRoutes = new LinkedHashSet<>();

        this.filterWildcardRoutes();
    }

    private void filterWildcardRoutes() {
        for ( Route registeredRoute : this.registeredRoutes ) {
            if ( registeredRoute.getType() == RouteType.WILDCARD ) {
                this.wildcardRoutes.add( registeredRoute );
            }
        }
    }


    public RouteContext matchRoute( String uri ) {
        for ( Route wildcardRoute : this.wildcardRoutes ) {
            String[] splittedKey = wildcardRoute.getUri().split( "/" );
            String[] splittedUri = uri.split( "/" );

            for ( int i = 0; i < splittedKey.length; i++ ) {
                if ( splittedKey[i].equals( "*" ) ) {
                    // We found the matching route
                    return new RouteContext( wildcardRoute, splittedKey, splittedUri, i );
                } else if ( !splittedKey[i].equals( splittedUri[i] ) ) {
                    // Thats a bad start, so it can't be the current route
                    break;
                }

            }

        }

        return null;
    }
}
