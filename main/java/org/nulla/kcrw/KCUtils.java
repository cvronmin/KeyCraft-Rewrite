package org.nulla.kcrw;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class KCUtils {
	public static final Vec3 zero = new Vec3(0, 0, 0);
		
	public static void exchange(Object a, Object b) {
		Object c;
		c = a;
		a = b;
		b = c;
    }
	
	/** 
	 * 注册带附魔的合成表。
	 */
	public static void addEnchantedRecipe(ItemStack item, Enchantment enchantment, int enchantmentLevel, Object[] ingredientArray) {
		if (enchantment != null) {
			item.addEnchantment(enchantment, enchantmentLevel);
	    }
	    GameRegistry.addRecipe(item, ingredientArray);
	}
	
	/** 
	 * 注册带附魔的合成表。
	 */
	public static void addEnchantedRecipe(Item item, Enchantment enchantment, int enchantmentLevel, Object[] ingredientArray) {
		addEnchantedRecipe(new ItemStack(item), enchantment, enchantmentLevel, ingredientArray);
	}
	
	/** 
	 * 注册带附魔和名字的合成表。
	 */
	public static void addEnchantedNamedRecipe(ItemStack item, Enchantment enchantment, int enchantmentLevel, String name, Object[] ingredientArray) {
		if (enchantment != null) {
			item.addEnchantment(enchantment, enchantmentLevel);
	    }
		if (name != null) {
			item.setStackDisplayName(name);
		}
	    GameRegistry.addRecipe(item, ingredientArray);
	}
	
	/** 
	 * 获取player身上某种item的总数量。
	 */
	public static int getNumberOfItemInPlayer(EntityPlayer player, Item item) {
		int number = 0;
		for (int i = 0; i < 36; i++) {
			if (player.inventory.mainInventory[i] != null) {
				if (player.inventory.mainInventory[i].getItem().equals(item))
					number += player.inventory.mainInventory[i].stackSize;
			}
		}
		return number;
	}
	
	/** 
	 * 从player身上扣掉一定数量的item，用于合成。
	 */
	public static void minusNumberOfItemInPlayer(EntityPlayer player, Item item, int number) {
		for (int i = 0; i < player.inventory.mainInventory.length; i++) {
			if (player.inventory.mainInventory[i] != null
				&& player.inventory.mainInventory[i].getItem().equals(item)) {
				int size = player.inventory.mainInventory[i].stackSize;
				if (size >= number) {
					player.inventory.decrStackSize(i, number);
					number = 0;
				} else {
					player.inventory.decrStackSize(i, size);
					number -= size;
				}
			}
			if (number <= 0)
				break;
		}
	}
	
}
