package fr.kysio.phonemod.api.applications;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public abstract class Application {

    private final String name;
    private final String description;
    private final ResourceLocation icon;

    public Application(String name, String description, @Nullable ResourceLocation icon) {
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ResourceLocation getIcon() {
        return icon;
    }

    public abstract void render(ScaledResolution resolution);
}
