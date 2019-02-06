package com.davoleo.testmod.block.furnace;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.block.BlockTEBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 22/12/2018 / 22:25
 * Class: BlockFastFurnace
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class BlockFastFurnace extends BlockTEBase implements ITileEntityProvider {

     public static final ResourceLocation FAST_FURNACE = new ResourceLocation(TestMod.MODID, "fast_furnace");

     public static final PropertyDirection FACING = PropertyDirection.create("facing");
     public static final PropertyEnum<FurnaceState> STATE = PropertyEnum.create("state", FurnaceState.class);

    public BlockFastFurnace()
    {
        super(Material.IRON);

        //testmod:fast_furnace
        setRegistryName(FAST_FURNACE);
        setTranslationKey(TestMod.MODID + ".fast_furnace");

        setHarvestLevel("pickaxe", 1);

        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public IBlockState getActualState(@Nonnull IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        TileEntity te = worldIn instanceof ChunkCache ? ((ChunkCache) worldIn).getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK) : worldIn.getTileEntity(pos);
        if (te instanceof TileFastFurnace)
            return state.withProperty(STATE, ((TileFastFurnace) te).getState());
        return super.getActualState(state, worldIn, pos);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta)
    {
        return new TileFastFurnace();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound != null)
        {
            int energy = compound.getInteger("energy");
            int sizeIn = getItemCount(compound, "itemsIn");
            int sizeOut = getItemCount(compound, "itemsOut");
            addInformationLocalized(tooltip, "tooltip.testmod.fast_furnace", energy, sizeIn, sizeOut);
        }
    }

    private int getItemCount(NBTTagCompound compound, String key)
    {
        int inputCount = 0;
        NBTTagCompound compoundIn = (NBTTagCompound) compound.getTag(key);
        NBTTagList itemsInput = compoundIn.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < itemsInput.tagCount(); i++)
        {
            NBTTagCompound itemTags = itemsInput.getCompoundTagAt(i);
            if (!new ItemStack(itemTags).isEmpty())
                inputCount++;
        }
        return inputCount;
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING, STATE);
    }

    @Nonnull
    @Override
    public IBlockState getStateForPlacement(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ, int meta, @Nonnull EntityLivingBase placer, EnumHand hand)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FACING).getIndex();
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta & 7));
    }
}
