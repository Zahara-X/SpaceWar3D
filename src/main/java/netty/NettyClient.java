package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class NettyClient {
    public NioEventLoopGroup group;
    public Bootstrap bootstrap;
    private final int size = 32 * 1024; // 32kb
    public NettyClient() {
        this.group = new NioEventLoopGroup(1);
        try {
            Bootstrap strap = new Bootstrap();
            strap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_RCVBUF, size)
                    .option(ChannelOption.SO_SNDBUF, size)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(size, 4, 0, 4, 0));
                        }
                    });
            this.bootstrap = strap;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}