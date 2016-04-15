package org.nulla.kcrw;

import java.util.ArrayList;
import java.util.List;

import org.nulla.kcrw.client.KCClientUtils;
import org.nulla.kcrw.item.ItemAuroraArmor;
import org.nulla.kcrw.item.ItemAuroraBlade;
import org.nulla.kcrw.item.ItemAuroraIronIngot;
import org.nulla.kcrw.item.ItemAuroraSword;
import org.nulla.kcrw.item.ItemAuroraTool;
import org.nulla.kcrw.item.ItemBaseball;
import org.nulla.kcrw.item.ItemFoodKC;
import org.nulla.kcrw.item.ItemMusicPlayer;
import org.nulla.kcrw.item.crafting.KCRecipe;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class KCItems {
	
	public static final ArrayList<Item> items = new ArrayList<Item>();
	
	public static Item aurora_iron_ingot;
	
    public static Item aurora_iron_helmet;
    public static Item aurora_iron_chestplate;
    public static Item aurora_iron_leggings;
    public static Item aurora_iron_boots;
	
    public static Item aurora_iron_axe;
    public static Item aurora_iron_pickaxe;
    public static Item aurora_iron_shovel;
    
    public static Item aurora_iron_sword;
    
    public static Item aurora_iron_hoe;
    
    public static Item baseball;
    
	public static Item music_player;
	
	public static Item peach_juice;
	
	public static Item aurora_blade;
	
    public static void InitItems() {
    	
    	aurora_iron_ingot = new ItemAuroraIronIngot()
			.setRecipe(new KCRecipe(new ItemStack[]{new ItemStack(Items.iron_ingot, 1)}, 1, 16))
			.setUnlocalizedName("auroraIronIngot");
    	GameRegistry.registerItem(aurora_iron_ingot, "aurora_iron_ingot");
    	
    	aurora_iron_helmet = new ItemAuroraArmor(0)
			.setUnlocalizedName("auroraIronHelmet");
    	GameRegistry.registerItem(aurora_iron_helmet, "aurora_iron_helmet");
    	KCUtils.addEnchantedRecipe(aurora_iron_helmet, Enchantment.protection, 2, new Object[] { "AAA", "A A", 'A', aurora_iron_ingot });

    	aurora_iron_chestplate = new ItemAuroraArmor(1)
    		.setUnlocalizedName("auroraIronChestPlate");
    	GameRegistry.registerItem(aurora_iron_chestplate, "aurora_iron_chestplate");
    	KCUtils.addEnchantedRecipe(aurora_iron_chestplate, Enchantment.protection, 2, new Object[] { "A A", "AAA", "AAA", 'A', aurora_iron_ingot });

    	aurora_iron_leggings = new ItemAuroraArmor(2)
			.setUnlocalizedName("auroraIronLeggings");
    	GameRegistry.registerItem(aurora_iron_leggings, "aurora_iron_leggings");
    	KCUtils.addEnchantedRecipe(aurora_iron_leggings, Enchantment.protection, 2, new Object[] { "AAA", "A A", "A A", 'A', aurora_iron_ingot });

    	aurora_iron_boots = new ItemAuroraArmor(3)
			.setUnlocalizedName("auroraIronBoots");
    	GameRegistry.registerItem(aurora_iron_boots, "aurora_iron_boots");
    	KCUtils.addEnchantedRecipe(aurora_iron_boots, Enchantment.protection, 2, new Object[] { "A A", "A A", 'A', aurora_iron_ingot });

    	aurora_iron_axe = new ItemAuroraTool("axe")
    			 .setUnlocalizedName("auroraIronAxe");
    			 GameRegistry.registerItem(aurora_iron_axe, "aurora_iron_axe");
    			 KCUtils.addEnchantedRecipe(aurora_iron_axe, Enchantment.efficiency, 2, new Object[] { "AA", "AB", " B" , 'A', aurora_iron_ingot, 'B', Items.stick });
    			 
    	/*aurora_iron_hoe = new ItemAuroraIronHoe()
       			.setUnlocalizedName("auroraIronHoe")
    	   		.setTextureName("kcrw:aurora_iron_hoe");
    			     	GameRegistry.registerItem(aurora_iron_hoe, "AuroraIronHoe");
    			     	KCUtils.addEnchantedRecipe(aurora_iron_hoe, Enchantment.looting, 1, new Object[] { "AA", " B", " B" , 'A', aurora_iron_ingot, 'B', Items.stick });
    	*/
    	
    	aurora_iron_pickaxe = new ItemAuroraTool("pickaxe")
    			.setUnlocalizedName("auroraIronPickaxe");
    	GameRegistry.registerItem(aurora_iron_pickaxe, "aurora_iron_pickaxe");
    	KCUtils.addEnchantedRecipe(aurora_iron_pickaxe, Enchantment.efficiency, 2, new Object[] { "AAA", " B ", " B ", 'A', aurora_iron_ingot, 'B', Items.stick });
    			 
    	aurora_iron_shovel = new ItemAuroraTool("shovel")
    			.setUnlocalizedName("auroraIronShovel");
    	GameRegistry.registerItem(aurora_iron_shovel, "aurora_iron_shovel");
    	KCUtils.addEnchantedRecipe(aurora_iron_shovel, Enchantment.efficiency, 2, new Object[] { "A", "B", "B", 'A', aurora_iron_ingot, 'B', Items.stick });
    			 
    			 
    	aurora_iron_sword = new ItemAuroraSword()
    			.setUnlocalizedName("auroraIronSword");
    	GameRegistry.registerItem(aurora_iron_sword, "aurora_iron_sword");
    	KCUtils.addEnchantedRecipe(aurora_iron_sword, Enchantment.sharpness, 2, new Object[] { "A", "A", "B", 'A', aurora_iron_ingot, 'B', Items.stick });
    			 	
    	baseball = new ItemBaseball()
    			.setRecipe(new KCRecipe(new ItemStack[]{new ItemStack(Items.leather, 4), new ItemStack(Blocks.wool, 1, 0)}, 16, 32))
    			.setUnlocalizedName("baseball");
    	GameRegistry.registerItem(baseball, "baseball");
    	
    	music_player = new ItemMusicPlayer()
			.setRecipe(new KCRecipe(new ItemStack[]{new ItemStack(Blocks.jukebox, 1)}, 1, 128))
			.setUnlocalizedName("musicPlayer");
    	GameRegistry.registerItem(music_player, "music_player");
		
    	peach_juice = new ItemFoodKC(3)
    		.setCallback(new ItemFoodKC.ICallback() {
				@Override
				public void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
    				player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 30 * 20));
				}
				@Override
				public void addInformation(ItemStack stack, EntityPlayer player, List information, boolean p_77624_4_) {
					information.add(StatCollector.translateToLocal("kcrw.item.intro.peachjuice"));
				}
    		})
    		.setAlwaysEdible()
    		.setRecipe(new KCRecipe(new ItemStack[]{new ItemStack(Items.slime_ball, 4), new ItemStack(Items.apple, 1)} , 4, 10))
    		.setUnlocalizedName("peachJuice");
    	GameRegistry.registerItem(peach_juice, "peach_juice");
    	
    	KCUtils.addEnchantedNamedRecipe(new ItemStack(Items.stick, 1), Enchantment.knockback, 99, "我是光棍", new Object[] { " A ", "ABA", " A " , 'A', Blocks.piston, 'B', Items.stick });
    	
    	aurora_blade = new ItemAuroraBlade()
			.setUnlocalizedName("auroraBlade")
			.setCreativeTab(null);
    	GameRegistry.registerItem(aurora_blade, "aurora_blade");
    }
    public static void InitItemsRenderer(){
    	KCClientUtils.registerItemRenderer(aurora_iron_ingot, "kcrw:aurora_iron_ingot");
    	KCClientUtils.registerItemRenderer(aurora_iron_helmet, "kcrw:aurora_iron_helmet");
    	KCClientUtils.registerItemRenderer(aurora_iron_chestplate, "kcrw:aurora_iron_chestplate");
    	KCClientUtils.registerItemRenderer(aurora_iron_leggings, "kcrw:aurora_iron_leggings");
    	KCClientUtils.registerItemRenderer(aurora_iron_boots, "kcrw:aurora_iron_boots");
    	KCClientUtils.registerItemRenderer(aurora_blade, "kcrw:aurora_blade");
    	KCClientUtils.registerItemRenderer(baseball, "kcrw:baseball");
    	KCClientUtils.registerItemRenderer(music_player, "kcrw:music_player");
    	KCClientUtils.registerItemRenderer(peach_juice, "kcrw:peach_juice");
    	KCClientUtils.registerItemRenderer(aurora_iron_axe, "kcrw:aurora_iron_axe");
    	//KCClientUtils.registerItemRenderer(aurora_iron_hoe, "kcrw:aurora_iron_hoe");
    	KCClientUtils.registerItemRenderer(aurora_iron_pickaxe, "kcrw:aurora_iron_pickaxe");
    	KCClientUtils.registerItemRenderer(aurora_iron_shovel, "kcrw:aurora_iron_shovel");
    	KCClientUtils.registerItemRenderer(aurora_iron_sword, "kcrw:aurora_iron_sword");
    }
}
