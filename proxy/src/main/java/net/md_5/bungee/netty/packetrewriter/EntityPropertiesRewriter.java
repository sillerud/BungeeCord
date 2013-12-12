package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class EntityPropertiesRewriter extends PacketRewriter
{
    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out)
    {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out)
    {
        // int - entityid, 4 bytes
        out.writeBytes( in.readBytes( 4 ) );
        int propertyCount = in.readInt();
        out.writeInt( propertyCount );
        for ( int i = 0; i < propertyCount; i++ )
        {
            String key = Var.readString( in, false );
            Var.writeString( key, out, true );
            // double - value
            // 8(double) = total 8 bytes
            out.writeBytes( in.readBytes( 8 ) );
            short length = in.readShort();
            out.writeShort( length );
            for ( int j = 0; j < length; j++ )
            {
                // mojang UUID - uuid, double - amount, byte - operation
                // 16(UUID) + 8(double) + 1(byte) = total 25 bytes
                out.writeBytes( in.readBytes( 25 ) );
            }
        }
    }
}
