package org.nulla.kcrw;

import org.nulla.kcrw.entity.EntityBaseball;
import org.nulla.kcrw.entity.EntityVibrationWave;
import org.nulla.kcrw.entity.effect.EntityAuroraBlast;
import org.nulla.kcrw.entity.effect.EntityAuroraShield;
import org.nulla.kcrw.entity.effect.EntityAuroraStorm;
import org.nulla.kcrw.potion.KCPotions;
import org.nulla.kcrw.skill.SkillsRw;
import org.nulla.nullacore.api.potion.NullaPotion;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod(modid = KeyCraft_Rewrite.MODID, name = KeyCraft_Rewrite.MODNAME, version = KeyCraft_Rewrite.VERSION)
public class KeyCraft_Rewrite {

	public static final String MODID = "kcrw";
	public static final String MODNAME = "KeyCraft Rewrite Ver.";
	public static final String VERSION = "TestInGroup";

	@SidedProxy(clientSide = "org.nulla.kcrw.KCClientProxy",
			serverSide = "org.nulla.kcrw.KCCommonProxy")
	public static KCCommonProxy proxy;
	@Instance(KeyCraft_Rewrite.MODID)
	public static KeyCraft_Rewrite instance;

	public static CreativeTabs KCCreativeTab = new KCCreativeTab("kcrw");


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {    	
		// 注册物品、方块
		KCItems.InitItems();
		KCBlocks.InitBlocks();

		// 注册实体
		int modID = 1;
		EntityRegistry.registerModEntity(EntityAuroraBlast.class, "AuroraBlast", modID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityAuroraShield.class, "AuroraShield", modID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityAuroraStorm.class, "AuroraStorm", modID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityBaseball.class, "Baseball", modID++, this, 128, 1, true);
		EntityRegistry.registerModEntity(EntityVibrationWave.class, "VibrationWave", modID++, this, 128, 1, true);
		proxy.preInit(event);
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		// 注册网络事件
		KCNetwork.getInstance().init();	

		// 注册技能
		SkillsRw.initSkills();

		// 注册效果
		KCPotions.initPotions();

		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

}
