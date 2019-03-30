package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.omega.WorldOmega;
import com.davoleo.testmod.omega.player.PlayerOmega;
import com.davoleo.testmod.omega.player.PlayerProperties;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 30/03/2019 / 21:20
 * Class: ItemWand
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class ItemWand extends Item {

    public ItemWand()
    {
        setTranslationKey(TestMod.MODID + ".wand");
        setRegistryName(new ResourceLocation(TestMod.MODID, "wand"));
        setCreativeTab(TestMod.testTab);
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        if (playerIn.isSneaking())
        {
            if (!worldIn.isRemote){
                WorldOmega worldOmega = WorldOmega.get(worldIn);
                float amount = worldOmega.extractOmega(worldIn, playerIn.getPosition());
                PlayerOmega playerOmega = PlayerProperties.getPlayerOmega(playerIn);
                playerOmega.setOmega(playerOmega.getOmega() + amount);
                return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        } else {
          if (!worldIn.isRemote)
          {
              PlayerOmega playerOmega = PlayerProperties.getPlayerOmega(playerIn);
              if (playerOmega.getOmega() >= 3)
              {
                  worldIn.playSound(null, playerIn.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1F, 1F);
                  playerOmega.setOmega(playerOmega.getOmega() - 3);
              }
              return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
          }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
