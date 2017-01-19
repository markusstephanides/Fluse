package com.digotsoft.fluse.server.configuration;

import com.digotsoft.fluse.server.routing.Route;
import org.ini4j.Ini;
import org.ini4j.Profile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Markus on 18.01.2017.
 */
public class Configuration {

    private String projectName;
    private Set<Route> routes;
    private int port;

    public Configuration(String filePath) throws IOException {
        Ini file = new Ini(new File(filePath));
        this.projectName = file.get("general","name");
        this.port = Integer.parseInt(file.get("server", "port"));
        this.routes = new LinkedHashSet<>();

        Profile.Section section = file.get("routes");
        for (String key : section.keySet()) {
            this.routes.add(new Route(key, section.get(key)));
        }
    }

    public String getProjectName() {
        return this.projectName;
    }

    public Set<Route> getRoutes() {
        return this.routes;
    }

    public int getPort() {
        return this.port;
    }
}
