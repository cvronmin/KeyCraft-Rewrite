package org.nulla.kcrw.item;

import org.nulla.kcrw.KCMaterials;

import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAuroraSword extends KCItemBase {
    private float damage;
    private final ToolMaterial material;

    public ItemAuroraSword() {
        this.material = KCMaterials.AURORA_IRON;
        this.maxStackSize = 1;
        this.setMaxDamage(material.getMaxUses());
        this.damage = 4.0F + material.getDamageVsEntity();
    }

    public float func_150931_i() {
        return this.material.getDamageVsEntity();
    }

    @Override
    public float getStrVsBlock(ItemStack stack, Block block) {
        if (block == Blocks.web) {
            return 15.0F;
        } else {
            Material material = block.getMaterial();
            return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.gourd ? 1.0F : 1.5F;
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
    	stack.damageItem(1, p_77644_3_);
        return true;
    }

    /** 这个工具是用来打人的，打方块掉双倍耐久。 */
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn) {
        if ((double)blockIn.getBlockHardness(worldIn, pos) != 0.0D) {
        	stack.damageItem(2, playerIn);
        }
        return true;
    }

    /** 立体渲染。 */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BLOCK;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        return stack;
    }

    @Override
    public boolean canHarvestBlock(Block blockIn) {
        return blockIn == Blocks.web;
    }

    /** Return 附魔能力 of 这个物品, 大多数情况基于material。 */
    @Override
    public int getItemEnchantability() {
        return this.material.getEnchantability();
    }

    /** Return 这个物品能不能续一秒 in an 铁砧。 */
    @Override
    public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_) {
        ItemStack mat = this.material.getRepairItemStack();
        if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, p_82789_2_, false)) return true;
        return super.getIsRepairable(p_82789_1_, p_82789_2_);
    }

    /** 获得 a map of 物品 attribute modifiers, used by 剑物品 to 提高打击伤害。 */
    @Override
    public Multimap getItemAttributeModifiers() {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID, "Weapon modifier", (double)this.damage, 0));
        return multimap;
    }
}