package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;

public class PositionAndLookRewriter extends PacketRewriter
{
    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out)
    {
        // double - x, double - y, double - stance, double - z, float - yaw, float - pitch, boolean - onground
        // 8(double), 8(double), 8(double), 8(double), 4(float), 4(float), 1(boolean) = total 41 bytes
        // out.writeBytes( in.readBytes( 41 ) );
        out.writeBytes( in.readBytes( in.readableBytes() ) );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out)
    {
        // double - x, double - y = total 16 bytes
        out.writeBytes( in.readBytes( 16 ) );
        in.skipBytes( 8 ); // Ignore stance.
        out.writeBytes( in.readBytes( in.readableBytes() ) );
    }
}
