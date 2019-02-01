package com.davoleo.testmod.block.furnace;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.init.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 22/12/2018 / 22:25
 * Class: BlockFastFurnace
 * Project: Test_mod
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class BlockFastFurnace extends Block implements ITileEntityProvider {

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
        setCreativeTab(TestMod.testTab);

        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        //Se client side - non si fa niente
        if(worldIn.isRemote)
            return true;

        TileEntity te = worldIn.getTileEntity(pos);

        if (!(te instanceof TileFastFurnace))
            return false;

        playerIn.openGui(TestMod.instance, GuiHandler.GUI_FAST_FURNACE, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(Objects.requireNonNull(getRegistryName()), "inventory"));
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
    public void getDrops(@Nonnull NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, @Nonnull IBlockState state, int fortune)
    {
        TileEntity te = world.getTileEntity(pos);

        if (te instanceof TileFastFurnace)
        {
            ItemStack stack = new ItemStack(Item.getItemFromBlock(this));
            NBTTagCompound compound = new NBTTagCompound();
            ((TileFastFurnace)te).writeRestorableToNBT(compound);

            stack.setTagCompound(compound);
            drops.add(stack);
        } else {
            super.getDrops(drops, world, pos, state, fortune);
        }
    }

    @Override
    public boolean removedByPlayer(@Nonnull IBlockState state, World world, @Nonnull BlockPos pos, @Nonnull EntityPlayer player, boolean willHarvest)
    {
        if (willHarvest)
            return true; //If the block will be harvested, delay deletion of the block until getDrops
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    @Override
    public void harvestBlock(@Nonnull World worldIn, EntityPlayer player, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        worldIn.setBlockToAir(pos);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        TileEntity te = worldIn.getTileEntity(pos);

        if (te instanceof TileFastFurnace)
        {
            NBTTagCompound compound = stack.getTagCompound();
            if (compound != null)
                ((TileFastFurnace)te).readRestorableFromNBT(compound);
        }
    }

    private static final Pattern COMPILE = Pattern.compile("@", Pattern.LITERAL);

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        NBTTagCompound compound = stack.getTagCompound();
        if (compound != null)
        {
            int energy = compound.getInteger("energy");
            int inputCount = getItemCount(compound, "itemsIn");
            int outputCount = getItemCount(compound, "itemsOut");

            String translated = I18n.format("message.testmod.fast_furnace", energy, inputCount, outputCount);
            translated = COMPILE.matcher(translated).replaceAll("\u00a7");
            Collections.addAll(tooltip, StringUtils.split(translated, "\n"));

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
