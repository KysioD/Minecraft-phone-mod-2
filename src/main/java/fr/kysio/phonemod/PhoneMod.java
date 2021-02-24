package fr.kysio.phonemod;

import fr.kysio.phonemod.api.PhoneManager;
import fr.kysio.phonemod.common.CommonProxy;
import fr.kysio.phonemod.items.PhoneItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

@Mod(modid = "phonemod")
public class PhoneMod {
    public static final String MODID = "phonemod";
    @Mod.Instance(PhoneMod.MODID)
    public static PhoneMod instance;

    @SidedProxy(clientSide = "fr.kysio.phonemod.common.CommonProxy", serverSide = "fr.kysio.phonemod.client.ClientProxy")
    public static CommonProxy proxy;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(PhoneItems.class);
        MinecraftForge.EVENT_BUS.register(RegistringHandler.class);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        if(event.getSide() == Side.CLIENT) {
            PhoneManager phoneManager = new PhoneManager();
        }
    }
}
