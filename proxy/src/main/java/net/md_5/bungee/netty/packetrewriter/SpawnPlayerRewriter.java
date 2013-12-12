package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class SpawnPlayerRewriter extends PacketRewriter
{

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out)
    {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out)
    {
        int entityId = in.readInt();
        String name = Var.readString( in, false );
        Var.writeVarInt( entityId, out );
        Var.writeString( "wowe_such_packet", out, true );
        Var.writeString( name, out, true );

        out.writeBytes( in.readBytes( 16 ) ); // int - x, int - y, int - z, byte - yaw, byte - pitch, short - item

        Var.rewriteEntityMetadata( in, out );
    }

}
