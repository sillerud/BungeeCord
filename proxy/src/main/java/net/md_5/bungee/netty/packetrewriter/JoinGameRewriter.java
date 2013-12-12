package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;

public class JoinGameRewriter extends PacketRewriter
{

    /*
    Packet ID 	    Field Name      Field Type
    0x01 	        Entity ID 	    int
                    Level type 	    string
                    Game mode 	    byte
                    Dimension 	    byte
                    Difficulty 	    byte
                    Not used 	    byte
                    Max players 	byte
                    Total Size: 	12 bytes + length of strings

    Packet ID       Entity ID       Int
    0x01            Gamemode        Unsigned Byte
                    Dimension       Byte
                    Difficulty 	    Unsigned Byte
                    Max Players     Unsigned Byte
                    Level Type 	    String
     */

    @Override
    public void rewriteClientToServer(ByteBuf in, ByteBuf out)
    {
        unsupported( true );
    }

    @Override
    public void rewriteServerToClient(ByteBuf in, ByteBuf out)
    {
        // entityid - int
        // 4 bytes
        out.writeBytes( in.readBytes( 4 ) );
        String levelType = Var.readString( in, false );
        // gamemode - byte, dimention - byte, difficulty
        // 1(byte) + 1(byte) + 1(byte) = total 3 bytes
        out.writeBytes( in.readBytes( 3 ) );
        in.skipBytes( 1 );

        out.writeBytes( in.readBytes( 1 ) ); // maxPlayers

        Var.writeString( levelType, out, true );
    }

}
