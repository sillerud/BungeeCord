package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class BlockChangeRewriter extends PacketRewriter
{

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out)
    {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out)
    {
        // int - x, byte - y, int - z
        // 4(int) + 1(byte) + 4(int) = total 9 bytes
        out.writeBytes( in.readBytes( 9 ) );
        short blockType = in.readShort();
        Var.writeVarInt( blockType, out );
        out.writeBytes( in.readBytes( 1 ) ); // Block data
    }

}
