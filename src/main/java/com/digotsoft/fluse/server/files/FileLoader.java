package com.digotsoft.fluse.server.files;

import com.digotsoft.fluse.server.routing.RouteContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Digot
 * @version 1.0
 */
public class FileLoader {

    public FileInformation loadFileByRoute( RouteContext context ) throws IOException {
        String[] localPathArr = new String[context.getSplittedUri().length - context.getIndex() + 1];
        localPathArr[0] = context.getRoute().getTarget();
        for ( int j = 0; j < localPathArr.length - 1; j++ ) {
            localPathArr[j + 1] = context.getSplittedUri()[j + context.getIndex()];
        }
        String filePath = String.join( "/", localPathArr );
        System.out.println(filePath);
        File file = new File( filePath );

        if ( file.exists() ) {
            Path path = file.toPath();
            return new FileInformation( Files.readAllBytes( path ), Files.probeContentType( path ) );
        }

        return null;
    }

    public FileInformation loadFile( String filePath ) throws IOException {
        File file = new File( filePath );
        System.out.println(filePath);
        if ( file.exists() ) {
            Path path = file.toPath();
            return new FileInformation( Files.readAllBytes( path ), Files.probeContentType( path ) );
        }

        return null;
    }

}
