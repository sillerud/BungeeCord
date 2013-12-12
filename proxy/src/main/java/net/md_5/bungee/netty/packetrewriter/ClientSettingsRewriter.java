package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class ClientSettingsRewriter extends PacketRewriter
{

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out)
    {
        String locale = Var.readString( in, true );
        Var.writeString( locale, out, false );

        // viewdistance - byte, chatflags - byte
        // 1(byte) + 1(byte) = total 2 bytes
        out.writeBytes( in.readBytes( 2 ) );
        in.skipBytes( 1 ); // boolean

        // difficulty - byte, cape - boolean
        // 1(byte) + 1(boolean) = total 2 bytes
        out.writeBytes( in.readBytes( 2 ) );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out)
    {
        unsupported( false );
    }

}
