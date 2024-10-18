package com.kai.chinahat;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class ConfigHandler {

	public static void loadConfig() {
		try {
			List<String> lines = Files.readAllLines(Paths.get("config/ChinaHat.toml"));
			for (String line : lines) {
				if (line.startsWith("//"))
					continue;
				String line_commentless = line.split("//")[0].trim();
				if (line_commentless.split("=").length != 2)
					continue;
				String key = line_commentless.split("=")[0].trim();
				String value = line_commentless.split("=")[1].trim();
				switch (key) {
					case "hat":
						if (value.equals("true") || value.equals("false"))
							Settings.hat = Boolean.parseBoolean(value);
						break;
					case "on_self":
						if (value.equals("true") || value.equals("false"))
							Settings.on_self = Boolean.parseBoolean(value);
						break;
					case "on_others":
						if (value.equals("true") || value.equals("false"))
							Settings.on_others = Boolean.parseBoolean(value);
						break;


					case "first_person":
						if (value.equals("true") || value.equals("false"))
							Settings.first_person = Boolean.parseBoolean(value);
						break;
					case "speed":
						try {Settings.speed = Math.max(1, (int) Math.abs(Math.round(Double.parseDouble(value))));}
						catch (NumberFormatException e) {}
						break;
					case "saturation":
						parseFloat(value, 0, 1).ifPresent(v -> Settings.saturation = v);
						break;
					case "tilt":
						if (value.equals("true") || value.equals("false"))
							Settings.tilt = Boolean.parseBoolean(value);
						break;


					case "outline":
						if (value.equals("true") || value.equals("false"))
							Settings.outline = Boolean.parseBoolean(value);
						break;
					case "outline_thickness":
						parseFloat(value, 0, 5).ifPresent(v -> Settings.outline_thickness = v);
						break;
					case "outline_mode":
						if (value.equals("Solid") || value.equals("Gradient") || value.equals("Rainbow"))
							Settings.outline_mode = value;
						break;
					case "outline_opacity":
						parseFloat(value, 0, 1).ifPresent(v -> Settings.outline_opacity = v);
						break;
					case "outline_solid":
						parseColor(value).ifPresent(v -> Settings.outline_solid = v);
						break;
					case "outline_gradient1":
						parseColor(value).ifPresent(v -> Settings.outline_gradient1 = v);
						break;
					case "outline_gradient2":
						parseColor(value).ifPresent(v -> Settings.outline_gradient2 = v);
						break;


					case "top_mode":
						if (value.equals("Solid") || value.equals("Gradient") || value.equals("Rainbow"))
							Settings.top_mode = value;
						break;
					case "top_opacity":
						parseFloat(value, 0, 1).ifPresent(v -> Settings.top_opacity = v);
						break;
					case "top_solid":
						parseColor(value).ifPresent(v -> Settings.top_solid = v);
						break;
					case "top_gradient1":
						parseColor(value).ifPresent(v -> Settings.top_gradient1 = v);
						break;
					case "top_gradient2":
						parseColor(value).ifPresent(v -> Settings.top_gradient2 = v);
						break;


					case "bottom_mode":
						if (value.equals("Solid") || value.equals("Gradient") || value.equals("Rainbow"))
							Settings.bottom_mode = value;
						break;
					case "bottom_opacity":
						parseFloat(value, 0, 1).ifPresent(v -> Settings.bottom_opacity = v);
						break;
					case "bottom_solid":
						parseColor(value).ifPresent(v -> Settings.bottom_solid = v);
						break;
					case "bottom_gradient1":
						parseColor(value).ifPresent(v -> Settings.bottom_gradient1 = v);
						break;
					case "bottom_gradient2":
						parseColor(value).ifPresent(v -> Settings.bottom_gradient2 = v);
						break;
				}
			}
		}
		catch (Exception e) {
			System.out.println("[ChinaHat] Couldn't load config! Error: " + e.getMessage());
		}
		saveConfig();
	}

	public static Optional<Float> parseFloat(String value, float minValue, float maxValue) {
		try {
			return Optional.of(Math.max(minValue, Math.min(maxValue, Float.parseFloat(value))));
		}
		catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

	public static Optional<Color> parseColor(String value) {
		try {
			return Optional.of(new Color(Integer.parseInt(value.replace("#", ""), 16)));
		}
		catch (NumberFormatException e) {
			return Optional.empty();
		}
	}



	public static void saveConfig() {
		try {
			Files.createDirectories(Paths.get("config"));
			Files.write(Paths.get("config/ChinaHat.toml"), (
					"// Main" +
					"\nhat = " + Settings.hat + " // Main toggle" +
					"\non_self = " + Settings.on_self + " // Render on self" +
					"\non_others = " + Settings.on_others + " // Render on other players" +

					"\n\n// Misc" +
					"\nfirst_person = " + Settings.first_person + " // Show hat in first person" +
					"\nspeed = " + Settings.speed + " // Rainbow/Gradient only (smaller = faster) (only whole numbers, can't be 0)" +
					"\nsaturation = " + Settings.saturation + " // Rainbow only (0.00 is 0%, 1.00 is 100%) (0 is grayscale)" +
					"\ntilt = " + Settings.tilt + " // Hat tilt with head (Only accessible with \"/chinahat tilt\" or via config)" +

					"\n\n// Outline settings" +
					"\noutline = " + Settings.outline + " // Main Outline toggle" +
					"\noutline_thickness = " + Settings.outline_thickness + " // (Over 5 looks shit, so it's capped at 5)" +
					"\noutline_mode = " + Settings.outline_mode + " // Solid/Gradient/Rainbow" +
					"\noutline_opacity = " + Settings.outline_opacity + " // (0.00 is 0%, 1.00 is 100%)" +
					"\noutline_solid = " + getHex(Settings.outline_solid) +
					"\noutline_gradient1 = " + getHex(Settings.outline_gradient1) +
					"\noutline_gradient2 = " + getHex(Settings.outline_gradient2) +

					"\n\n// Top hat settings" +
					"\ntop_mode = " + Settings.top_mode + " // Solid/Gradient/Rainbow" +
					"\ntop_opacity = " + Settings.top_opacity + " // (0.00 is 0%, 1.00 is 100%)" +
					"\ntop_solid = " + getHex(Settings.top_solid) +
					"\ntop_gradient1 = " + getHex(Settings.top_gradient1) +
					"\ntop_gradient2 = " + getHex(Settings.top_gradient2) +

					"\n\n// Bottom hat settings" +
					"\nbottom_mode = " + Settings.bottom_mode + " // Solid/Gradient/Rainbow" +
					"\nbottom_opacity = " + Settings.bottom_opacity + " // (0.00 is 0%, 1.00 is 100%)" +
					"\nbottom_solid = " + getHex(Settings.bottom_solid) +
					"\nbottom_gradient1 = " + getHex(Settings.bottom_gradient1) +
					"\nbottom_gradient2 = " + getHex(Settings.bottom_gradient2)
			).getBytes());
		}
		catch (Exception e) {
			System.out.println("[ChinaHat] Couldn't save config! Error: " + e.getMessage());
		}
	}

	public static String getHex(Color color) {
		StringBuilder hex = new StringBuilder("#");
		hex.append(String.format("%02x", color.getRed()));
		hex.append(String.format("%02x", color.getGreen()));
		hex.append(String.format("%02x", color.getBlue()));
		return hex.toString().toUpperCase();
	}

}