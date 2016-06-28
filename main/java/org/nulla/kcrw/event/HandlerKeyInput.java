package org.nulla.kcrw.event;

import org.nulla.kcrw.KCClientProxy;
import org.nulla.kcrw.client.KCClientUtils;
import org.nulla.kcrw.client.gui.GuiSwitchSkill;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class HandlerKeyInput {
	
	@SubscribeEvent
	public void keyListener(KeyInputEvent event) {
		EntityPlayer player = KCClientUtils.getPlayerCl();
		if (KCClientProxy.kbSwitchSkill.isPressed()) {
			System.out.println("nice!.");
			KCClientUtils.getMC().displayGuiScreen(new GuiSwitchSkill(KCClientUtils.getMC().currentScreen, player));
		}
	}

}
