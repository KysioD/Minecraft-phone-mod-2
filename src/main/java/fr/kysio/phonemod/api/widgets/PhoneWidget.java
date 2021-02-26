package fr.kysio.phonemod.api.widgets;

import net.minecraft.client.gui.ScaledResolution;

public abstract class PhoneWidget {

    private int x;
    private int y;
    private int width;
    private int height;
    private float scale;
    private boolean isSelected;

    public PhoneWidget(int x, int y, int width, int height, float scale){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    public abstract void onRender(ScaledResolution scaledResolution);
    public abstract void onUse();
    public abstract void onSelected();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
