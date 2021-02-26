package fr.kysio.phonemod.api.applications;

import fr.kysio.phonemod.api.widgets.PhoneWidget;
import fr.kysio.phonemod.api.widgets.WidgetButton;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;

public class MenuApplication extends Application {
    public MenuApplication() {
        super("menu", "menu appliaction", null,
                new WidgetButton(10, 20, 30, 30, 1, Color.darkGray.getRGB(), Color.white.getRGB(), Color.gray.getRGB()) {
                    @Override
                    public void onUse() {

                    }
                },
                new WidgetButton(50, 20, 30, 30, 1, Color.darkGray.getRGB(), Color.white.getRGB(), Color.gray.getRGB()) {
                    @Override
                    public void onUse() {

                    }
                },
                new WidgetButton(90, 20, 30, 30, 1, Color.darkGray.getRGB(), Color.white.getRGB(), Color.gray.getRGB()) {
                    @Override
                    public void onUse() {

                    }
                },
                new WidgetButton(10, 60, 30, 30, 1, Color.darkGray.getRGB(), Color.white.getRGB(), Color.gray.getRGB()) {
                    @Override
                    public void onUse() {

                    }
                },
                new WidgetButton(50, 60, 30, 30, 1, Color.darkGray.getRGB(), Color.white.getRGB(), Color.gray.getRGB()) {
                    @Override
                    public void onUse() {

                    }
                },
                new WidgetButton(90, 60, 30, 30, 1, Color.darkGray.getRGB(), Color.white.getRGB(), Color.gray.getRGB()) {
                    @Override
                    public void onUse() {

                    }
                });
    }

    @Override
    public void render(ScaledResolution resolution) {
        for(PhoneWidget widget : getPhoneWidgets()){
            widget.onRender(resolution);
        }
    }

    @Override
    public void keyPressed() {
        super.keyPressed();
    }
}
