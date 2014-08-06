package net.md_5.bungee.protocol.packet.protocolhack;

import io.netty.buffer.ByteBuf;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import net.md_5.bungee.protocol.packet.AbstractPacketHandler;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class PacketLoginSuccess extends Defined172Packet
{

    private String uuid;
    private String username;

    public PacketLoginSuccess(String uuid, String username)
    {
        super( 0x02 );
        this.uuid = uuid;
        this.username = username;
    }

    @Override
    public void read(ByteBuf buf)
    {
        uuid = readString( buf, true );
        username = readString( buf, true );
    }

    @Override
    public void write(ByteBuf buf)
    {
        writeString( uuid, buf, true );
        writeString( username, buf, true );
    }

    @Override
    public void handle(AbstractPacketHandler handler) throws Exception
    {
        handler.handle( this );
    }
}
