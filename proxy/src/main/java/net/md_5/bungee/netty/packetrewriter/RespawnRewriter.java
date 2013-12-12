package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class RespawnRewriter extends PacketRewriter
{

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out)
    {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out)
    {
        // int - dimention, byte - difficulty, byte - gamemode
        // 4(int) + 1(byte) + 1(byte) = total 6 bytes
        out.writeBytes( in.readBytes( 6 ) );
        in.skipBytes( 2 ); // Ignore world height - short

        String levelType = Var.readString( in, false );
        Var.writeString( levelType, out, true );
    }

}
