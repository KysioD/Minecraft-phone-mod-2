package fr.kysio.phonemod.items;

import fr.kysio.phonemod.PhoneMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PhoneItems {

    public static final Item PHONE = new ItemPhone();

    public static void setItemName(Item item, String name) {
        item.setRegistryName(PhoneMod.MODID, name).setTranslationKey(name);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerItemModels(ModelRegistryEvent event) {
        registerModel(PHONE, 0);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModel(Item item, int metadata) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
