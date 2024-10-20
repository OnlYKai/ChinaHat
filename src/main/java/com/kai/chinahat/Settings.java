package com.kai.chinahat;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Settings {

	// Main toggle
	public static boolean hat = true;
	public static boolean on_self = true;
	public static String on_others = "None"; // None/Custom/Party/Custom+Party/All
	// List of players to render hat on (divided by ",")
	public static List<String> on_players = new ArrayList<>();

	// Show hat in first person
	public static boolean first_person = false;
	// Rainbow/Gradient only (smaller = faster) (only whole numbers, can't be 0)
	public static int speed = 10;
	// Rainbow only (0.00 is 0%, 1.00 is 100%) (0 is grayscale)
	public static float saturation = 1.00f;
	// Offset hat for helmets/skulls/blocks on head
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

}