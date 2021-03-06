package minechem.network;

import minechem.ModMinechem;
import minechem.item.chemistjournal.ChemistJournalPacketActiveItem;
import minechem.tileentity.decomposer.DecomposerPacketUpdate;
import minechem.tileentity.multiblock.ghostblock.GhostBlockPacket;
import minechem.tileentity.synthesis.SynthesisPacketUpdate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

public abstract class MinechemPackets
{
    public static class ProtocolException extends Exception
    {

        public ProtocolException()
        {
        }

        public ProtocolException(String message)
        {
            super(message);
        }

        public ProtocolException(String message, Throwable cause)
        {
            super(message, cause);
        }

        public ProtocolException(Throwable cause)
        {
            super(cause);
        }
    }

    private static final BiMap<Integer, Class<? extends MinechemPackets>> idMap;

    static
    {
        ImmutableBiMap.Builder<Integer, Class<? extends MinechemPackets>> builder = ImmutableBiMap.builder();

        // Chemists Journal
        builder.put(Integer.valueOf(0), ChemistJournalPacketActiveItem.class);

        // Chemical Decomposer Machine
        builder.put(Integer.valueOf(1), DecomposerPacketUpdate.class);

        // Fusion Reactor Ghost Blocks
        builder.put(Integer.valueOf(2), GhostBlockPacket.class);

        // Chemical Synthesis Machine
        builder.put(Integer.valueOf(3), SynthesisPacketUpdate.class);

        idMap = builder.build();
    }

    public static MinechemPackets constructPacket(int packetId) throws ProtocolException, ReflectiveOperationException
    {
        Class<? extends MinechemPackets> clazz = idMap.get(Integer.valueOf(packetId));
        if (clazz == null)
        {
            throw new ProtocolException("Unknown Packet Id!");
        }
        else
        {
            return clazz.newInstance();
        }
    }

    public abstract void execute(EntityPlayer player, Side side) throws ProtocolException;

    public final int getPacketId()
    {
        if (idMap.inverse().containsKey(getClass()))
        {
            return idMap.inverse().get(getClass()).intValue();
        }
        else
        {
            throw new RuntimeException("Packet " + getClass().getSimpleName() + " is missing a mapping!");
        }
    }

    public final Packet makePacket()
    {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeByte(getPacketId());
        write(out);
        return PacketDispatcher.getPacket(ModMinechem.CHANNEL_NAME, out.toByteArray());
    }

    public abstract void read(ByteArrayDataInput in) throws ProtocolException;

    public abstract void write(ByteArrayDataOutput out);
}