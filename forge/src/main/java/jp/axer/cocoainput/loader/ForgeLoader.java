package jp.axer.cocoainput.loader;

import jp.axer.cocoainput.CocoaInput;
import jp.axer.cocoainput.util.ModLogger;
import jp.axer.cocoainput.util.FCConfig;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(CocoaInput.MOD_ID)
public final class ForgeLoader {

    private CocoaInput instance;

    public ForgeLoader() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLClientSetupEvent event) {
        this.instance = new CocoaInput("MinecraftForge", ModList.get().getModFileById("cocoainput").getFile().getFilePath().toString());
        ModLogger.log("Forge config setup");
        ModLogger.log("Config path:" + FMLPaths.CONFIGDIR.get().resolve("cocoainput.json").toString());
        FCConfig.init("cocoainput", FMLPaths.CONFIGDIR.get().resolve("cocoainput.json"), FCConfig.class);
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((mc, modListScreen) -> new FCConfig().getScreen(modListScreen)));
        CocoaInput.config = new FCConfig();
        ModLogger.log("ConfigPack:" + CocoaInput.config.isAdvancedPreeditDraw() + " " + CocoaInput.config.isNativeCharTyped());
    }

    @SubscribeEvent
    public void didChangeGui(ScreenEvent.Opening event) {
        this.instance.distributeScreen(event.getScreen());
    }

    @SubscribeEvent
    public void didChangeGui(ScreenEvent.Closing event) {
        this.instance.distributeScreen(event.getScreen());
    }
}
