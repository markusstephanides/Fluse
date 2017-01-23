package com.digotsoft.fluse.server.error;

import com.digotsoft.fluse.server.files.FileInformation;
import com.digotsoft.fluse.server.files.FileLoader;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Digot
 * @version 1.0
 */
public class ErrorPageProvider {

    private Map<HttpResponseStatus, FileInformation> loadedErrorPages;
    private final FileLoader fileLoader;

    public ErrorPageProvider() {
        this.loadedErrorPages = new HashMap<>();
        this.fileLoader = new FileLoader();
    }

    public FileInformation retrievePage( HttpResponseStatus httpResponseStatus ) throws IOException {
        if ( this.loadedErrorPages.containsKey( httpResponseStatus ) ) {
            return this.loadedErrorPages.get( httpResponseStatus );
        }

        FileInformation fileInformation = this.fileLoader.loadFile( "errorpages/error" + httpResponseStatus.code() + ".html" );

        if ( fileInformation == null ) {
            fileInformation = new FileInformation( ("Error " + httpResponseStatus.code() + ": " + httpResponseStatus.reasonPhrase()).getBytes(), null );
        }

        this.loadedErrorPages.put( httpResponseStatus, fileInformation );

        return fileInformation;
    }
}
