package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class BlockActionRewriter extends PacketRewriter
{

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out)
    {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out)
    {
        // x - int, y - short, z - int, 2 bytes - data
        // 4(int) + 2(short) + 1(byte) + 1(byte) = 8 total
        out.writeBytes( in.readBytes( 8 ) );

        int blockType = in.readShort();
        Var.writeVarInt( blockType, out );
    }

}
