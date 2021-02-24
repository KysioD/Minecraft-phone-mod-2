package fr.kysio.phonemod.api;

import fr.kysio.phonemod.api.applications.Application;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class PhoneManager {

    private static PhoneManager instance;
    private ArrayList<PhoneSetting> settings = new ArrayList<>();
    private ArrayList<Application> application = new ArrayList<>();

    public PhoneManager(){
        instance = this;
    }

    /**
     * Get the instance of phone manager
     */
    public static PhoneManager getInstance() {
        return instance;
    }

    /**
     * Get the settings list
     * @return the settings list
     */
    public ArrayList<PhoneSetting> getSettings() {
        return settings;
    }

    /**
     * Get the applications list
     * @return the applications list
     */
    public ArrayList<Application> getApplications() {
        return application;
    }

    /**
     * Add an application
     * @param application
     */
    public void addApplication(Application application) {
        if(!getApplications().contains(application)){
            getApplications().add(application);
        }
    }

    /**
     * Remove an appliaction
     * @param application
     */
    public void removeApplication(Application application){
        getApplications().remove(application);
    }
}
