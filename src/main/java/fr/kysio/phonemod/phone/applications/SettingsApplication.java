package fr.kysio.phonemod.phone.applications;

import fr.kysio.phonemod.PhoneMod;
import fr.kysio.phonemod.api.PhoneGraphicUtil;
import fr.kysio.phonemod.api.PhoneManager;
import fr.kysio.phonemod.api.applications.Application;
import fr.kysio.phonemod.api.widgets.PhoneWidget;
import fr.kysio.phonemod.api.widgets.WidgetButton;
import fr.kysio.phonemod.client.KeyBindings;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;
import sun.security.util.ArrayUtil;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class SettingsApplication extends Application {

    private int page;

    public SettingsApplication(PhoneManager phoneManager) {
        super(phoneManager, "settings", "The phone settings", new ResourceLocation(PhoneMod.MODID, "textures/phone/settings.png"),
                new WidgetButton(phoneManager, 50, 280, 40, 15, 1, Color.gray.getRGB(), Color.white.getRGB(), Color.darkGray.getRGB(), "<") {
                    @Override
                    public void onUse() {

                    }
                },
                new WidgetButton(phoneManager, 105, 280, 40, 15, 1, Color.gray.getRGB(), Color.white.getRGB(), Color.darkGray.getRGB(), ">") {
                    @Override
                    public void onUse() {

                    }
                }
        );
        if(phoneManager.getPhone().getTagCompound() != null) {
            ArrayList<PhoneWidget> phoneWidgets = new ArrayList<>();
            int i = 1;
            for (String str : phoneManager.getPhone().getTagCompound().getKeySet()) {
                if (str.startsWith("s_")) {
                    NBTBase nbtBase = phoneManager.getPhone().getTagCompound().getTag(str);
                    WidgetButton button = new WidgetButton(phoneManager, 20, 30 + (i * 35), 158, 30, 1, Color.gray.getRGB(), Color.white.getRGB(), Color.darkGray.getRGB(), str + " : " + nbtBase.toString().replace("\"", "").replace("s_", "")) {
                        @Override
                        public void onUse() {

                        }
                    };
                    i++;
                    phoneWidgets.add(button);
                }
            }
            PhoneWidget[] widgets = new PhoneWidget[phoneWidgets.size()];
            widgets = phoneWidgets.toArray(widgets);
            setPhoneWidgets(ArrayUtils.addAll(widgets, getPhoneWidgets()));
        }else{
            System.out.println("TAG COMPOUND"+phoneManager.getPhone().getTagCompound());
        }
    }

    @Override
    public void render(ScaledResolution resolution) {
        PhoneGraphicUtil.drawTopBar(getPhoneManager(), resolution);
        super.render(resolution);
    }

    @Override
    public void keyPressed() {
        if (KeyBindings.BACK_KEY.isPressed()) {
            Application application = getPhoneManager().getApplication("menu");
            if (application != null) getPhoneManager().setCurrentApplication(application);
        }
        super.keyPressed();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
