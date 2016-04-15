package org.nulla.kcrw.event;

import org.nulla.kcrw.KCClientProxy;
import org.nulla.kcrw.KCUtils;
import org.nulla.kcrw.client.KCClientUtils;
import org.nulla.kcrw.client.gui.GuiSwitchSkill;
import org.nulla.kcrw.skill.Skill;
import org.nulla.kcrw.skill.SkillUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class HandlerKeyInput {
	
	@SubscribeEvent
	public void keyListener(KeyInputEvent event) {
		EntityPlayer player = KCClientUtils.getPlayerCl();
		Skill skill = null;
		if (KCClientProxy.kbSkill1.isPressed()) {
			if (KCClientUtils.isShiftKeyDown()) {
				skill = SkillUtils.getSkillInSlot(player, 2);
				//System.out.println("使用技能2");
			} else {
				skill = SkillUtils.getSkillInSlot(player, 0);
				//System.out.println("使用技能0");
			}
		} else if (KCClientProxy.kbSkill2.isPressed()) {
		    if (KCClientUtils.isShiftKeyDown()) {
				skill = SkillUtils.getSkillInSlot(player, 3);
				//System.out.println("使用技能3");
		    } else {
				skill = SkillUtils.getSkillInSlot(player, 1);
				//System.out.println("使用技能1");
		    }
		}
		if (skill != null)
			skill.useSkill(player);
		
		if (KCClientProxy.kbSwitchSkill.isPressed()) {
			KCClientUtils.getMC().displayGuiScreen(new GuiSwitchSkill(KCClientUtils.getMC().currentScreen, player));
		}
	}

}
