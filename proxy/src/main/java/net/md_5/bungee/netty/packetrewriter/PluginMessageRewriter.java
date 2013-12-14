package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.md_5.bungee.netty.Var;

public class PluginMessageRewriter extends PacketRewriter
{

	@Override
	public void rewriteClientToServer(ByteBuf in, ByteBuf out)
	{
		String channel = Var.readString( in, true );
		short length = in.readShort();
		ByteBuf bytes = in.readBytes( length );
		
		// Command Block Rewrite
		if ( channel.equals( "MC|AdvCdm" ) ) {
			if ( bytes.readByte() != 0 ) return;
			ByteBuf buf = Unpooled.buffer();
			
			// x - int, y - int, z - int
			// 4(int) + 4(int) + 4(int) = 12 total
			buf.writeBytes( bytes.readBytes( 12 ) );
			Var.writeString( Var.readString( bytes, true ), buf, false );
			
			bytes = buf;
			length = (short) bytes.readableBytes();
		}
		
		Var.writeString( channel, out, false );
		out.writeShort( length );
		out.writeBytes( bytes );
	}

	@Override
	public void rewriteServerToClient(ByteBuf in, ByteBuf out)
	{
		String channel = Var.readString( in, false );
		short length = in.readShort();
		ByteBuf bytes = in.readBytes( length );
		
		// Command Block Rewrite
		if ( channel.equals( "MC|AdvCdm" ) ) {
			ByteBuf buf = Unpooled.buffer();
			
			// x - int, y - int, z - int
			// 4(int) + 4(int) + 4(int) = 12
			buf.writeByte( 0 );
			buf.writeBytes( bytes.readBytes( 12 ) );
			Var.writeString( Var.readString( bytes, false ), buf, true );
			
			bytes = buf;
			length = (short) bytes.readableBytes();
		}
		
		Var.writeString( channel, out, true );
		out.writeShort( length );
		out.writeBytes( bytes );
	}

}
