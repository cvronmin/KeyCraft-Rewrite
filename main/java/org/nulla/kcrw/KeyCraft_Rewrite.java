package org.nulla.kcrw;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;

@Mod(modid = KeyCraft_Rewrite.MODID, name = KeyCraft_Rewrite.MODNAME, version = KeyCraft_Rewrite.VERSION)
public class KeyCraft_Rewrite {
	
	public static final String MODID = "KCRw";
	public static final String MODNAME = "KeyCraft Rewrite Ver.";
    public static final String VERSION = "Demo";
    
    @SidedProxy(clientSide = "org.nulla.kcrw.ClientProxy",
            	serverSide = "org.nulla.kcrw.CommonProxy")
    public static CommonProxy proxy;
 
    @Instance("KeyCraft_Rewrite")
    public static KeyCraft_Rewrite instance;
    
    public static CreativeTabs KCCreativeTab = new KCCreativeTab("KCRw");
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    	proxy.preInit(event);
    	
    	KCItems.InitItems();
    	
    	KCBlocks.InitBlocks();
    	    	
    }
    
    @EventHandler
    public void Init(FMLInitializationEvent event) {
    	
    	proxy.init(event);
    	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    	proxy.postInit(event);
    	
    }

}
