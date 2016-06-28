package org.nulla.nullacore;

import org.lwjgl.input.Keyboard;
import org.nulla.nullacore.event.HandlerDrawHUD;
import org.nulla.nullacore.event.HandlerKeyInput;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


/* 注册只在客户端发生的事件 */
public class ClientProxy extends CommonProxy {
	
	public static final KeyBinding kbSkill1 = new KeyBinding("nullalib.key.skill1", Keyboard.KEY_R, "nullalib.key.keytitle");
	public static final KeyBinding kbSkill2 = new KeyBinding("nullalib.key.skill2", Keyboard.KEY_F, "nullalib.key.keytitle");
	public static final KeyBinding kbSwitchSkill = new KeyBinding("nullalib.key.skill3", Keyboard.KEY_V, "nullalib.key.keytitle");
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		// 注册GUI、用户输入事件
    	MinecraftForge.EVENT_BUS.register(new HandlerDrawHUD());
    	MinecraftForge.EVENT_BUS.register(new HandlerKeyInput());
		
		//注册KeyBinding
		ClientRegistry.registerKeyBinding(kbSkill1);
		ClientRegistry.registerKeyBinding(kbSkill2);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		
	}

}
