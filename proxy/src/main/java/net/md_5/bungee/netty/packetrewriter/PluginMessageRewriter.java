package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class PluginMessageRewriter extends PacketRewriter
{

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out)
    {
        String channel = Var.readString(in, true);
        Var.writeString( channel, out, false );

        short length = in.readShort();
        out.writeShort(length);

        out.writeBytes( in.readBytes( length ) );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out)
    {
        String channel = Var.readString( in, false );
        Var.writeString( channel, out, true );
        out.writeBytes( in.readBytes( in.readableBytes() ) );
    }

}
