package fr.kysio.phonemod;

import fr.kysio.phonemod.api.PhoneManager;
import fr.kysio.phonemod.phone.applications.MenuApplication;
import fr.kysio.phonemod.client.KeyBindings;
import fr.kysio.phonemod.common.CommonProxy;
import fr.kysio.phonemod.items.PhoneItems;
import fr.kysio.phonemod.network.PlayerCloseAppPacket;
import fr.kysio.phonemod.network.PlayerOpenAppPacket;
import fr.kysio.phonemod.network.PlayerOpenPhonePacket;
import fr.kysio.phonemod.phone.PhoneEvents;
import fr.kysio.phonemod.phone.applications.SettingsApplication;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

@Mod(modid = "phonemod", name = "phonemod", version="1.0", dependencies = "required-before:sqript")
public class PhoneMod {
    public static final String MODID = "phonemod";
    @Mod.Instance(PhoneMod.MODID)
    public static PhoneMod instance;

    @SidedProxy(clientSide = "fr.kysio.phonemod.common.CommonProxy", serverSide = "fr.kysio.phonemod.client.ClientProxy")
    public static CommonProxy proxy;

    public static Logger logger;


    public static SimpleNetworkWrapper network;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();

        network = NetworkRegistry.INSTANCE.newSimpleChannel("phonemod");

        network.registerMessage(PlayerOpenPhonePacket.Handler.class, PlayerOpenPhonePacket.class, 0, Side.SERVER);
        network.registerMessage(PlayerOpenAppPacket.Handler.class, PlayerOpenAppPacket.class, 1, Side.SERVER);
        network.registerMessage(PlayerCloseAppPacket.Handler.class, PlayerCloseAppPacket.class, 2, Side.SERVER);

        proxy.preInit(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(PhoneItems.class);
        MinecraftForge.EVENT_BUS.register(RegistringHandler.class);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        if(event.getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new PhoneEvents());
            KeyBindings.registerKeyBindings();
        }
    }

}
