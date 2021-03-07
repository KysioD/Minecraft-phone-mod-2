package fr.kysio.phonemod.api.widgets;

import fr.kysio.phonemod.api.PhoneGraphicUtil;
import fr.kysio.phonemod.api.PhoneManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.Sys;

import java.awt.*;

public abstract class WidgetButtonImage extends WidgetButton{

    private ResourceLocation icon;

    public WidgetButtonImage(PhoneManager phoneManager, int x, int y, int width, int height, float scale, int selectedColor, ResourceLocation icon) {
        super(phoneManager, x, y, width, height, scale, Color.TRANSLUCENT, Color.TRANSLUCENT, selectedColor, "");
        this.icon = icon;
    }

    @Override
    public void onRender(ScaledResolution resolution) {

        float phoneScale = PhoneGraphicUtil.getPhoneScale(getPhoneManager());
        int screenX = (int) (((resolution.getScaledWidth() / (phoneScale)) - 210)/getScale());
        int screenY = (int) (((resolution.getScaledHeight() / (phoneScale)) - 360)/getScale());

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, 2);
        GlStateManager.scale(phoneScale*getScale(), phoneScale*getScale(), 1);
        if(isSelected()) Gui.drawRect((screenX+(int)(getX()/getScale()))-2, (screenY+(int)(getY()/getScale()))-2, (screenX+(int)((getX()+getWidth())/getScale()))+2, (screenY+(int)((getY()+getHeight())/getScale()))+2, selectedColor);

        Minecraft.getMinecraft().getTextureManager().bindTexture(getIcon());
        //Gui.drawRect(screenX+(int)(getX()/getScale()), screenY+(int)(getY()/getScale()), screenX+(int)((getX()+getWidth())/getScale()), screenY+(int)((getY()+getHeight())/getScale()), backgroundColor);
        Gui.drawModalRectWithCustomSizedTexture(screenX+(int)(getX()/getScale()), screenY+(int)(getY()/getScale()), 0, 0, getWidth(), getHeight(), getWidth(), getHeight());
        GlStateManager.popMatrix();
    }

    @Override
    public abstract void onUse();

    public ResourceLocation getIcon() {
        return icon;
    }

    public void setIcon(ResourceLocation icon) {
        this.icon = icon;
    }
}
