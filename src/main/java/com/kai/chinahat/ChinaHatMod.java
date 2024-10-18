package com.kai.chinahat;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static com.kai.chinahat.Settings.hat;

@Mod(modid = "chinahat", name = "ChinaHat", version = "1.0")
public class ChinaHatMod {

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.loadConfig();
		ClientCommandHandler.instance.registerCommand(new Commands());
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}



	// Hat
	private final ChinaHat chinaHat = new ChinaHat();
	@SubscribeEvent
	public void onRenderWorld(RenderWorldLastEvent event) {
		if (hat)
			chinaHat.onRender3DEvent(event.partialTicks);
	}

	// GUI
	public static boolean openGUI = false;
	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event) {
		if (openGUI) {
			openGUI = false;
			Minecraft.getMinecraft().displayGuiScreen(new SettingsGUI());
		}
	}

}