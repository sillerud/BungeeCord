package net.md_5.bungee.netty;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.netty.packetrewriter.*;
import net.md_5.bungee.protocol.packet.DefinedPacket;
import net.md_5.bungee.protocol.packet.protocolhack.*;

public class PacketMapping {
    /**
     * Client packet mapping - packets from client to server
      */
    public static short[] cpm = new short[ 0x18 ];
    /**
     * Server packet mapping - packets from server to client
      */
    public static int[] spm = new int[ 0xFF + 1 ];

    /**
     * Re-writers for packets
      */
    public static PacketRewriter[] rewriters = new PacketRewriter[ 0xFF + 1 ];

    /**
     * Animation mappings, 1.6.4->1.7.2
     */
    public static byte[] animations = new byte[ 106 ];

    /**
     * Statistics mapping, 1.6.4->1.7.2
     */
    public static String[] statistics = new String[ 2029 ];

    public static int supported16Start = 74;
    public static int supported16End = 80;

    public static int supported17Start = 1;
    public static int supported17End = 5;

    public static short[] itemIdMapping = new short[ Short.MAX_VALUE ];

    static
    {
        // Client mappings
        cpm[ 0x00 ] = 0x00;
        cpm[ 0x01 ] = 0x03;
        cpm[ 0x02 ] = 0x07;
        cpm[ 0x03 ] = 0x0A;
        cpm[ 0x04 ] = 0x0B;
        cpm[ 0x05 ] = 0x0C;
        cpm[ 0x06 ] = 0x0D;
        cpm[ 0x07 ] = 0x0E;
        cpm[ 0x08 ] = 0x0F;
        cpm[ 0x09 ] = 0x10;
        cpm[ 0x0A ] = 0x12;
        cpm[ 0x0B ] = 0x13;
        cpm[ 0x0C ] = 0x1B;
        cpm[ 0x0D ] = 0x65;
        cpm[ 0x0E ] = 0x66;
        cpm[ 0x0F ] = 0x6A;
        cpm[ 0x10 ] = 0x6B;
        cpm[ 0x11 ] = 0x6C;
        cpm[ 0x12 ] = 0x82;
        cpm[ 0x13 ] = 0xCA;
        cpm[ 0x14 ] = 0xCB;
        cpm[ 0x15 ] = 0xCC;
        cpm[ 0x16 ] = 0xCD;
        cpm[ 0x17 ] = 0xFA;

        // Server mappings
        spm[ 0x00 ] = 0x00;
        spm[ 0x01 ] = 0x01;
        spm[ 0x03 ] = 0x02;
        spm[ 0x04 ] = 0x03;
        spm[ 0x05 ] = 0x04;
        spm[ 0x06 ] = 0x05;
        spm[ 0x08 ] = 0x06;
        spm[ 0x09 ] = 0x07;
        spm[ 0x0D ] = 0x08;
        spm[ 0x10 ] = 0x09;
        spm[ 0x11 ] = 0x0A;
        spm[ 0x12 ] = 0x0B;
        spm[ 0x14 ] = 0x0C;
        spm[ 0x16 ] = 0x0D;
        spm[ 0x17 ] = 0x0E;
        spm[ 0x18 ] = 0x0F;
        spm[ 0x19 ] = 0x10;
        spm[ 0x1A ] = 0x11;
        spm[ 0x1C ] = 0x12;
        spm[ 0x1D ] = 0x13;
        spm[ 0x1E ] = 0x14;
        spm[ 0x1F ] = 0x15;
        spm[ 0x20 ] = 0x16;
        spm[ 0x21 ] = 0x17;
        spm[ 0x22 ] = 0x18;
        spm[ 0x23 ] = 0x19;
        spm[ 0x26 ] = 0x1A;
        spm[ 0x27 ] = 0x1B;
        spm[ 0x28 ] = 0x1C;
        spm[ 0x29 ] = 0x1D;
        spm[ 0x2A ] = 0x1E;
        spm[ 0x2B ] = 0x1F;
        spm[ 0x2C ] = 0x20;
        spm[ 0x33 ] = 0x21;
        spm[ 0x34 ] = 0x22;
        spm[ 0x35 ] = 0x23;
        spm[ 0x36 ] = 0x24;
        spm[ 0x37 ] = 0x25;
        spm[ 0x38 ] = 0x26;
        spm[ 0x3C ] = 0x27;
        spm[ 0x3D ] = 0x28;
        spm[ 0x3E ] = 0x29;
        spm[ 0x3F ] = 0x2A;
        spm[ 0x46 ] = 0x2B;
        spm[ 0x47 ] = 0x2C;
        spm[ 0x64 ] = 0x2D;
        spm[ 0x65 ] = 0x2E;
        spm[ 0x67 ] = 0x2F;
        spm[ 0x68 ] = 0x30;
        spm[ 0x69 ] = 0x31;
        spm[ 0x6A ] = 0x32;
        spm[ 0x82 ] = 0x33;
        spm[ 0x83 ] = 0x34;
        spm[ 0x84 ] = 0x35;
        spm[ 0x85 ] = 0x36;
        spm[ 0xC8 ] = 0x37;
        spm[ 0xC9 ] = 0x38;
        spm[ 0xCA ] = 0x39;
        spm[ 0xCB ] = 0x3A;
        spm[ 0xCE ] = 0x3B;
        spm[ 0xCF ] = 0x3C;
        spm[ 0xD0 ] = 0x3D;
        spm[ 0xD1 ] = 0x3E;
        spm[ 0xFA ] = 0x3F;
        spm[ 0xFC ] = 0x02;
        spm[ 0xFD ] = 0x01;
        spm[ 0xFF ] = 0x40;

        animations[ 1 ] = 0;
        animations[ 2 ] = 1;
        animations[ 3 ] = 2;
        animations[ 5 ] = 3;
        animations[ 6 ] = 4;
        animations[ 7 ] = 5;
        animations[ 102 ] = 102;
        animations[ 104 ] = 104;
        animations[ 105 ] = 105;

        statistics[ 1004 ] = "stat.leaveGame";
        statistics[ 1100 ] = "stat.playOneMinute";
        statistics[ 2000 ] = "stat.walkOneCm";
        statistics[ 2001 ] = "stat.swimOneCm";
        statistics[ 2002 ] = "stat.fallOneCm";
        statistics[ 2003 ] = "stat.climbOneCm";
        statistics[ 2004 ] = "stat.flyOneCm";
        statistics[ 2005 ] = "stat.diveOneCm";
        statistics[ 2006 ] = "stat.minecartOneCm";
        statistics[ 2007 ] = "stat.boatOneCm";
        statistics[ 2008 ] = "stat.pigOneCm"; // Not on minecraftwiki
        statistics[ 2009 ] = "stat.horseOneCm"; // Same with this one
        statistics[ 2010 ] = "stat.jump";
        statistics[ 2011 ] = "stat.drop";
        statistics[ 2020 ] = "stat.damageDealt";
        statistics[ 2021 ] = "stat.damageTaken";
        statistics[ 2022 ] = "stat.deaths";
        statistics[ 2023 ] = "stat.mobKills";
        statistics[ 2024 ] = "stat.playerKills";
        statistics[ 2025 ] = "stat.fishCaught";
        statistics[ 2026 ] = "stat.junkFished";
        statistics[ 2027 ] = "stat.treasureFished";
        statistics[ 2028 ] = "stat.animalsBred"; // Not on wiki, and no really fitting ids

        itemIdMapping[ 26 ] = 355; // Bed
        itemIdMapping[ 34 ] = 33; // Piston "arm"
        itemIdMapping[ 36 ] = 33; // Piston moving
        itemIdMapping[ 55 ] = 331; // Redstone, maybe?
        itemIdMapping[ 59 ] = 295; // Crops
        itemIdMapping[ 63 ] = 323; // Sign
        itemIdMapping[ 64 ] = 324; // Wooden door
        itemIdMapping[ 68 ] = 323; // Sign
        itemIdMapping[ 71 ] = 330; // Iron door
        itemIdMapping[ 74 ] = 73; // Glowing redstone ore
        itemIdMapping[ 75 ] = 76; // Redstone torch in off-mode
        itemIdMapping[ 83 ] = 338; // Sugarcanes
        itemIdMapping[ 92 ] = 354; // Cake! :D
        itemIdMapping[ 93 ] = 356; // Repeater
        itemIdMapping[ 94 ] = 356; // Repeater
        itemIdMapping[ 104 ] = 361; // Pumpkin seeds
        itemIdMapping[ 105 ] = 362; // Melon seeds
        itemIdMapping[ 115 ] = 372; // Nether wart
        itemIdMapping[ 117 ] = 379; // Brewing stand
        itemIdMapping[ 118 ] = 379; // Brewing stand
        itemIdMapping[ 124 ] = 123; // Redstone lamp
        itemIdMapping[ 132 ] = 126; // Wooden slab
        itemIdMapping[ 140 ] = 390; // Flower pot
        itemIdMapping[ 144 ] = 397; // Head
        itemIdMapping[ 149 ] = 404; // Comparator
        itemIdMapping[ 150 ] = 404; // Comparator

        // Re-writers
        rewriters[ 0x01 ] = new JoinGameRewriter();
        //rewriters[ 0x02 ] = new HandshakeRewriter();
        rewriters[ 0x03 ] = new ChatMessageRewriter();
        rewriters[ 0x07 ] = new EntityInteractRewriter();
        rewriters[ 0x09 ] = new RespawnRewriter();
        rewriters[ 0x0D ] = new PositionAndLookRewriter();
        rewriters[ 0x10 ] = new HeldItemChangeRewriter();
        rewriters[ 0x11 ] = new UseBedRewriter();
        rewriters[ 0x12 ] = new AnimationRewriter();
        rewriters[ 0x14 ] = new SpawnPlayerRewriter();
        rewriters[ 0x17 ] = new EntityChangeRewriter();
        rewriters[ 0x18 ] = new SpawnMobRewriter();
        rewriters[ 0x19 ] = new SpawnPaintingRewriter();
        rewriters[ 0x1A ] = new EntityChangeRewriter();
        rewriters[ 0x28 ] = new EntityMetadataRewriter();
        rewriters[ 0x2C ] = new EntityPropertiesRewriter();
        rewriters[ 0x35 ] = new BlockChangeRewriter();
        rewriters[ 0x36 ] = new BlockActionRewriter();
        rewriters[ 0x37 ] = new EntityChangeRewriter();
        rewriters[ 0x3C ] = new ExplosionRewriter();
        rewriters[ 0x3E ] = new SoundEffectRewriter();
        rewriters[ 0x3F ] = new ParticleRewriter();
        rewriters[ 0x46 ] = new ChangeGameStateRewriter();
        rewriters[ 0x47 ] = new EntityChangeRewriter();
        rewriters[ 0x64 ] = new WindowOpenRewriter();
        rewriters[ 0x67 ] = new SetSlotRewriter();
        rewriters[ 0x68 ] = new WindowItemsRewriter();
        rewriters[ 0x82 ] = new UpdateSignRewriter();
        rewriters[ 0x83 ] = new ItemDataRewriter();
        rewriters[ 0x85 ] = new SignEditorRewriter();
        rewriters[ 0xC8 ] = new StatisticsRewriter();
        rewriters[ 0xC9 ] = new PlayerListItemRewriter();
        rewriters[ 0xCB ] = new TabCompleteRewriter();
        rewriters[ 0xCC ] = new ClientSettingsRewriter();
        rewriters[ 0xCD ] = new ClientStatusRewriter();
        rewriters[ 0xCE ] = new ScoreboardObjectiveRewriter();
        rewriters[ 0xCF ] = new UpdateScoreRewriter();
        rewriters[ 0xD0 ] = new ShowScoreboardRewriter();
        rewriters[ 0xD1 ] = new TeamsRewriter();
        rewriters[ 0xFA ] = new PluginMessageRewriter();
        rewriters[ 0xFC ] = new EncryptionResponseRewriter();
        rewriters[ 0xFD ] = new EncryptionRequestRewriter();
        rewriters[ 0xFF ] = new DisconnectRewriter();
    }

    public static DefinedPacket readInitialPacket(int packetId, int state, ByteBuf buf)
    {
        DefinedPacket packet;
        if ( state == 0 )
        {
            packet = new PacketHandshake();
        } else if ( state == 1 )
        {
            if ( packetId == 0x00 )
            {
                packet = new PacketPingRequest();
            } else if ( packetId == 0x01 )
            {
                packet = new PacketPing();
            } else
            {
                return null;
            }
        } else if ( state == 2 )
        {
            if ( packetId == 0x00 )
            {
                packet = new PacketLoginStart();
            } else if ( packetId == 0x01 )
            {
                packet = new PacketEncryptionResponse();
            } else
            {
                return null;
            }
        } else
        {
            return null;
        }
        packet.read( buf );
        return packet;
    }
}
