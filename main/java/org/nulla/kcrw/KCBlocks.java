package org.nulla.kcrw;

import org.nulla.kcrw.block.BlockKotoriWorkshop;
import org.nulla.kcrw.client.KCClientUtils;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class KCBlocks {

	public static Block KotoriWorkshop;

	public static void InitBlocks() {
		KotoriWorkshop = new BlockKotoriWorkshop()
				.setUnlocalizedName("KotoriWorkshop")
				.setHardness(1.5F)
				.setResistance(10.0F);
		GameRegistry.registerBlock(KotoriWorkshop, "KotoriWorkshop");
		GameRegistry.addShapedRecipe(new ItemStack(KotoriWorkshop, 1), new Object[] { " A ", "BCB", " B ", 'A', Blocks.crafting_table, 'C', Items.gold_ingot, 'B', Blocks.log });
	}
	public static void InitBlockRenderer(){
		KCClientUtils.registerItemRenderer(Item.getItemFromBlock(KotoriWorkshop), "kcrw:KotoriWorkshop");
	}
}
