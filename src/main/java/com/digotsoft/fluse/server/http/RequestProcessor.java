package com.digotsoft.fluse.server.http;

import com.digotsoft.fluse.server.configuration.Configuration;
import com.digotsoft.fluse.server.error.ErrorPageProvider;
import com.digotsoft.fluse.server.files.FileInformation;
import com.digotsoft.fluse.server.files.FileLoader;
import com.digotsoft.fluse.server.routing.RouteContext;
import com.digotsoft.fluse.server.routing.RouteMatcher;
import com.digotsoft.fluse.server.routing.RouteType;
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
    private final Configuration configuration;
    private final FileLoader fileLoader;
    private final ErrorPageProvider errorPageProvider;

    public RequestProcessor( Configuration configuration ) {
        this.configuration = configuration;
        this.fileLoader = new FileLoader();
        this.errorPageProvider = new ErrorPageProvider();
        this.routeMatcher = new RouteMatcher( this.configuration.getRoutes() );
    }


    public FullHttpResponse process( HttpRequest request ) throws IOException {
        String uri = request.uri();
        String mimeType = "text/html";
        byte[] bytes = null;
        HttpResponseStatus status = OK;

        RouteContext routeContext = this.routeMatcher.matchRoute( uri );

        if ( routeContext == null ) {
            // Requested route was not found
            status = NOT_FOUND;
        } else {
            if( routeContext.getRoute().getType() == RouteType.WILDCARD ) {
                // Requested route was found, load the file if it exists
                FileInformation fileInformation = this.fileLoader.loadFileByRoute( routeContext );

                if ( fileInformation == null ) {
                    // Requested file was not found
                    status = NOT_FOUND;
                } else {
                    bytes = fileInformation.getData();
                    mimeType = fileInformation.getMimeType();
                }
            }
        }

        if(status != OK){
            FileInformation fileInformation = this.errorPageProvider.retrievePage( status );
            bytes = fileInformation.getData();
            mimeType = ( fileInformation.getMimeType() != null ? fileInformation.getMimeType() : mimeType);
        }


        FullHttpResponse response = new DefaultFullHttpResponse( HTTP_1_1, status, Unpooled.wrappedBuffer( bytes ) );
        response.headers().set( CONTENT_TYPE, mimeType );
        response.headers().setInt( CONTENT_LENGTH, response.content().readableBytes() );

        return response;
    }
}
