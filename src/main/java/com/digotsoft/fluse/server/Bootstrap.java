package com.digotsoft.fluse.server;

import com.digotsoft.fluse.server.configuration.Configuration;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Markus on 18.01.2017.
 */
public class Bootstrap {

    public static void main( String[] args ) throws IOException {
        BasicConfigurator.configure();
        Logger logger = LoggerFactory.getLogger( Bootstrap.class );
        logger.info("Starting Fluse Server");

        Configuration configuration = new Configuration(args[0]);

        new HttpServer(configuration);
    }

}
