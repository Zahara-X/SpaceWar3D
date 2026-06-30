package org.example.spacewar3d.manager;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ManagerNetty {
     private final Queue<ByteBuf> mouse = new LinkedBlockingQueue<>();

     private Channel channel;

     public void writeMouse(int mouseX, int mouseY) {
          ByteBuf buf = channel.alloc().ioBuffer(13);
          buf.writeByte(1);
          buf.writeInt(9);
          buf.writeInt(mouseX);
          buf.writeInt(mouseY);
          mouse.add(buf);
     }
     public void writingQueue() {
         if(!mouse.isEmpty()) {
             ByteBuf buf = mouse.poll();
             if(buf != null ) {
                  channel.write(buf);
             }
             channel.flush();
         }
     }
     public void setChannel(Channel channel) {
          this.channel = channel;
     }
     public Channel getChannel() {
          return this.channel;
     }
}