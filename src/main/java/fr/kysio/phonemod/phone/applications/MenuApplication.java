package fr.kysio.phonemod.phone.applications;

import fr.kysio.phonemod.api.PhoneGraphicUtil;
import fr.kysio.phonemod.api.PhoneManager;
import fr.kysio.phonemod.api.applications.Application;
import fr.kysio.phonemod.api.widgets.PhoneWidget;
import fr.kysio.phonemod.api.widgets.WidgetButton;
import fr.kysio.phonemod.api.widgets.WidgetButtonImage;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.util.ArrayList;

public class MenuApplication extends Application {
    public MenuApplication(PhoneManager manager) {
        super(manager, "menu", "menu application", null);
        ArrayList<PhoneWidget> phoneWidgets = new ArrayList<>();
        //4x6 menu
        int line = 0;
        int column = 0;
        for(Application application : getPhoneManager().getApplications()){
            if(application.getIcon() != null){
                WidgetButtonImage widgetButtonImage = new WidgetButtonImage(getPhoneManager(), 22 + (column * 42), 55 + (line * 42), 30, 30, 1, Color.gray.getRGB(), application.getIcon()) {
                    @Override
                    public void onUse() {
                        getPhoneManager().setCurrentApplication(application);
                    }
                };

                phoneWidgets.add(widgetButtonImage);
                column++;
                if(column == 4){
                    column = 0;
                    line++;
                }
            }
        }
        PhoneWidget[] widgets = new PhoneWidget[phoneWidgets.size()];
        setPhoneWidgets(phoneWidgets.toArray(widgets));
    }

    @Override
    public void render(ScaledResolution resolution) {
        PhoneGraphicUtil.drawTopBar(getPhoneManager(), resolution);
        super.render(resolution);
    }

    @Override
    public void keyPressed() {
        super.keyPressed();
    }
}
