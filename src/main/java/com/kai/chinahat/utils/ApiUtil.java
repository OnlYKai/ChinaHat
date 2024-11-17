package com.kai.chinahat.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kai.chinahat.ConfigHandler;
import com.kai.chinahat.Settings;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApiUtil {

	private static final JsonParser jsonParser = new JsonParser();
	private static final ExecutorService executor = Executors.newCachedThreadPool();


	public static CompletableFuture<Void> loadFromUUID(String uuid) {
		return CompletableFuture.runAsync(() -> {
			try {
				URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid);
				JsonObject json = getJson(url);

				synchronized (Settings.on_players_uuid) {
					Settings.on_players_uuid.add(uuid);
				}
				synchronized (Settings.on_players_name) {
					Settings.on_players_name.add(json.get("name").getAsString());
				}
			}
			catch (UnknownHostException e) {
				synchronized (Settings.on_players_uuid) {
					Settings.on_players_uuid.add(uuid);
				}
				System.out.println("No internet connection.");
			}
			catch (SocketTimeoutException e) {
				synchronized (Settings.on_players_uuid) {
					Settings.on_players_uuid.add(uuid);
				}
				System.out.println("Connection timed out while fetching player data.");
			}
			catch (Exception e) {
				if (!e.getMessage().equals("not found!"))
					System.out.println("'" + uuid + "' " + e.getMessage());
				else {
					synchronized (Settings.on_players_uuid) {
						Settings.on_players_uuid.add(uuid);
					}
					System.out.println("Error: " + e.getMessage());
				}
			}
		}, executor);
	}


	public static void addByName(ICommandSender sender, String arg) {
		executor.submit(() -> {
			try {
				URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + arg);
				JsonObject json = getJson(url);

				String uuid = json.get("id").getAsString();
				String name = json.get("name").getAsString();

				if (Settings.on_players_uuid.contains(uuid))
					sender.addChatMessage(new ChatComponentText(name + " is already on the list."));
				else {
					synchronized (Settings.on_players_uuid) {
						Settings.on_players_uuid.add(uuid);
						ConfigHandler.saveConfig();
					}
					synchronized (Settings.on_players_name) {
						Settings.on_players_name.add(name);
					}
					sender.addChatMessage(new ChatComponentText("Added " + name + "!"));
				}
			}
			catch (UnknownHostException e) {
				sender.addChatMessage(new ChatComponentText("No internet connection."));
			}
			catch (SocketTimeoutException e) {
				sender.addChatMessage(new ChatComponentText("Connection timed out while fetching player data."));
			}
			catch (Exception e) {
				if (e.getMessage().equals("not found!"))
					sender.addChatMessage(new ChatComponentText("'" + arg + "' " + e.getMessage()));
				else
					sender.addChatMessage(new ChatComponentText("Error: " + e.getMessage()));
			}
		}, executor);
	}

	public static void removeByName(ICommandSender sender, String arg) {
		executor.submit(() -> {
			try {
				URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + arg);
				JsonObject json = getJson(url);

				String uuid = json.get("id").getAsString();
				String name = json.get("name").getAsString();

				if (!Settings.on_players_uuid.contains(uuid))
					sender.addChatMessage(new ChatComponentText(name + " is not on the list."));
				else {
					synchronized (Settings.on_players_uuid) {
						Settings.on_players_uuid.remove(uuid);
						ConfigHandler.saveConfig();
					}
					synchronized (Settings.on_players_name) {
						Settings.on_players_name.remove(name);
					}
					sender.addChatMessage(new ChatComponentText("Removed " + name + "!"));
				}
			}
			catch (UnknownHostException e) {
				sender.addChatMessage(new ChatComponentText("No internet connection."));
			}
			catch (SocketTimeoutException e) {
				sender.addChatMessage(new ChatComponentText("Connection timed out while fetching player data."));
			}
			catch (Exception e) {
				if (e.getMessage().equals("not found!"))
					sender.addChatMessage(new ChatComponentText("'" + arg + "' " + e.getMessage()));
				else
					sender.addChatMessage(new ChatComponentText("Error: " + e.getMessage()));
			}
		}, executor);
	}



	private static JsonObject getJson(URL url) throws Exception {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// Set the request method to GET and timeout to 5 seconds
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(1000);
		connection.setReadTimeout(2000);

		// Check response code
		int responseCode = connection.getResponseCode();
		if (responseCode == 200) {
			// Read the response
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			// Return the JsonParser
			return jsonParser.parse(response.toString()).getAsJsonObject();
		}
		else if (responseCode == 404)
			throw new IllegalArgumentException("not found!");
		else
			throw new RuntimeException("" + responseCode);
	}

}