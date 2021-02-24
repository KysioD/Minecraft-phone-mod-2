package fr.kysio.phonemod.api;

import fr.kysio.phonemod.PhoneMod;
import fr.kysio.phonemod.api.applications.Application;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class PhoneManager {

    private static PhoneManager instance;
    private ArrayList<PhoneSetting> settings = new ArrayList<>();
    private ArrayList<Application> applications = new ArrayList<>();

    private Application currentApplication;
    private boolean isLocked = true;

    private ResourceLocation background = new ResourceLocation(PhoneMod.MODID, "textures/phone/phone.png");

    public PhoneManager() {
        instance = this;
        settings.add(new PhoneSetting("scale", "phone scale", 0.5f)); //float value
    }

    /**
     * Get the instance of phone manager
     */
    public static PhoneManager getInstance() {
        return instance;
    }

    /**
     * Get the settings list
     *
     * @return the settings list
     */
    public ArrayList<PhoneSetting> getSettings() {
        return settings;
    }

    /**
     * Get the applications list
     *
     * @return the applications list
     */
    public ArrayList<Application> getApplications() {
        return applications;
    }

    /**
     * Add an application
     *
     * @param application
     */
    public void addApplication(Application application) {
        if (!getApplications().contains(application)) {
            getApplications().add(application);
        }
    }

    /**
     * Remove an appliaction
     *
     * @param application
     */
    public void removeApplication(Application application) {
        getApplications().remove(application);
    }

    /**
     * Get current use application
     *
     * @return current use application
     */
    public Application getCurrentApplication() {
        return currentApplication;
    }

    /**
     * Get if phone is locked
     *
     * @return if phone is locked
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Lock or unlock phone
     *
     * @param locked if phone is locked
     */
    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    /**
     * Set the current application in use
     *
     * @param currentApplication application in use
     */
    public void setCurrentApplication(Application currentApplication) {
        this.currentApplication = currentApplication;
    }

    /**
     * Change the phone background
     *
     * @param background the phone background
     */
    public void setBackground(ResourceLocation background) {
        this.background = background;
    }

    /**
     * Get the phone background
     *
     * @return the phone background
     */
    public ResourceLocation getBackground() {
        return background;
    }

    /**
     * Get a setting thanks to it name
     * @param name setting name
     * @return the setting
     */
    public PhoneSetting getSettings(String name) {
        for (PhoneSetting setting : settings) {
            if (setting.getName().equals(name)) return setting;
        }

        return null;
    }

    /**
     * Get an application thanks to it name
     * @param name appliaction name
     * @return the application
     */
    public Application getApplication(String name){
        for(Application application : applications){
            if(application.getName().equals(name)) return application;
        }
        return null;
    }
}
