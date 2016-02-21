package org.nulla.kcrw;

import org.lwjgl.opengl.GL11;
import org.nulla.kcrw.skill.Skill;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;

public class KCUtils {
	
	public static Minecraft getMC() {
		return Minecraft.getMinecraft();
	}
	
	/** 
	 * 只能在Client端用，以获取Client端的玩家信息（例如位置），不会改变Server端信息。
	 */
	public static EntityPlayer getPlayerCl() {
		return getMC().thePlayer;
	}
	
	/** 
	 * 绘制可缩放的纹理。<BR/>
	 * x，y代表绘制在屏幕上的位置。<BR/>
	 * u，v代表要绘制的部分在源素材图上的位置。<BR/>
	 * uW，vH代表要绘制的部分在源素材图上的大小。<BR/>
	 * w，h代表要绘制的部分的大小。<BR/>
	 * tW，tH代表源素材图的大小（缩放用）。
	 */
	public static void drawScaledCustomSizeModalRect(int x, int y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight) {
	    float f4 = 1.0F / tileWidth;
	    float f5 = 1.0F / tileHeight;
	    Tessellator tessellator = Tessellator.instance;
	    tessellator.startDrawingQuads();
	    tessellator.addVertexWithUV((double)x, (double)(y + height), 0.0D, (double)(u * f4), (double)((v + (float)vHeight) * f5));
	    tessellator.addVertexWithUV((double)(x + width), (double)(y + height), 0.0D, (double)((u + (float)uWidth) * f4), (double)((v + (float)vHeight) * f5));
	    tessellator.addVertexWithUV((double)(x + width), (double)y, 0.0D, (double)((u + (float)uWidth) * f4), (double)(v * f5));
	    tessellator.addVertexWithUV((double)x, (double)y, 0.0D, (double)(u * f4), (double)(v * f5));
	    tessellator.draw();
	}
	
	/** 
	 * 绘制HUD上的欧若拉条。<BR/>
	 * width，height代表屏幕的宽高。
	 */
	public static void drawAuroraStrip(int width, int height) {
		
		width *= 0.95;
		height *= 0.05;
		
		int currentAuroraPoint = Skill.getAuroraPoint(KCUtils.getPlayerCl());
		int maximumAuroraPoint = Skill.MAX_AURORA_POINT;
	        
		int length = Math.min(105 *  currentAuroraPoint / maximumAuroraPoint, 105);
		
		GL11.glEnable(GL11.GL_BLEND);
		KCUtils.getMC().getTextureManager().bindTexture(KCResources.aurora_strip_inside);
		KCUtils.drawScaledCustomSizeModalRect(width - length - 15, height + 1, 630 - 6 * length, 0, 6 * length + 90, 120, length + 15, 18, 720, 120);
		
		KCUtils.getMC().getTextureManager().bindTexture(KCResources.aurora_strip_outside);
		KCUtils.drawScaledCustomSizeModalRect(width - 120, height, 0, 0, 720, 120, 120, 20, 720, 120);
		GL11.glDisable(GL11.GL_BLEND);
	        
		String info = "Aurora: " + Skill.getAuroraPoint(KCUtils.getPlayerCl()) + " / " + Skill.MAX_AURORA_POINT;
		FontRenderer fontRenderer = KCUtils.getMC().fontRenderer;
		int color = 0x7FFFBF;
		//if (currentAuroraPoint <= MaximumAuroraPoint * 0.25) {color = 0xFF0000;}
		//else if (currentAuroraPoint <= MaximumAuroraPoint * 0.5) {color = 0xFFFF00;}
		//else if (currentAuroraPoint > MaximumAuroraPoint) {color = 0x00FF00;}
		fontRenderer.drawStringWithShadow(info, (int)width - 105, (int)height - 3, color);
		initDrawerState();
		getMC().renderEngine.bindTexture(Gui.icons);
	}
	
	/** 
	 * 初始化（绘制器的）撞钛鸡。
	 */
	public static void initDrawerState() {
		FontRenderer fontRenderer = getMC().fontRenderer;
		fontRenderer.drawString("", 0, 0, 0xFFFFFF);
	}
	
	/** 
	 * 绘制HUD上的技能格。<BR/>
	 * width，height代表屏幕的宽高。
	 */
	public static void drawSkill(int width, int height) {
		
		width *= 0.8;
		int height0 = (int) (height * 0.35);
		int height1 = (int) (height * 0.5);
		int height2 = (int) (height * 0.65);
		int height3 = (int) (height * 0.8);
			
		GL11.glEnable(GL11.GL_BLEND);
		//KCUtils.getMC().getTextureManager().bindTexture(KCResources.aurora_strip_outside);
		KCUtils.drawScaledCustomSizeModalRect(width, height0, 0, 0, 64, 64, 32, 32, 64, 64);
		KCUtils.drawScaledCustomSizeModalRect(width, height1, 0, 0, 64, 64, 32, 32, 64, 64);
		KCUtils.drawScaledCustomSizeModalRect(width, height2, 0, 0, 64, 64, 32, 32, 64, 64);
		KCUtils.drawScaledCustomSizeModalRect(width, height3, 0, 0, 64, 64, 32, 32, 64, 64);

		GL11.glDisable(GL11.GL_BLEND);
		initDrawerState();
		getMC().renderEngine.bindTexture(Gui.icons);
	}


}
