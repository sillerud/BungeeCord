package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;

public class EntityInteractRewriter extends PacketRewriter
{

    byte left = 1;
    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out)
    {
        out.writeInt( 0 ); // Should be ignored by server, its from when the player sent their ID to the server
        out.writeBytes( in.readBytes( 4 ) ); // int - target
        byte button = in.readByte();
        out.writeBoolean( button == left );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out)
    {
        unsupported( false );
    }
}
