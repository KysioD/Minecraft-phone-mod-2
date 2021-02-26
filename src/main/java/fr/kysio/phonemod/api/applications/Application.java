package fr.kysio.phonemod.api.applications;

import fr.kysio.phonemod.api.widgets.PhoneWidget;
import fr.kysio.phonemod.api.widgets.WidgetButton;
import fr.kysio.phonemod.client.KeyBindings;
import net.minecraft.client.Minecraft;
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

    public void keyPressed() {
        PhoneWidget nextButton = null;
        if (KeyBindings.DOWN_KEY.isPressed()) {
            int currentY = (selectedWidget != null && selectedWidget instanceof WidgetButton) ? selectedWidget.getY() : 0;
            for (PhoneWidget widget : phoneWidgets) {
                if (widget != selectedWidget) {
                    if (widget.getY() != currentY) {
                        {
                            if (widget.getY() >= currentY) {
                                if (nextButton != null) {
                                    if (widget.getY() < nextButton.getY()) {
                                        nextButton = widget;
                                    }
                                } else {
                                    nextButton = widget;
                                }
                            }
                        }
                    }
                }
            }
            if (selectedWidget != null) selectedWidget.setSelected(false);
            if (nextButton != null) nextButton.setSelected(true);
            selectedWidget = nextButton;

        }else if(KeyBindings.UP_KEY.isPressed()) {
            int currentY = (selectedWidget != null && selectedWidget instanceof WidgetButton) ? selectedWidget.getY() : 500;
            for (PhoneWidget widget : phoneWidgets) {
                if (widget != selectedWidget) {
                    if (widget.getY() != currentY) {
                        {
                            if (widget.getY() <= currentY) {
                                if (nextButton != null) {
                                    if (widget.getY() > nextButton.getY()) {
                                        nextButton = widget;
                                    }
                                } else {
                                    nextButton = widget;
                                }
                            }
                        }
                    }
                }
                if (selectedWidget != null) selectedWidget.setSelected(false);
                if (nextButton != null) nextButton.setSelected(true);
                selectedWidget = nextButton;
            }
        }else if(KeyBindings.RIGHT_KEY.isPressed()){
            int currentY = (selectedWidget != null && selectedWidget instanceof WidgetButton) ? selectedWidget.getY() : 0;
            int currentX = (selectedWidget != null && selectedWidget instanceof WidgetButton) ? selectedWidget.getX() : 0;
            for (PhoneWidget widget : phoneWidgets) {
                if (widget != selectedWidget) {
                    if (widget.getX() != currentX && widget.getY() == currentY) {
                        {
                            if (widget.getX() >= currentX) {
                                if (nextButton != null) {
                                    if (widget.getX() < nextButton.getX()) {
                                        nextButton = widget;
                                    }
                                } else {
                                    nextButton = widget;
                                }
                            }
                        }
                    }
                }
            }
            if (selectedWidget != null) selectedWidget.setSelected(false);
            if (nextButton != null) nextButton.setSelected(true);
            selectedWidget = nextButton;

        }else if(KeyBindings.LEFT_KEY.isPressed()){
            int currentY = (selectedWidget != null && selectedWidget instanceof WidgetButton) ? selectedWidget.getY() : 0;
            int currentX = (selectedWidget != null && selectedWidget instanceof WidgetButton) ? selectedWidget.getX() : 0;
            for (PhoneWidget widget : phoneWidgets) {
                if (widget != selectedWidget) {
                    if (widget.getX() != currentX && widget.getY() == currentY) {
                        {
                            if (widget.getX() <= currentX) {
                                if (nextButton != null) {
                                    if (widget.getX() > nextButton.getX()) {
                                        nextButton = widget;
                                    }
                                } else {
                                    nextButton = widget;
                                }
                            }
                        }
                    }
                }
            }
            if (selectedWidget != null) selectedWidget.setSelected(false);
            if (nextButton != null) nextButton.setSelected(true);
            selectedWidget = nextButton;

        }
    }

    public PhoneWidget[] getPhoneWidgets() {
        return phoneWidgets;
    }

    public void setPhoneWidgets(PhoneWidget[] phoneWidgets) {
        this.phoneWidgets = phoneWidgets;
    }
}
