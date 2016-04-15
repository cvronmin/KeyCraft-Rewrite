package org.nulla.kcrw.event;

import org.nulla.kcrw.KCUtils;
import org.nulla.kcrw.client.KCClientUtils;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerDrawHUD {
	
	@SubscribeEvent
	public void drawAuroraPoint(RenderGameOverlayEvent.Pre event) {
		
		int width = event.resolution.getScaledWidth();
		int height = event.resolution.getScaledHeight();
		
		KCClientUtils.drawAuroraStrip(width, height);
		KCClientUtils.drawSkillSlot(width, height);
	}

}
