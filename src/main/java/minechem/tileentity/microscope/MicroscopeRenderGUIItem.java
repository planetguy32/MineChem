package minechem.tileentity.microscope;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

public class MicroscopeRenderGUIItem extends RenderItem
{
    public MicroscopeContainer microscopeContainer;
    public InventoryPlayer inventoryPlayer;
    public MicroscopeGui microscopeGui;

    int colorTextureID;
    int framebufferID;
    int depthRenderBufferID;
    boolean isFBOSupported;

    public MicroscopeRenderGUIItem(MicroscopeGui microscopeGui)
    {
        super();
        this.microscopeGui = microscopeGui;
        microscopeContainer = (MicroscopeContainer) microscopeGui.inventorySlots;
        inventoryPlayer = microscopeGui.inventoryPlayer;
    }

    private void setScissor(float x, float y, float w, float h)
    {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledRes = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        int scale = scaledRes.getScaleFactor();
        x *= scale;
        y *= scale;
        w *= scale;
        h *= scale;
        float guiScaledWidth = (microscopeGui.guiWidth * scale);
        float guiScaledHeight = (microscopeGui.guiHeight * scale);
        float guiLeft = ((mc.displayWidth / 2) - guiScaledWidth / 2);
        float guiTop = ((mc.displayHeight / 2) + guiScaledHeight / 2);
        int scissorX = Math.round((guiLeft + x));
        int scissorY = Math.round(((guiTop - h) - y));
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor(scissorX, scissorY, (int) w, (int) h);
    }

    private void stopScissor()
    {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    @Override
    public void renderItemAndEffectIntoGUI(FontRenderer par1FontRenderer, TextureManager par2RenderEngine, ItemStack itemstack, int x, int y)
    {
        if (itemstack == null)
            return;
        Slot slot = microscopeContainer.getSlot(0);
        if (itemstack == slot.getStack() || itemstack == inventoryPlayer.getItemStack())
        {
            GL11.glPushMatrix();
            setScissor(microscopeGui.eyepieceX, microscopeGui.eyepieceY, 52, 52);
            GL11.glTranslatef(x, y, 0.0F);
            GL11.glScalef(3.0F, 3.0F, 1.0F);
            GL11.glTranslatef(-x - 5.3F, -y - 5.3F, 2.0F);
            super.renderItemAndEffectIntoGUI(par1FontRenderer, par2RenderEngine, itemstack, x, y);
            stopScissor();
            GL11.glPopMatrix();
        }

        if (itemstack != microscopeContainer.getSlot(0).getStack())
        {
            if (itemstack == inventoryPlayer.getItemStack())
                this.zLevel = 40.0F;
            else
                this.zLevel = 50F;

            super.renderItemAndEffectIntoGUI(par1FontRenderer, par2RenderEngine, itemstack, x, y);
        }

        if (inventoryPlayer.getItemStack() != null && itemstack == inventoryPlayer.getItemStack())
        {
            GL11.glPushMatrix();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
            drawRect(microscopeGui.eyepieceX, microscopeGui.eyepieceY, 54, 54);
            GL11.glPopMatrix();
        }
    }

    private void drawRect(int x, int y, int width, int height)
    {
        double z = 50D;
        GL11.glDisable(GL11.GL_LIGHTING);
        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.setColorOpaque_I(0x121212);
        t.addVertex(x + 0, y + 0, z);
        t.addVertex(x + 0, y + height, z);
        t.addVertex(x + width, y + height, z);
        t.addVertex(x + width, y + 0, z);
        t.draw();
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    @Override
    public void renderItemOverlayIntoGUI(FontRenderer par1FontRenderer, TextureManager par2RenderEngine, ItemStack itemstack, int par4, int par5)
    {
        if (itemstack == null)
            return;
        if (itemstack == microscopeContainer.getSlot(0).getStack() || (itemstack == inventoryPlayer.getItemStack() && microscopeGui.isMouseInMicroscope()))
        {
            // do nothing.
        }
        else
        {
            super.renderItemOverlayIntoGUI(par1FontRenderer, par2RenderEngine, itemstack, par4, par5);
        }
    }
}
