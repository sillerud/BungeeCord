package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;
import com.google.common.base.Charsets;

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
        Var.writeString( java.util.UUID.nameUUIDFromBytes( ( name ).getBytes( Charsets.UTF_8 ) ).toString(), out, true );
        Var.writeString( name, out, true );
        Var.writeVarInt( 1, out );
        Var.writeString( "", out, true );
        Var.writeString( "", out, true );
        Var.writeString( "", out, true );
        out.writeBytes( in.readBytes( 16 ) ); // int - x, int - y, int - z, byte - yaw, byte - pitch, short - item

        Var.rewriteEntityMetadata( in, out );
    }

}
