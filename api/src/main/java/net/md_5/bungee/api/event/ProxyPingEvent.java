package net.md_5.bungee.api.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.md_5.bungee.api.NewServerPing;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.plugin.Event;

/**
 * Called when the proxy is pinged with packet 0xFE from the server list.
 */
@Data
@AllArgsConstructor //kept for completeness
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class ProxyPingEvent extends Event
{

    /**
     * The connection asking for a ping response.
     */
    private final PendingConnection connection;
    /**
     * The data to respond with. (for 1.6.4 pings; filled in at 1.7.2 for
     * conveinience)
     */
    private ServerPing response;
    /**
     * 1.7.2 response, if this event was caused by 1.7.2 ping.
     */
    private NewServerPing newResponse;
    /**
     * Specifies if this ping event was caused by a 1.7.2 client.
     */
    private boolean isNewProtocol;
    /**
     * The Playerlist to send. (for 1.7 pings)
     */
    private String[] players;

    public ProxyPingEvent(final PendingConnection connection, final ServerPing response)
    {
        this.connection = connection;
        this.response = response;
        this.newResponse = null;
        this.isNewProtocol = false;
        this.players = null;
    }

    public ProxyPingEvent(final PendingConnection connection, final NewServerPing newResponse)
    {
        this.connection = connection;
        this.response = newResponse.toServerPing();
        this.newResponse = newResponse;
        this.isNewProtocol = true;
        this.players = newResponse.getPlayers().getSample();
    }

    public void setResponse(final ServerPing resp)
    {
        this.response = resp;
        if ( this.isNewProtocol )
        {
            NewServerPing newResponse = resp.toNewServerPing();
            newResponse.setFavicon( this.newResponse.getFavicon() );
            this.newResponse = newResponse;
        }
    }
}
