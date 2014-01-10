package net.md_5.bungee.protocol.packet.protocolhack;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.packet.AbstractPacketHandler;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class PacketKick extends Defined172Packet
{
    private String reason;
    
    public PacketKick(String reason)
    {
        super( 0x00 );
        this.reason = reason;
    }

    @Override
    public void read(ByteBuf buf) 
    {
    }

    @Override
    public void write(ByteBuf buf) 
    {
        writeString( "{text:\"" + reason + "\"}", buf, true );
    }

    @Override
    public void handle(AbstractPacketHandler handler) throws Exception 
    {
    }
}
