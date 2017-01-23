package com.digotsoft.fluse.server.http;

import com.digotsoft.fluse.server.configuration.Configuration;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Created by Markus on 18.01.2017.
 */
public class HttpServer {

    public HttpServer(final Configuration configuration){

        Logger.getLogger("io.netty").setLevel(Level.OFF);

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, configuration.getPort());
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline channelPipeline = socketChannel.pipeline();
                            channelPipeline.addLast(new HttpServerCodec());
                            channelPipeline.addLast(new HttpServerHandler(configuration));
                        }
                    });

            Channel ch = b.bind(configuration.getPort()).sync().channel();

            System.err.println("Open your web browser and navigate to " +
                    ("http") + "://127.0.0.1:" + configuration.getPort() + '/');

            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
