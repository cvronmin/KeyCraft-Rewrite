package org.nulla.kcrw;

import org.nulla.kcrw.client.KCUniteRenderFactory;
import org.nulla.kcrw.client.renderer.RendererAuroraShield;
import org.nulla.kcrw.client.renderer.RendererNull;
import org.nulla.kcrw.client.renderer.RendererVibrationWave;
import org.nulla.kcrw.entity.EntityBaseball;
import org.nulla.kcrw.entity.EntityVibrationWave;
import org.nulla.kcrw.entity.effect.EntityAuroraShield;
import org.nulla.kcrw.entity.effect.EntityAuroraStorm;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class KCRenderer {
	public static void init(){
		RenderingRegistry.registerEntityRenderingHandler(EntityBaseball.class, new KCUniteRenderFactory<EntityBaseball>(RenderSnowball.class, true, new Object[]{((Item)KCItems.baseball)}));
		RenderingRegistry.registerEntityRenderingHandler(EntityAuroraShield.class, new KCUniteRenderFactory<EntityAuroraShield>(RendererAuroraShield.class));//盾的渲染
		RenderingRegistry.registerEntityRenderingHandler(EntityVibrationWave.class, new KCUniteRenderFactory<EntityVibrationWave>(RendererVibrationWave.class));//震荡波的渲染
		RenderingRegistry.registerEntityRenderingHandler(EntityAuroraStorm.class, new KCUniteRenderFactory<EntityAuroraStorm>(RendererNull.class));//风暴的渲染
	}
}
