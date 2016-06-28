package org.nulla.nullacore.api.potion;

import org.nulla.kcrw.KeyCraft_Rewrite;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class NullaPotion extends Potion {
	/** 在PotionUtils初始化，其他模块不要修改！ */
	public static int nextID;
		
	public final ResourceLocation mIcon;
		
	public NullaPotion(String name, ResourceLocation icon) {
		super(new ResourceLocation(KeyCraft_Rewrite.MODID, name.replace("kcrw.potion.", "")), false, 0);
		
		this.setPotionName(name);
		this.mIcon = icon;
	}
	
	/** 重载实现效果 */
	@Override
	public void performEffect(EntityLivingBase entity, int level) { }

	@Override
	public boolean isReady(int duration, int level)
	{
		return true;
	}
	
	// 以后实现
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc)
	{
		mc.getTextureManager().bindTexture(mIcon);
		
		x += 6;
		y += 7;
		final int width = 18, height = 18;
		
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldRenderer.pos(x, (y + height), 0.0D).tex(0,1).endVertex();
        worldRenderer.pos((x + width), (y + height), 0.0D).tex(1,1).endVertex();
        worldRenderer.pos((x + width), y, 0.0D).tex(1,0).endVertex();
        worldRenderer.pos(x, y, 0.0D).tex(0,0).endVertex();
        tessellator.draw();
	}

}
