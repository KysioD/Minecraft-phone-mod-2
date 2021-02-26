package fr.kysio.phonemod.api.applications;

import fr.kysio.phonemod.api.widgets.PhoneWidget;
import fr.kysio.phonemod.client.KeyBindings;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public abstract class Application {

    private final String name;
    private final String description;
    private final ResourceLocation icon;
    private PhoneWidget[] phoneWidgets;

    //Widgets gestion
    private PhoneWidget selectedWidget;

    public Application(String name, String description, @Nullable ResourceLocation icon, PhoneWidget... phoneWidgets) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.phoneWidgets = phoneWidgets;
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

    public void keyPressed(){
    }

    public PhoneWidget[] getPhoneWidgets() {
        return phoneWidgets;
    }

    public void setPhoneWidgets(PhoneWidget[] phoneWidgets) {
        this.phoneWidgets = phoneWidgets;
    }
}
