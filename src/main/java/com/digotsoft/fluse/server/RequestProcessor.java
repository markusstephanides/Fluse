package com.digotsoft.fluse.server;

import com.digotsoft.fluse.server.configuration.Configuration;
import com.digotsoft.fluse.server.files.FileInformation;
import com.digotsoft.fluse.server.files.FileLoader;
import com.digotsoft.fluse.server.routing.RouteContext;
import com.digotsoft.fluse.server.routing.RouteMatcher;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.io.IOException;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author Digot
 * @version 1.0
 */
public class RequestProcessor {

    private final RouteMatcher routeMatcher;
    private final FileLoader fileLoader;
    private final Configuration configuration;

    public RequestProcessor( Configuration configuration ) {
        this.configuration = configuration;
        this.fileLoader = new FileLoader();
        this.routeMatcher = new RouteMatcher( this.configuration.getRoutes() );
    }


    public FullHttpResponse process( HttpRequest request ) throws IOException {
        String uri = request.uri();
        String mimeType = "text/plain";
        byte[] bytes = null;
        HttpResponseStatus status = OK;

        RouteContext routeContext = this.routeMatcher.matchRoute( uri );

        if ( routeContext == null ) {
            // Requested route was not found
            status = NOT_FOUND;
            FileInformation fileInformation = this.fileLoader.loadFile( "error404.html" );

            if ( fileInformation == null ) {
                bytes = "Error 404 - Document not found".getBytes();
            } else {
                bytes = fileInformation.getData();
                mimeType = fileInformation.getMimeType();
            }
        } else {
            // Requested route was found, load the file if it exists
            FileInformation fileInformation = this.fileLoader.loadFileByRoute( routeContext );

            if ( fileInformation == null ) {
                // Requested file was not found
                status = NOT_FOUND;
                fileInformation = this.fileLoader.loadFile( "error404.html" );
                if ( fileInformation == null ) {
                    bytes = "Error 404 - Document not found".getBytes();
                } else {
                    bytes = fileInformation.getData();
                    mimeType = fileInformation.getMimeType();
                }
            } else {
                bytes = fileInformation.getData();
                mimeType = fileInformation.getMimeType();
            }

        }


        FullHttpResponse response = new DefaultFullHttpResponse( HTTP_1_1, OK, Unpooled.wrappedBuffer( bytes ) );
        response.headers().set( CONTENT_TYPE, mimeType );
        response.setStatus( status );
        response.headers().setInt( CONTENT_LENGTH, response.content().readableBytes() );

        return response;
    }
}
