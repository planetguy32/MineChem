package minechem.tileentity.multiblock.ghostblock;

import minechem.item.blueprint.MinechemBlueprint;
import minechem.network.MinechemPackets;
import minechem.network.MinechemPackets.ProtocolException;
import net.minecraft.entity.player.EntityPlayer;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class GhostBlockPacket extends MinechemPackets
{
    /** X coordinates of tile entity source. */
    private int tilePosX;
    
    /** Y coordinates of tile entity source. */
    private int tilePosY;
    
    /** Z coordinates of tile entity source. */
    private int tilePosZ;
    
    /** Client only reference to our block. */
    private GhostBlockTileEntity tileEntity;
    
    /** Stores the blueprint ID for the type of machine that we are to be. */
    private int blueprintID;
    
    /** Reference number for which ghost block in the total machine layout this makes. */
    private int ghostBlockID;

    public GhostBlockPacket(GhostBlockTileEntity ghostBlock)
    {
        // World position information.
        tilePosX = ghostBlock.xCoord;
        tilePosY = ghostBlock.yCoord;
        tilePosZ = ghostBlock.zCoord;
        
        // Ghost block specific information.
        this.blueprintID = ghostBlock.getBlueprint().id;
        this.ghostBlockID = ghostBlock.getBlockID();
    }

    public GhostBlockPacket()
    {
        // Required for reflection.
    }

    @Override
    public void execute(EntityPlayer player, Side side) throws ProtocolException
    {
        // Grab and populate our reference to the ghost block in the world.
        this.tileEntity = (GhostBlockTileEntity) player.worldObj.getBlockTileEntity(tilePosX, tilePosY, tilePosZ);
        if (tileEntity == null)
        {
            return;
        }
        
        // Set the ghost block specific information onto the client block.
        MinechemBlueprint blueprint = MinechemBlueprint.blueprints.get(this.blueprintID);
        this.tileEntity.setBlueprint(blueprint);
        this.tileEntity.setBlockID(this.ghostBlockID);
    }

    @Override
    public void read(ByteArrayDataInput in) throws ProtocolException
    {
        // World coordinate information.
        this.tilePosX = in.readInt();
        this.tilePosY = in.readInt();
        this.tilePosZ = in.readInt();
        
        // Ghost block specific.
        this.blueprintID = in.readInt();
        this.ghostBlockID = in.readInt();
    }

    @Override
    public void write(ByteArrayDataOutput out)
    {
        // World coordinate information.
        out.writeInt(this.tilePosX);
        out.writeInt(this.tilePosY);
        out.writeInt(this.tilePosZ);
        
        // Ghost block specific.
        out.writeInt(this.blueprintID);
        out.writeInt(this.ghostBlockID);
    }
}
