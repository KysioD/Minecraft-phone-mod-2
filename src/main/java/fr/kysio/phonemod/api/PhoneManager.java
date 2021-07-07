package fr.kysio.phonemod.api;

import fr.kysio.phonemod.PhoneMod;
import fr.kysio.phonemod.api.applications.Application;
import fr.kysio.phonemod.phone.applications.MenuApplication;
import fr.kysio.phonemod.phone.applications.SettingsApplication;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class PhoneManager {
    private ArrayList<Application> applications = new ArrayList<>();

    private ItemStack phone;

    private ResourceLocation background = new ResourceLocation(PhoneMod.MODID, "textures/phone/phone.png");

    public PhoneManager(ItemStack phone) {
        this.phone = phone;

        NBTTagCompound nbt;
        if(phone.hasTagCompound()){
            nbt = phone.getTagCompound();
        }else{
            nbt = new NBTTagCompound();
            nbt.setFloat("s_scale", 0.5f); //s_settingName -> a phone setting
            nbt.setString("current_app", "");
            nbt.setBoolean("locked", true);
        }

        phone.setTagCompound(nbt);

        this.addApplication(new SettingsApplication(this));
        this.addApplication(new MenuApplication(this));
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
        if(!phone.hasTagCompound()) return null;
        return getApplication(phone.getTagCompound().getString("current_app"));
    }

    /**
     * Get if phone is locked
     *
     * @return if phone is locked
     */
    public boolean isLocked() {
        if(!phone.hasTagCompound()) return true;
        return phone.getTagCompound().getBoolean("locked");
    }

    /**
     * Lock or unlock phone
     *
     * @param locked if phone is locked
     */
    public void setLocked(boolean locked) {
       if(phone.hasTagCompound()){
           NBTTagCompound nbt = phone.getTagCompound();
           nbt.setBoolean("locked", locked);
           phone.setTagCompound(nbt);
       }
    }

    /**
     * Set the current application in use
     *
     * @param currentApplication application in use
     */
    public void setCurrentApplication(Application currentApplication) {
        System.out.println("phone has compound : "+phone.hasTagCompound());
        if(phone.hasTagCompound()){
            NBTTagCompound nbt = phone.getTagCompound();
            System.out.println("set app name "+currentApplication.getName());
            nbt.setString("current_app", currentApplication.getName());
            phone.setTagCompound(nbt);
        }
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

    public ItemStack getPhone() {
        return phone;
    }

    public void setPhone(ItemStack phone) {
        this.phone = phone;
    }
}
