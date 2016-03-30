package org.nulla.kcrw;

import org.nulla.kcrw.block.BlockKotoriWorkshop;

import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class KCBlocks {
	
	public static Block KotoriWorkshop;
	
	public static void InitBlocks() {
		KotoriWorkshop = new BlockKotoriWorkshop()
			.setUnlocalizedName("KotoriWorkshop")
			.setHardness(1.5F)
			.setResistance(10.0F);
    	GameRegistry.registerBlock(KotoriWorkshop, "KotoriWorkshop");
    }
	public static void InitBlockRenderer(){
		KCUtils.getMC().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(KotoriWorkshop),0, new ModelResourceLocation("kcrw:KotoriWorkshop", "inventory"));
	}
}
