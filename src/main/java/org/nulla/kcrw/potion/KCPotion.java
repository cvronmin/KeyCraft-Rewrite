package org.nulla.kcrw.potion;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class KCPotion extends Potion {
	
	private static int nextID;
	
	public static void init()
	{
		try {
			Field potionTypesField = Potion.class.getDeclaredField("potionTypes");
			
			// 去掉final
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(potionTypesField, potionTypesField.getModifiers() & ~Modifier.FINAL);
			
			nextID = Potion.potionTypes.length;
			Potion[] newPotionTypes = new Potion[Potion.potionTypes.length + 32];
			// 复制
			for (int i = 0; i < Potion.potionTypes.length; i++)
				newPotionTypes[i] = Potion.potionTypes[i];
			
			// 修改
			potionTypesField.set(null, newPotionTypes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 注册所有效果
		KCPotions.initPotions();
	}
	
	
	public final ResourceLocation mIcon;
		
	public KCPotion(String name, ResourceLocation icon) {
		super(icon, false, 0);
		
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
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(x, (y + height), 0.0D).tex(0,1).endVertex();
        worldRenderer.pos((x + width), (y + height), 0.0D).tex(1,1).endVertex();
        worldRenderer.pos((x + width), y, 0.0D).tex(1,0).endVertex();
        worldRenderer.pos(x, y, 0.0D).tex(0,0).endVertex();
        tessellator.draw();
	}

}
