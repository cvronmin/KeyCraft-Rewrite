package org.nulla.kcrw;

import java.util.ArrayList;
import java.util.List;

import org.nulla.kcrw.item.ItemAuroraArmor;
import org.nulla.kcrw.item.ItemAuroraBlade;
import org.nulla.kcrw.item.ItemAuroraIronIngot;
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
	
	public static Item music_player;
	
	public static Item peach_juice;
	
	public static Item aurora_blade;
	
    public static void InitItems() {
    	
    	aurora_iron_ingot = new ItemAuroraIronIngot()
			.setRecipe(new KCRecipe(new ItemStack[]{new ItemStack(Items.iron_ingot, 1)}, 1, 16))
			.setUnlocalizedName("auroraIronIngot");
			//.setTextureName("kcrw:aurora_iron_ingot");
    	GameRegistry.registerItem(aurora_iron_ingot, "aurora_iron_ingot");
    	
    	aurora_iron_helmet = new ItemAuroraArmor(0)
			.setUnlocalizedName("auroraIronHelmet")
			//.setTextureName("kcrw:aurora_iron_helmet")
			.setCreativeTab(KeyCraft_Rewrite.KCCreativeTab);
    	GameRegistry.registerItem(aurora_iron_helmet, "aurora_iron_helmet");
    	KCUtils.addEnchantedRecipe(aurora_iron_helmet, Enchantment.protection, 2, new Object[] { "AAA", "A A", 'A', aurora_iron_ingot });

    	aurora_iron_chestplate = new ItemAuroraArmor(1)
    		.setUnlocalizedName("auroraIronChestPlate")
    		//.setTextureName("kcrw:aurora_iron_chestplate")
			.setCreativeTab(KeyCraft_Rewrite.KCCreativeTab);
    	GameRegistry.registerItem(aurora_iron_chestplate, "aurora_iron_chestplate");
    	KCUtils.addEnchantedRecipe(aurora_iron_chestplate, Enchantment.protection, 2, new Object[] { "A A", "AAA", "AAA", 'A', aurora_iron_ingot });

    	aurora_iron_leggings = new ItemAuroraArmor(2)
			.setUnlocalizedName("auroraIronLeggings")
			//.setTextureName("kcrw:aurora_iron_leggings")
			.setCreativeTab(KeyCraft_Rewrite.KCCreativeTab);
    	GameRegistry.registerItem(aurora_iron_leggings, "aurora_iron_leggings");
    	KCUtils.addEnchantedRecipe(aurora_iron_leggings, Enchantment.protection, 2, new Object[] { "AAA", "A A", "A A", 'A', aurora_iron_ingot });

    	aurora_iron_boots = new ItemAuroraArmor(3)
			.setUnlocalizedName("auroraIronBoots")
			//.setTextureName("kcrw:aurora_iron_boots")
			.setCreativeTab(KeyCraft_Rewrite.KCCreativeTab);
    	GameRegistry.registerItem(aurora_iron_boots, "aurora_iron_boots");
    	//KCUtils.addEnchantedRecipe(aurora_iron_boots, Enchantment.protection, 2, new Object[] { "A A", "A A", 'A', aurora_iron_ingot });
    	KCUtils.addEnchantedRecipe(new ItemStack(aurora_iron_boots, 1, 200), Enchantment.protection, 2, new Object[] { "A A", "A A", 'A', aurora_iron_ingot });

    	music_player = new ItemMusicPlayer()
			.setRecipe(new KCRecipe(new ItemStack[]{new ItemStack(Blocks.jukebox, 1)}, 1, 128))
			.setUnlocalizedName("musicPlayer");
			//.setTextureName("kcrw:music_player");
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
    		//.setTextureName("kcrw:peach_juice");
    	GameRegistry.registerItem(peach_juice, "peach_juice");
    	
    	KCUtils.addEnchantedNamedRecipe(new ItemStack(Items.stick, 1), Enchantment.knockback, 99, "我是光棍", new Object[] { " A ", "ABA", " A " , 'A', Blocks.piston, 'B', Items.stick });
    	
    	aurora_blade = new ItemAuroraBlade()
			.setUnlocalizedName("auroraBlade");
			//.setTextureName("kcrw:aurora_blade");
    	GameRegistry.registerItem(aurora_blade, "aurora_blade");
    }
    public static void InitItemsRenderer(){
    	KCUtils.getMC().getRenderItem().getItemModelMesher().register(aurora_iron_ingot,0, new ModelResourceLocation("kcrw:aurora_iron_ingot", "inventory"));
    	KCUtils.getMC().getRenderItem().getItemModelMesher().register(aurora_iron_helmet,0, new ModelResourceLocation("kcrw:aurora_iron_helmet", "inventory"));
    	KCUtils.getMC().getRenderItem().getItemModelMesher().register(aurora_iron_chestplate,0, new ModelResourceLocation("kcrw:aurora_iron_chestplate", "inventory"));
    	KCUtils.getMC().getRenderItem().getItemModelMesher().register(aurora_iron_leggings,0, new ModelResourceLocation("kcrw:aurora_iron_leggings", "inventory"));
    	KCUtils.getMC().getRenderItem().getItemModelMesher().register(aurora_iron_boots,0, new ModelResourceLocation("kcrw:aurora_iron_boots", "inventory"));
    	KCUtils.getMC().getRenderItem().getItemModelMesher().register(aurora_blade,0, new ModelResourceLocation("kcrw:aurora_blade", "inventory"));
    	KCUtils.getMC().getRenderItem().getItemModelMesher().register(music_player,0, new ModelResourceLocation("kcrw:music_player", "inventory"));
    	KCUtils.getMC().getRenderItem().getItemModelMesher().register(peach_juice,0, new ModelResourceLocation("kcrw:peach_juice", "inventory"));
    }
}
