package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class ItemDataRewriter extends PacketRewriter
{

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out)
    {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out)
    {
        in.skipBytes( 2 ); // Short - itemtype
        short itemDamage = in.readShort();
        Var.writeVarInt(itemDamage, out);
        out.writeBytes( in.readBytes( in.readableBytes() ) );
    }

}
