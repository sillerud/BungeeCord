package net.md_5.bungee.api;

import lombok.Data;

/**
 * Represents the standard list data returned by opening a server in the
 * Minecraft client server list, or hitting it with a packet 0xFE.
 */
@Data
public class ServerPing
{

    /**
     * Numeric protocol version supported by the server.
     */
    private final byte protocolVersion;
    /**
     * Human readable game version.
     */
    private final String gameVersion;
    /**
     * Server MOTD.
     */
    private final String motd;
    /**
     * Current amount of players on the server.
     */
    private final int currentPlayers;
    /**
     * Max amount of players the server will allow.
     */
    private final int maxPlayers;

    /**
     * Converts this object to a {@link NewServerPing}, as used by 1.7.2
     * clients.
     *
     * @return A {@link NewServerPing} object with all data contained in this
     * object. Changes are not written back.
     */
    public NewServerPing toNewServerPing()
    {
        return new NewServerPing(
                new NewServerPing.Protocol( gameVersion, protocolVersion ),
                new NewServerPing.Players( maxPlayers, currentPlayers ),
                motd, null );
    }
}
