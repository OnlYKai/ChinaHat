package com.kai.chinahat.utils;

import org.lwjgl.opengl.GL11;

public class RenderUtil {

	public static void resetColor() {
		GL11.glColor4f(1, 1, 1, 1);
	}

	public static void color(int color) {
		float red = (color >> 16 & 255) / 255.0F;
		float green = (color >> 8 & 255) / 255.0F;
		float blue = (color & 255) / 255.0F;
		float alpha = (color >> 24 & 255) / 255.0F;
		GL11.glColor4f(red, green, blue, alpha);
	}
}