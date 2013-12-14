package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class TeamsRewriter extends PacketRewriter
{

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out)
    {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out)
    {
        String teamName = Var.readString( in, false );
        Var.writeString( teamName, out, true );

        byte mode = in.readByte();
        out.writeByte( mode );
        if ( mode == 0 || mode == 2 )
        {
            String displayName = Var.readString( in, false );
            String teamPrefix = Var.readString( in, false );
            String teamSuffix = Var.readString( in, false );
            byte friendlyFire = in.readByte();

            Var.writeString( displayName, out, true );
            Var.writeString( teamPrefix, out, true );
            Var.writeString( teamSuffix, out, true );
            out.writeByte( friendlyFire );
        }

        if ( mode == 0 || mode == 3 || mode == 4 )
        {
            short length = in.readShort();
            out.writeShort( length );
            for ( int i = 0; i < length; i++ )
            {
                String user = Var.readString( in, false );
                Var.writeString( user, out, true );
            }
        }
    }

}
