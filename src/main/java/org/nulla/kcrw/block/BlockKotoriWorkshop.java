package org.nulla.kcrw.block;

import org.nulla.kcrw.KCUtils;
import org.nulla.kcrw.gui.GuiKotoriWorkshop;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockKotoriWorkshop extends KCBlockBase {

	public BlockKotoriWorkshop() {
		super(Material.iron);
	}

	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			return true;
		}
		KCUtils.getMC().displayGuiScreen(new GuiKotoriWorkshop(KCUtils.getMC().currentScreen, playerIn));
		return true;
    }

}
