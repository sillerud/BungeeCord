package net.md_5.bungee.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the standard list data returned by opening a server in the
 * Minecraft client server list, or hitting it with a packet 0xFE. (copied from
 * BungeeCord 1.7.2; representing the new ping method)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewServerPing
{

    private Protocol version;

    @Data
    @AllArgsConstructor
    public static class Protocol
    {

        private String name;
        private int protocol;
    }
    private Players players;

    @Data
    @AllArgsConstructor
    public static class Players
    {

        private int max;
        private int online;
    }
    private String description;
    private String favicon;

    /**
     * Converts this object to a {@link ServerPing}, as used by 1.6.4 clients.
     *
     * @return A {@link ServerPing} object with all data contained in this
     * object. Changes are not written back.
     */
    public ServerPing toServerPing()
    {
        return new ServerPing( (byte) this.version.getProtocol(), //It will take a while until the protocol version strikes 127
                this.version.getName(),
                this.description,
                this.players.getOnline(),
                this.players.getMax() );
    }

    /**
     * Converts this object to a {@link JsonObject}, as accepted by the
     * Minecrfat client in the 1.7.2 ping process and as seen in
     * http://wiki.vg/Server_List_Ping#1.7 .
     *
     * @return A {@link JsonObject} containing this object's data.
     */
    public JsonObject toJson()
    {
        JsonObject json = new JsonObject();
        JsonObject jsonVersion = new JsonObject();
        JsonObject jsonPlayers = new JsonObject();
        jsonVersion.add( "name", new JsonPrimitive( version.getName() ) );
        jsonVersion.add( "protocol", new JsonPrimitive( version.getProtocol() ) );
        jsonPlayers.add( "max", new JsonPrimitive( players.getMax() ) );
        jsonPlayers.add( "online", new JsonPrimitive( players.getOnline() ) );
        jsonPlayers.add( "sample", new JsonArray() ); // empty array
        json.add( "version", jsonVersion );
        json.add( "players", jsonPlayers );
        json.add( "description", new JsonPrimitive( description.replaceAll( "\\\\n", "\n" ) ) );
        if ( favicon != null )
        {
            json.add( "favicon", new JsonPrimitive( favicon ) );
        }

        return json;
    }
}
