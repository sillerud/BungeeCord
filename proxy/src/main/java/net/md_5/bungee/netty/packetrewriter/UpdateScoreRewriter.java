package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class UpdateScoreRewriter extends PacketRewriter {

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out)
    {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out)
    {
        String itemName = Var.readString( in, false );
        Var.writeString( itemName, out, true );

        byte updateRemove = in.readByte();
        out.writeByte( updateRemove );

        String scoreboardName = Var.readString( in, false );
        Var.writeString( scoreboardName, out, true );

        out.writeBytes(in.readBytes(4)); // int - value
    }

}
