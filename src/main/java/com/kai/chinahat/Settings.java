package com.kai.chinahat;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Settings {

	// Main toggle
	public static boolean hat = true;
	public static boolean on_self = true;
	public static String on_others = "None"; // None/Custom/Party/Custom+Party/All
	// List (Set) of players to render hat on
	public static final Set<String> on_players_uuid = new HashSet<>();
	public static final Set<String> on_players_name = new HashSet<>();

	// Show hat in first person
	public static boolean first_person = false;
	// Rainbow/Gradient only (smaller = faster) (only whole numbers, can't be 0)
	public static int speed = 10;
	// Rainbow only (0.00 is 0%, 1.00 is 100%) (0 is grayscale)
	public static float saturation = 1.00f;
	// Offset hat if helmets, skulls, blocks on head for Everyone/Others/Self/Nobody
	public static String offset = "Everyone";
	// Tilt hat with head
	public static boolean tilt = false;

	// Outline settings
	public static boolean outline = true; // Main Outline toggle
	public static float outline_thickness = 2.50f; // Outline thickness (Over 5 looks shit, so it's capped at 5)
	public static String outline_mode = "Rainbow"; // Solid/Gradient/Rainbow
	public static float outline_opacity = 0.50f; // (0.00 is 0%, 1.00 is 100%)
	public static Color outline_solid = new Color(0, 0, 0);
	public static Color outline_gradient1 = new Color(0, 0, 0);
	public static Color outline_gradient2 = new Color(0, 0, 0);

	// Top hat settings
	public static String top_mode = "Rainbow"; // Solid/Gradient/Rainbow
	public static float top_opacity = 0.70f; // (0.00 is 0%, 1.00 is 100%)
	public static Color top_solid = new Color(0, 0, 255);
	public static Color top_gradient1 = new Color(115, 0, 255);
	public static Color top_gradient2 = new Color(255, 0, 50);

	// Bottom hat settings
	public static String bottom_mode = "Rainbow"; // Solid/Gradient/Rainbow
	public static float bottom_opacity = 0.20f; // (0.00 is 0%, 1.00 is 100%)
	public static Color bottom_solid = new Color(0, 175, 255);
	public static Color bottom_gradient1 = new Color(115, 0, 255);
	public static Color bottom_gradient2 = new Color(255, 0, 50);



	public static void reset() {
		// Main toggle
		hat = true;
		on_self = true;
		on_others = "None"; // None/Custom/Party/Custom+Party/All
		// List (Set) of players to render hat on
		on_players_uuid.clear();
		on_players_name.clear();

		// Show hat in first person
		first_person = false;
		// Rainbow/Gradient only (smaller = faster) (only whole numbers, can't be 0)
		speed = 10;
		// Rainbow only (0.00 is 0%, 1.00 is 100%) (0 is grayscale)
		saturation = 1.00f;
		// Offset hat for helmets/skulls/blocks on head
		offset = "Everyone";
		// Tilt hat with head
		tilt = false;

		// Outline settings
		outline = true; // Main Outline toggle
		outline_thickness = 2.50f; // Outline thickness (Over 5 looks shit, so it's capped at 5)
		outline_mode = "Rainbow"; // Solid/Gradient/Rainbow
		outline_opacity = 0.50f; // (0.00 is 0%, 1.00 is 100%)
		outline_solid = new Color(0, 0, 0);
		outline_gradient1 = new Color(0, 0, 0);
		outline_gradient2 = new Color(0, 0, 0);

		// Top hat settings
		top_mode = "Rainbow"; // Solid/Gradient/Rainbow
		top_opacity = 0.70f; // (0.00 is 0%, 1.00 is 100%)
		top_solid = new Color(0, 0, 255);
		top_gradient1 = new Color(115, 0, 255);
		top_gradient2 = new Color(255, 0, 50);

		// Bottom hat settings
		bottom_mode = "Rainbow"; // Solid/Gradient/Rainbow
		bottom_opacity = 0.20f; // (0.00 is 0%, 1.00 is 100%)
		bottom_solid = new Color(0, 175, 255);
		bottom_gradient1 = new Color(115, 0, 255);
		bottom_gradient2 = new Color(255, 0, 50);
	}

}