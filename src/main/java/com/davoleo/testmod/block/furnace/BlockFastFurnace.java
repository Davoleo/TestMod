package com.davoleo.testmod.block.furnace;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockTEBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 22/12/2018 / 22:25
 * Class: BlockFastFurnace
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class BlockFastFurnace extends BlockTEBase {

     public static final ResourceLocation FAST_FURNACE = new ResourceLocation(TestMod.MODID, "fast_furnace");

     public static final DirectionProperty FACING = DirectionProperty.create("facing", EnumFacing.values());
     public static final EnumProperty<FurnaceState> STATE = EnumProperty.create("state", FurnaceState.class);

    public BlockFastFurnace()
    {
        super(Properties
                .create(Material.IRON)
        );

        //testmod:fast_furnace
        setRegistryName(FAST_FURNACE);
        //TODO 1.13 port
        //setHarvestLevel("pickaxe", 1);

        setDefaultState(getStateContainer().getBaseState().with(FACING, EnumFacing.NORTH));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(IBlockState state, IBlockReader world)
    {
        return new TileFastFurnace();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        NBTTagCompound compound = stack.getTag();
        if (compound != null)
        {
            int energy = compound.getInt("energy");
            int sizeIn = getItemCount(compound, "itemsIn");
            int sizeOut = getItemCount(compound, "itemsOut");
            addInformationLocalized(tooltip, "tooltip.testmod.fast_furnace", energy, sizeIn, sizeOut);
        }
    }

    private int getItemCount(NBTTagCompound compound, String key)
    {
        int inputCount = 0;
        NBTTagCompound compoundIn = (NBTTagCompound) compound.getTag(key);
        NBTTagList itemsInput = compoundIn.getList("Items", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < itemsInput.size(); i++)
        {
            NBTTagCompound itemTags = itemsInput.getCompound(i);
            if (!ItemStack.read(itemTags).isEmpty())
                inputCount++;
        }
        return inputCount;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
    {
        builder.add(FACING).add(STATE);
    }

    @Nullable
    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context)
    {
        //TODO 1.13 port
        return this.getDefaultState().with(FACING, EnumFacing.getFacingFromVector(context.getHitX(), context.getHitY(), context.getHitZ()));
    }
}
