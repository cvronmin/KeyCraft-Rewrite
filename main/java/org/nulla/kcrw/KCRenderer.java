package org.nulla.kcrw;

import org.nulla.kcrw.client.KCUniteRenderFactory;
import org.nulla.kcrw.entity.EntityBaseball;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class KCRenderer {
	public static void init(){
		RenderingRegistry.registerEntityRenderingHandler(EntityBaseball.class, new KCUniteRenderFactory<EntityBaseball>(RenderSnowball.class, new Object[]{((Item)KCItems.baseball)}, true));
	}
}
