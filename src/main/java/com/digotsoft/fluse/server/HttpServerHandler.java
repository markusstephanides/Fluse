package com.digotsoft.fluse.server;

import com.digotsoft.fluse.server.configuration.Configuration;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.AsciiString;

import java.io.IOException;

import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpServerHandler extends ChannelInboundHandlerAdapter {

    private final RequestProcessor processor;

    private static final AsciiString CONTENT_TYPE = new AsciiString( "Content-Type" );
    private static final AsciiString CONTENT_LENGTH = new AsciiString( "Content-Length" );
    private static final AsciiString CONNECTION = new AsciiString( "Connection" );
    private static final AsciiString KEEP_ALIVE = new AsciiString( "keep-alive" );

    public HttpServerHandler( Configuration configuration ) {
        this.processor = new RequestProcessor( configuration );
    }

    @Override
    public void channelReadComplete( ChannelHandlerContext ctx ) {
        ctx.flush();
    }

    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws IOException {
        if ( msg instanceof HttpRequest ) {
            HttpRequest req = (HttpRequest) msg;

            if ( HttpUtil.is100ContinueExpected( req ) ) {
                ctx.write( new DefaultFullHttpResponse( HTTP_1_1, CONTINUE ) );
            }


            FullHttpResponse response = this.processor.process( req );

            boolean keepAlive = HttpUtil.isKeepAlive( req );
            if ( !keepAlive ) {
                ctx.write( response ).addListener( ChannelFutureListener.CLOSE );
            } else {
                response.headers().set( CONNECTION, KEEP_ALIVE );
                ctx.write( response );
            }
        }
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) {
        cause.printStackTrace();
        ctx.close();
    }
}