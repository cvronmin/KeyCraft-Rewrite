package org.nulla.kcrw;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import org.nulla.kcrw.client.KCMusicHelper;
import org.nulla.kcrw.entity.EntityBaseball;
import org.nulla.kcrw.entity.effect.EntityAuroraBlast;
import org.nulla.kcrw.event.HandlerChatCheating;
import org.nulla.kcrw.potion.KCPotion;
import org.nulla.kcrw.skill.SkillNetwork;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.EntityRegistry;

@Mod(modid = KeyCraft_Rewrite.MODID, name = KeyCraft_Rewrite.MODNAME, version = KeyCraft_Rewrite.VERSION)
public class KeyCraft_Rewrite {
	
	public static final String MODID = "kcrw";
	public static final String MODNAME = "KeyCraft Rewrite Ver.";
    public static final String VERSION = "Demo20160421";
    
    @SidedProxy(clientSide = "org.nulla.kcrw.KCClientProxy",
            	serverSide = "org.nulla.kcrw.KCCommonProxy")
    public static KCCommonProxy proxy;
    
    public static CreativeTabs KCCreativeTab = new KCCreativeTab("kcrw");
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	proxy.preInit(event);
    	
    	// 注册物品、方块
    	KCItems.InitItems();
    	KCBlocks.InitBlocks();
		
		// 注册效果
		KCPotion.init();
		
		// 注册实体
		int modID = 1;
    	EntityRegistry.registerModEntity(EntityBaseball.class, "Baseball", modID++, this, 128, 1, true);
    	EntityRegistry.registerModEntity(EntityAuroraBlast.class, "AuroraBlast", modID++, this, 128, 1, true);
    }
    
    @EventHandler
    public void Init(FMLInitializationEvent event) {
    	proxy.init(event);
    	
		// 注册聊天作弊
    	MinecraftForge.EVENT_BUS.register(new HandlerChatCheating());
    	
		// 注册网络事件
		SkillNetwork.getInstance().init();
		KCNetwork.getInstance().init();	
		
		// 注册音乐tick事件
		FMLCommonHandler.instance().bus().register(new KCMusicHelper());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit(event);
    }

}
