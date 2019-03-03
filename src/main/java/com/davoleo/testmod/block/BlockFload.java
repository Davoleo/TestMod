package com.davoleo.testmod.block;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.init.ModFluids;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 03/03/2019 / 19:03
 * Class: BlockFload
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class BlockFload extends BlockFluidClassic {

    public static final ResourceLocation FLOAD = new ResourceLocation(TestMod.MODID, "fload");

    public BlockFload()
    {
        super(ModFluids.fload, Material.WATER);
        setCreativeTab(TestMod.testTab);
        setTranslationKey(TestMod.MODID + ".fload");
        setRegistryName(FLOAD);
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        ModelResourceLocation fluidLoaction = new ModelResourceLocation(FLOAD, "fluid");

        StateMapperBase customState = new StateMapperBase() {
            @Nonnull
            @Override
            protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state)
            {
                return fluidLoaction;
            }
        };
        ModelLoader.setCustomStateMapper(this, customState);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(FLOAD, "inventory"));
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (entityIn instanceof EntityLivingBase)
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 60, 1));
    }
}
