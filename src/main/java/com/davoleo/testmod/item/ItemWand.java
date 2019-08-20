package com.davoleo.testmod.item;

import com.davoleo.testmod.TestMod;
import com.davoleo.testmod.entity.sphere.EntitySphere;
import com.davoleo.testmod.omega.WorldOmega;
import com.davoleo.testmod.omega.player.PlayerOmega;
import com.davoleo.testmod.omega.player.PlayerProperties;
import com.davoleo.testmod.util.RayTraceUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

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
        super(new Properties().group(TestMod.testTab));
        setRegistryName(new ResourceLocation(TestMod.MODID, "wand"));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        WandMode mode = getMode(stack);
        tooltip.add(new TextComponentString("Mode: " + mode.name()));
    }

    //Wand mode is stored in NBT so that it's not shared between all the wands
    private WandMode getMode(ItemStack stack)
    {
        if (!stack.hasTag())
            return WandMode.SPHERE;
        return WandMode.values()[stack.getTag().getInt("mode")];
    }

    public void toggleMode(EntityPlayer player, ItemStack stack)
    {
        WandMode mode = getMode(stack);
        if (mode == WandMode.SPHERE)
            mode = WandMode.LEVITATE;
        else
            mode = WandMode.SPHERE;
        player.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "Switched to " + mode.name() + " mode"), false);
        if (!stack.hasTag())
            stack.setTag(new NBTTagCompound());
        stack.getTag().setInt("mode", mode.ordinal());
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {
        if (playerIn.isSneaking())
        {
            if (!worldIn.isRemote){
                chargeOmega(worldIn, playerIn);
                return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        } else {
          if (!worldIn.isRemote)
          {
              switch (getMode(playerIn.getHeldItem(handIn)))
              {
                  case SPHERE:
                      fireSphere(worldIn, playerIn);
                      break;
                  case LEVITATE:
                      levitate(worldIn, playerIn);
                      break;
              }
              return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
          }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private void chargeOmega(World worldIn, EntityPlayer playerIn)
    {
        WorldOmega worldOmega = WorldOmega.get(worldIn);
        float amount = worldOmega.extractOmega(worldIn, playerIn.getPosition());
        playerIn.getCapability(PlayerProperties.PLAYER_OMEGA).ifPresent(playerOmega1 -> playerOmega1.setOmega(playerOmega1.getOmega() + amount));
    }

    private void fireSphere(World world, EntityPlayer player)
    {
        player.getCapability(PlayerProperties.PLAYER_OMEGA).ifPresent(playerOmega -> {
            if (playerOmega.getOmega() >= 0.5) {
                world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1F, 1F);
                playerOmega.setOmega(playerOmega.getOmega() - 0.5F);
                RayTraceUtil.Beam beam = new RayTraceUtil.Beam(world, player, 20);
                spawnSphere(player, beam);
            }
        });
    }

    private void spawnSphere(EntityPlayer player, RayTraceUtil.Beam beam)
    {
        Vec3d start = beam.getStart();
        Vec3d lookVec = beam.getLookVec();
        double accelX = lookVec.x * 1D;
        double accelY = lookVec.y * 1D;
        double accelZ = lookVec.z * 1D;

        EntitySphere laser = new EntitySphere(player.getEntityWorld(), player, accelX, accelY, accelZ);
        laser.posX = start.x;
        laser.posY = start.y;
        laser.posZ = start.z;

        player.getEntityWorld().spawnEntity(laser);
    }

    private void levitate(World world, EntityPlayer player)
    {
        player.getCapability(PlayerProperties.PLAYER_OMEGA).ifPresent(playerOmega -> {

            if (playerOmega.getOmega() >= .1f) {
                world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                playerOmega.setOmega(playerOmega.getOmega() - .1f);
                RayTraceUtil.Beam beam = new RayTraceUtil.Beam(world, player, 20);
                RayTraceUtil.rayTrace(beam, entity -> {
                    if (entity instanceof EntityLivingBase) {
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 60, 1));
                        return true;
                    } else {
                        return false;
                    }
                });
            }

        });
    }
}
