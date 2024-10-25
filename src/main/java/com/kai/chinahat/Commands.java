package com.kai.chinahat;

import com.kai.chinahat.utils.ApiUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
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

	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, "help", "add", "remove", "list", "reload");
		}
		if (args.length == 2 && (args[0].equals("remove"))) {
			return getListOfStringsMatchingLastWord(args, Settings.on_players_name);
		}
		return null;
	}

	private static boolean isReloading;

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0)
			if (!(Minecraft.getMinecraft().currentScreen instanceof SettingsGUI))
				ChinaHatMod.openGUI = true;
			else
				sender.addChatMessage(new ChatComponentText("Settings already open!"));
		if (args.length == 1) {
			switch (args[0]) {
				case "help":
					sender.addChatMessage(new ChatComponentText("ChinaHat commands:" +
										"\n     /chinahat -> settings menu" +
										"\n     /chinahat add <player> -> add player to list" +
										"\n     /chinahat remove <player> -> remove player from list" +
										"\n     /chinahat list -> list of player the hat is enabled for when 'On Others' is set to 'Custom' or 'Custom+Party'" +
										"\n     /chinahat reload -> reloads config (includes playernames from uuids)"
					));
					break;
				case "add":
					sender.addChatMessage(new ChatComponentText("Specify a player to add. /chinahat help"));
					break;
				case "remove":
					sender.addChatMessage(new ChatComponentText("Specify a player to remove. /chinahat help"));
					break;
				case "list":
					if (!Settings.on_players_name.isEmpty())
						sender.addChatMessage(new ChatComponentText("ChinaHat enabled for:\n     " + String.join("\n     ", Settings.on_players_name)));
					else if (Settings.on_players_uuid.isEmpty())
						sender.addChatMessage(new ChatComponentText("No players on the list. Use /chinahat add <player> to add a player."));
					else
						sender.addChatMessage(new ChatComponentText("Looks like playernames couldn't be loaded.\nCheck your internet connection and do /chinahat reload"));
					break;
				case "reload":
					if (isReloading) {
						sender.addChatMessage(new ChatComponentText("Still reloading..."));
						break;
					}
					isReloading = true;
					Settings.reset();
					ConfigHandler.loadConfig()
						.thenRun(() -> {
							sender.addChatMessage(new ChatComponentText("Reloaded!"));
							isReloading = false;
						})
						.exceptionally(e -> {
							sender.addChatMessage(new ChatComponentText("Error during reload: " + e.getMessage() + "\nSeriously, how did you even trigger this?"));
							isReloading = false;
							return null;
						});
					break;
				default:
					sender.addChatMessage(new ChatComponentText("Invalid argument! /chinahat help"));
			}
		}
		if (args.length >= 2) {
			switch (args[0]) {
				case "add":
					if (args[1].length() <= 16)
						if (args[1].matches("[a-zA-Z0-9_]+"))
							ApiUtil.addByName(sender, args[1]);
						else
							sender.addChatMessage(new ChatComponentText("Invalid name! Only characters A-z, 0-9 and _ are allowed."));
					else
						sender.addChatMessage(new ChatComponentText("Invalid name! Max length is 16 characters."));
					break;
				case "remove":
					if (args[1].length() <= 16)
						if (args[1].matches("[a-zA-Z0-9_]+"))
							ApiUtil.removeByName(sender, args[1]);
						else
							sender.addChatMessage(new ChatComponentText("Invalid name! Only characters A-z, 0-9 and _ are allowed."));
					else
						sender.addChatMessage(new ChatComponentText("Invalid name! Max length is 16 characters."));
					break;
				default:
					sender.addChatMessage(new ChatComponentText("Invalid argument! /chinahat help"));
			}
		}
	}

}