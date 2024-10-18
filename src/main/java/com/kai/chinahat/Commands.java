package com.kai.chinahat;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class Commands extends CommandBase {
	@Override
	public String getCommandName() {
		return "chinahat";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/chinahat";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
		return null;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 1) {
			if (args[0].equals("tilt")) {
				Settings.tilt = !Settings.tilt;
				sender.addChatMessage(new ChatComponentText((Settings.tilt ? "ENABLED" : "DISABLED") + " tilt!"));
				ConfigHandler.saveConfig();
			}
			else if (!(Minecraft.getMinecraft().currentScreen instanceof SettingsGUI))
				ChinaHatMod.openGUI = true;
		}
		else if (!(Minecraft.getMinecraft().currentScreen instanceof SettingsGUI))
			ChinaHatMod.openGUI = true;
	}

}