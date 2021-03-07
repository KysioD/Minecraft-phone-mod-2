package fr.kysio.phonemod.phone.applications;

import fr.kysio.phonemod.PhoneMod;
import fr.kysio.phonemod.api.PhoneManager;
import fr.kysio.phonemod.api.applications.Application;
import fr.kysio.phonemod.api.widgets.PhoneWidget;
import fr.kysio.phonemod.client.KeyBindings;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class SettingsApplication extends Application {
    public SettingsApplication(PhoneManager phoneManager) {
        super(phoneManager, "settings", "The phone settings", new ResourceLocation(PhoneMod.MODID, "textures/phone/settings.png"));
    }

    @Override
    public void render(ScaledResolution resolution) {

    }

    @Override
    public void keyPressed() {
        if(KeyBindings.BACK_KEY.isPressed()){
            Application application = getPhoneManager().getApplication("menu");
            if(application != null) getPhoneManager().setCurrentApplication(application);
        }
        super.keyPressed();
    }
}
