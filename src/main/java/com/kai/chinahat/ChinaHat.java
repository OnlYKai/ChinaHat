package com.kai.chinahat;

import com.kai.chinahat.utils.ColorGetter;
import com.kai.chinahat.utils.ColorUtil;
import com.kai.chinahat.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static com.kai.chinahat.Settings.*;
import static com.kai.chinahat.utils.PartyGetter.inParty;

public class ChinaHat {

	private final Minecraft mc = Minecraft.getMinecraft();

	public void onRender3DEvent(float partialTicks) {
		if (mc.thePlayer == null || mc.theWorld == null)
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

			ItemStack helmet = player.getCurrentArmor(3);

			boolean self = player == mc.thePlayer;
			double sneakOffset = player.isSneaking() ? 0.25 : 0;
			double helmetOffset = 0;

			if (offset.equals("Nobody") || offset.equals("Others") && self || offset.equals("Self") && !self || helmet == null)
				helmetOffset = 0.01 + (tilt ? 0.02 : 0);
			else if (helmet.getItem() instanceof ItemArmor)
				helmetOffset = 0.07 + (tilt ? 0.02 : 0);
			else if (helmet.getItem() instanceof ItemSkull)
				helmetOffset = 0.11 + (tilt ? 0.03 : 0);
			else if (helmet.getItem() instanceof ItemBlock)
				helmetOffset = 0.07 + (tilt ? 0.02 : 0);



			if (self && !first_person && mc.gameSettings.thirdPersonView == 0)
				continue;

			if (self && !on_self)
				continue;

			if (!self) {
				if (on_others.equals("None"))
					continue;
				else if (on_others.equals("Custom") && !on_players.contains(player.getName()))
					continue;
				else if (on_others.equals("Party") && !inParty.contains(player.getName()))
					continue;
				else if (on_others.equals("Custom+Party") && !on_players.contains(player.getName()) && !inParty.contains(player.getName()))
					continue;
			}



			GL11.glPushMatrix();

			double posX = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks - renderPosX;
			double posY = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks - renderPosY;
			double posZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks - renderPosZ;

			AxisAlignedBB bb = player.getEntityBoundingBox();
			double height = bb.maxY - bb.minY;
			double radius = bb.maxX - bb.minX;

			float yaw = interpolate(player.prevRotationYawHead, player.rotationYawHead, partialTicks);
			float pitch = interpolate(player.prevRotationPitch, player.rotationPitch, partialTicks);



			GL11.glTranslated(posX, posY + height + helmetOffset - sneakOffset, posZ);

			GL11.glEnable(GL11.GL_LINE_SMOOTH);
			GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);

			if (tilt) {
				GL11.glRotated(yaw, 0, -1, 0);
				GL11.glRotated(pitch / 3.0, 1, 0, 0);
				GL11.glTranslated(0, 0, pitch / 270.0);
			}



			// Draw hat top
			GL11.glBegin(GL11.GL_TRIANGLE_FAN);
			RenderUtil.color(getColorTop(4).getRGB());
			GL11.glVertex3d(0, 0.3, 0);
			// Draw hat bottom
			for (int i = 0; i <= 180; i++) {
				RenderUtil.color(getColorBottom(i * 4).getRGB());
				GL11.glVertex3d(
						-Math.sin(i * Math.PI * 2 / 90) * radius,
						0,
						Math.cos(i * Math.PI * 2 / 90) * radius
				);
			}
			GL11.glVertex3d(0, 0.3, 0);
			GL11.glEnd();



			if (outline) {
				GL11.glLineWidth(outline_thickness);
				// Draw outline
				GL11.glBegin(GL11.GL_LINE_LOOP);
				for (int i = 0; i <= 180; i++) {
					RenderUtil.color(getColorOutline(i * 4).getRGB());
					GL11.glVertex3d(
							-Math.sin(i * Math.PI * 2 / 90) * radius,
							0,
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