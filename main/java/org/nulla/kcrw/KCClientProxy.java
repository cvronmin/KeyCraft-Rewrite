package org.nulla.kcrw;

import org.lwjgl.input.Keyboard;
import org.nulla.kcrw.event.HandlerDrawHUD;
import org.nulla.kcrw.event.HandlerKeyInput;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.*;

public class KCClientProxy extends KCCommonProxy {
	
	public static final KeyBinding kbSkill1 = new KeyBinding("kcrw.key.skill1", Keyboard.KEY_R, "kcrw.key.keytitle");
	public static final KeyBinding kbSkill2 = new KeyBinding("kcrw.key.skill2", Keyboard.KEY_F, "kcrw.key.keytitle");
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		
		// 注册GUI、用户输入事件
    	MinecraftForge.EVENT_BUS.register(new HandlerDrawHUD());
    	MinecraftForge.EVENT_BUS.register(new KCMusicHelper());
		FMLCommonHandler.instance().bus().register(new HandlerKeyInput());
		
		//注册KeyBinding
		ClientRegistry.registerKeyBinding(kbSkill1);
		ClientRegistry.registerKeyBinding(kbSkill2);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

}
