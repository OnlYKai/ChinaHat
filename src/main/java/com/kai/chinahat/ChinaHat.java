package com.kai.chinahat;

import com.kai.chinahat.utils.ColorGetter;
import com.kai.chinahat.utils.ColorUtil;
import com.kai.chinahat.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static com.kai.chinahat.Settings.*;

public class ChinaHat {

	private final Minecraft mc = Minecraft.getMinecraft();

	public void onRender3DEvent(float partialTicks) {
		if (mc.thePlayer == null || mc.theWorld == null)
			return;

		if(!first_person && mc.gameSettings.thirdPersonView == 0)
			return;

		double renderPosX = mc.getRenderManager().viewerPosX;
		double renderPosY = mc.getRenderManager().viewerPosY;
		double renderPosZ = mc.getRenderManager().viewerPosZ;

		// OpenGL State Settings
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha(); // Ensure alpha is enabled
		GlStateManager.disableCull();
		GlStateManager.disableLighting();
		GlStateManager.disableTexture2D();
		GlStateManager.color(1, 1, 1, 1); // Set full color

		OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

		for (EntityPlayer player : mc.theWorld.playerEntities) {
			if (player.isDead || player.isInvisible())
				continue;

			boolean self = player == mc.thePlayer;
			double sneakOffset = player.isSneaking() ? 0.2 : 0;

			if (self && !on_self)
				continue;

			if (!self && !on_others)
				continue;

			GL11.glPushMatrix();

			double posX = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks - renderPosX;
			double posY = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks - renderPosY;
			double posZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks - renderPosZ;

			AxisAlignedBB bb = player.getEntityBoundingBox();
			double height = bb.maxY - bb.minY;
			double radius = bb.maxX - bb.minX;

			float yaw = interpolate(player.prevRotationYawHead, player.rotationYawHead, partialTicks);
			float pitch = interpolate(player.prevRotationPitch, player.rotationPitch, partialTicks);



			GL11.glTranslated(posX, posY + height, posZ);

			GL11.glEnable(GL11.GL_LINE_SMOOTH);
			GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);

			if (tilt) {
				if (self) GL11.glRotated(yaw, 0, -1, 0);
				GL11.glRotated(pitch / 3.0, 1, 0, 0);
				GL11.glTranslated(0, 0, pitch / 270.0);
			}



			// Draw hat top
			GL11.glBegin(GL11.GL_TRIANGLE_FAN);
			int color12 = getColorTop(4).getRGB();
			RenderUtil.color(color12);
			// Draw hat bottom
			GL11.glVertex3d(0, 0.3 - sneakOffset, 0);
			for (int i = 0; i <= 180; i++) {
				int color1 = getColorBottom(i * 4).getRGB();
				RenderUtil.color(color1);
				GL11.glVertex3d(
						-Math.sin(i * Math.PI * 2 / 90) * radius,
						0 - sneakOffset,
						Math.cos(i * Math.PI * 2 / 90) * radius
				);
			}
			GL11.glVertex3d(0, 0.3 - sneakOffset, 0);
			GL11.glEnd();



			if (outline) {
				GL11.glLineWidth(outline_thickness);
				// Draw outline
				GL11.glBegin(GL11.GL_LINE_LOOP);
				for (int i = 0; i <= 180; i++) {
					int color1 = getColorOutline(i * 4).getRGB();
					RenderUtil.color(color1);
					GL11.glVertex3d(
							-Math.sin(i * Math.PI * 2 / 90) * radius,
							0 - sneakOffset,
							Math.cos(i * Math.PI * 2 / 90) * radius
					);
				}
				GL11.glEnd();
			}

			GL11.glPopMatrix();
		}

		RenderUtil.resetColor();
		GlStateManager.enableCull();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glShadeModel(GL11.GL_FLAT);
	}

	private float interpolate(float prev, float current, float delta) {
		return prev + (current - prev) * delta;
	}



	private Color getColorOutline(int index) {
		Color returnColor = null;
		switch (outline_mode) {
			case "Solid":
				returnColor = outline_solid;
				break;
			case "Gradient":
				returnColor = new ColorGetter().getGradientColor(index, outline_gradient1, outline_gradient2);
				break;
			case "Rainbow":
				returnColor = new ColorGetter().getRainbowColor(index);
				break;
		}
		return ColorUtil.applyOpacity(returnColor, outline_opacity);
	}

	private Color getColorTop(int index) {
		Color returnColor = null;
		switch (top_mode) {
			case "Solid":
				returnColor = top_solid;
				break;
			case "Gradient":
				returnColor = new ColorGetter().getGradientColor(index, top_gradient1, top_gradient2);
				break;
			case "Rainbow":
				returnColor = new ColorGetter().getRainbowColor(index);
				break;
		}
		return ColorUtil.applyOpacity(returnColor, top_opacity);
	}

	private Color getColorBottom(int index) {
		Color returnColor = null;
		switch (bottom_mode) {
			case "Solid":
				returnColor = bottom_solid;
				break;
			case "Gradient":
				returnColor = new ColorGetter().getGradientColor(index, bottom_gradient1, bottom_gradient2);
				break;
			case "Rainbow":
				returnColor = new ColorGetter().getRainbowColor(index);
				break;
		}
		return ColorUtil.applyOpacity(returnColor, bottom_opacity);
	}

}