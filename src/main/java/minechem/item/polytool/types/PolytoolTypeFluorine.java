package minechem.item.polytool.types;

import minechem.item.element.ElementEnum;
import minechem.item.polytool.PolytoolUpgradeType;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PolytoolTypeFluorine extends PolytoolUpgradeType
{

    public PolytoolTypeFluorine()
    {
        super();
    }

    @Override
    public float getStrVsBlock(ItemStack itemStack, Block block)
    {

        return 0;
    }

    @Override
    public void hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
    {
    }

    @Override
    public void onBlockDestroyed(ItemStack itemStack, World world, int id, int x, int y, int z, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer && world.rand.nextInt(10) < this.power)
        {
            ((EntityPlayer) entityLiving).getFoodStats().addStats((ItemFood) Item.carrot);
        }
    }

    @Override
    public ElementEnum getElement()
    {

        return ElementEnum.F;
    }

    @Override
    public void onTick()
    {
    }

    @Override
    public String getDescription()
    {

        return "Preserves teeth to give you bonus food when mining";
    }

}
