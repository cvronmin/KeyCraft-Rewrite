package org.nulla.kcrw;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.nulla.kcrw.event.HandlerDrawHUD;
import org.nulla.kcrw.event.HandlerKeyInput;
import org.nulla.kcrw.event.HandlerLivingAttack;
import org.nulla.kcrw.event.HandlerLivingDeath;
import org.nulla.kcrw.event.HandlerPlayerTick;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/* 注册只在客户端发生的事件 */
public class KCClientProxy extends KCCommonProxy {
	
	public static final KeyBinding kbSkill1 = new KeyBinding("kcrw.key.skill1", Keyboard.KEY_R, "kcrw.key.keytitle");
	public static final KeyBinding kbSkill2 = new KeyBinding("kcrw.key.skill2", Keyboard.KEY_F, "kcrw.key.keytitle");
	public static final KeyBinding kbSwitchSkill = new KeyBinding("kcrw.key.skill3", Keyboard.KEY_V, "kcrw.key.keytitle");
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		Display.setTitle("Welcome to KeyCraft's World!");
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		// 注册GUI、用户输入事件
    	MinecraftForge.EVENT_BUS.register(new HandlerDrawHUD());
    	MinecraftForge.EVENT_BUS.register(new HandlerKeyInput());
    	MinecraftForge.EVENT_BUS.register(new HandlerPlayerTick());
		MinecraftForge.EVENT_BUS.register(new HandlerLivingAttack());
		MinecraftForge.EVENT_BUS.register(new HandlerLivingDeath());
		KCBlocks.InitBlockRenderer();
		KCItems.InitItemsRenderer();
		//注册KeyBinding
		ClientRegistry.registerKeyBinding(kbSkill1);
		ClientRegistry.registerKeyBinding(kbSkill2);
		
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		
	}

}
