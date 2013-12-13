package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class SpawnMobRewriter extends PacketRewriter
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
        Var.writeVarInt( entityId, out );

        /*
         byte - type, int - x, int - y, int - z, byte - pitch, byte - headpitch, byte - yaw
         short velocity x, short velocity y, short velocity z
         1(byte) + 4(int) + 4(int) + 4(int) + 1(byte) + 1(byte) + 1(byte) + 2(short) + 2(short) + 2(short) = total 18 bytes
          */
        out.writeBytes( in.readBytes( 22 ) );

        Var.rewriteEntityMetadata( in, out );
    }

}
