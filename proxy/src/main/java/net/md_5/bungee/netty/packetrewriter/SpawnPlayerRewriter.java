package net.md_5.bungee.netty.packetrewriter;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.Var;
import com.google.common.base.Charsets;
import io.netty.channel.Channel;
import java.util.UUID;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.connection.LoginResult;

public class SpawnPlayerRewriter extends PacketRewriter
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
        String name = Var.readString( in, false );
        Protocol4Rewrite( in, out, entityId, name );
    }

    public void rewriteServerToClient(ByteBuf in, ByteBuf out, Channel ch)
    {
        int entityId = in.readInt();
        String name = Var.readString( in, false );
        
        UserConnection target = (UserConnection) BungeeCord.getInstance().getPlayer( ch );

        if ( target != null && target.getProtocolVersion() >= 5 )
        {
            Protocol5RewriteOnline( in, out, entityId, name );
        } else
        {
            Protocol4Rewrite( in, out, entityId, name );
        }
    }

    public void Protocol4Rewrite(ByteBuf in, ByteBuf out, int entityId, String name)
    {
        Var.writeVarInt( entityId, out );
        Var.writeString( java.util.UUID.nameUUIDFromBytes( ( name ).getBytes( Charsets.UTF_8 ) ).toString(), out, true );
        Var.writeString( name, out, true );
        out.writeBytes( in.readBytes( 16 ) ); // int - x, int - y, int - z, byte - yaw, byte - pitch, short - item
        Var.rewriteEntityMetadata( in, out );
    }

    public void Protocol5RewriteOnline(ByteBuf in, ByteBuf out, int entityId, String name)
    {
        UserConnection player = (UserConnection) BungeeCord.getInstance().getPlayer( name );
        if ( player != null )
        {
            LoginResult profile = player.getPendingConnection().getLoginProfile();
            if ( profile != null && profile.getProperties() != null && profile.getProperties().length >= 1 )
            {
                Var.writeVarInt( entityId, out );
                Var.writeString( player.getPendingConnection().getUniqueId().toString(), out, true );
                Var.writeString( name, out, true );
                Var.writeVarInt( profile.getProperties().length, out );
                for ( LoginResult.Property property : profile.getProperties() )
                {
                    Var.writeString( property.getName(), out, true );
                    Var.writeString( property.getValue(), out, true );
                    Var.writeString( property.getSignature(), out, true );
                }
                out.writeBytes( in.readBytes( 16 ) ); // int - x, int - y, int - z, byte - yaw, byte - pitch, short - item
                Var.rewriteEntityMetadata( in, out );
            } else
            {
                // Offline Player
                Protocol5RewriteOffline( in, out, entityId, name );
            }
        } else
        {
            // Offline Player
            Protocol5RewriteOffline( in, out, entityId, name );
        }
    }

    public void Protocol5RewriteOffline(ByteBuf in, ByteBuf out, int entityId, String name)
    {
        Var.writeVarInt( entityId, out );
        Var.writeString( getRandomUUID(), out, true );
        Var.writeString( name, out, true );
        Var.writeVarInt( 0, out );
        out.writeBytes( in.readBytes( 16 ) ); // int - x, int - y, int - z, byte - yaw, byte - pitch, short - item
        Var.rewriteEntityMetadata( in, out );
    }

    public static String getRandomUUID()
    {
        UUID uuid = UUID.randomUUID();
        return ( new UUID( uuid.getMostSignificantBits() | 0x0000000000005000L, uuid.getLeastSignificantBits() ) ).toString();
    }

}
