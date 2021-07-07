package fr.kysio.phonemod.phone.applications;

import fr.kysio.phonemod.PhoneMod;
import fr.kysio.phonemod.api.PhoneGraphicUtil;
import fr.kysio.phonemod.api.PhoneManager;
import fr.kysio.phonemod.api.applications.Application;
import fr.kysio.phonemod.api.widgets.PhoneWidget;
import fr.kysio.phonemod.api.widgets.WidgetButton;
import fr.kysio.phonemod.client.KeyBindings;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.nbt.*;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;

import java.awt.*;
import java.util.ArrayList;

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
                    NBTTagCompound nbtTagCompound = phoneManager.getPhone().getTagCompound();
                    NBTBase nbtBase = nbtTagCompound.getTag(str);
                    // x : 20 width : 158

                    String text = str.replace("s_", "") + " : ";

                    if(nbtBase instanceof NBTTagByte){
                        text+=nbtTagCompound.getBoolean(str);
                    }else if(nbtBase instanceof NBTTagFloat){
                        text+=nbtTagCompound.getFloat(str);
                    }else if(nbtBase instanceof NBTTagString){
                        text+=nbtTagCompound.getString(str);
                    }

                    WidgetButton button = new WidgetButton(phoneManager, 45, 30 + (i * 35), 108, 30, 1, Color.gray.getRGB(), Color.white.getRGB(), Color.darkGray.getRGB(), text) {
                        @Override
                        public void onUse() {
                        }
                    };

                    WidgetButton left = new WidgetButton(phoneManager, 22, 30 + (i * 35), 17, 30, 1, Color.gray.getRGB(), Color.white.getRGB(), Color.darkGray.getRGB(), "<") {
                        @Override
                        public void onUse() {
                            if(nbtBase instanceof NBTTagFloat){
                                float min = 0f;
                                if(str.equals("s_scale")) min = 0.1f;

                                if(nbtTagCompound.getFloat(str)-0.1f >= min) {
                                    nbtTagCompound.setFloat(str, nbtTagCompound.getFloat(str)-0.1f);
                                    phoneManager.getPhone().setTagCompound(nbtTagCompound);
                                    button.text = str.replace("s_", "") + " : " + nbtTagCompound.getFloat(str);
                                }
                            }else if(nbtBase instanceof NBTTagByte){
                                nbtTagCompound.setBoolean(str, !nbtTagCompound.getBoolean(str));
                                phoneManager.getPhone().setTagCompound(nbtTagCompound);
                                button.text = str.replace("s_", "") + " : " + nbtTagCompound.getBoolean(str);
                            }
                        }
                    };
                    WidgetButton right = new WidgetButton(phoneManager, 158, 30 + (i * 35), 17, 30, 1, Color.gray.getRGB(), Color.white.getRGB(), Color.darkGray.getRGB(), ">") {
                        @Override
                        public void onUse() {
                            if(nbtBase instanceof NBTTagFloat){
                                float max = 1.0f;

                                if(nbtTagCompound.getFloat(str)+0.1f <= max) {
                                    nbtTagCompound.setFloat(str, nbtTagCompound.getFloat(str)+0.1f);
                                    phoneManager.getPhone().setTagCompound(nbtTagCompound);
                                    button.text = str.replace("s_", "") + " : " + nbtTagCompound.getFloat(str);
                                }
                            }else if(nbtBase instanceof NBTTagByte){
                                nbtTagCompound.setBoolean(str, !nbtTagCompound.getBoolean(str));
                                phoneManager.getPhone().setTagCompound(nbtTagCompound);
                                button.text = str.replace("s_", "") + " : " + nbtTagCompound.getBoolean(str);
                            }
                        }
                    };

                    phoneWidgets.add(left);
                    phoneWidgets.add(right);
                    i++;
                    phoneWidgets.add(button);
                }
            }
            PhoneWidget[] widgets = new PhoneWidget[phoneWidgets.size()];
            widgets = phoneWidgets.toArray(widgets);
            setPhoneWidgets(ArrayUtils.addAll(widgets, getPhoneWidgets()));
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
