package com.davoleo.testmod.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;

import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 22/02/2019 / 19:03
 * Class: GuardAIFollow
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class GuardAIFollow extends EntityAIBase {

    private final EntityLiving entity;
    private EntityPlayer followedPlayer;
    private final double speedModifier;
    private final PathNavigate navigation;
    private int timeToRecalcPath;
    private final float stopDistance;
    private float oldWaterCost;
    private final float areaSize;

    public GuardAIFollow(final EntityLiving entity, double speedModifier, float stopDistance, float areaSize)
    {
        this.entity = entity;
        this.speedModifier = speedModifier;
        this.navigation = entity.getNavigator();
        this.stopDistance = stopDistance;
        this.areaSize = areaSize;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute()
    {
        List<EntityPlayer> list = this.entity.world.getEntitiesWithinAABB(EntityPlayer.class, this.entity.getEntityBoundingBox().grow((double) this.areaSize));

        for (EntityPlayer player : list)
            if (!player.isInvisible())
            {
                this.followedPlayer = player;
                return true;
            }

        return false;
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return this.followedPlayer != null && !this.navigation.noPath() && this.entity.getDistanceSq(this.followedPlayer) > (Math.pow(this.stopDistance, 2));
    }

    @Override
    public void startExecuting()
    {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.entity.getPathPriority(PathNodeType.WATER);
        this.entity.setPathPriority(PathNodeType.WATER, 0F);
    }

    @Override
    public void resetTask()
    {
        this.followedPlayer = null;
        this.navigation.clearPath();
        this.entity.setPathPriority(PathNodeType.WATER, this.oldWaterCost);
    }

    @Override
    public void updateTask()
    {
        if (this.followedPlayer != null && !this.entity.getLeashed())
        {
            this.entity.getLookHelper().setLookPositionWithEntity(this.followedPlayer, 10F, (float) this.entity.getVerticalFaceSpeed());

            if (--this.timeToRecalcPath <= 0)
            {
                this.timeToRecalcPath = 10;
                double x = this.entity.posX - this.followedPlayer.posX;
                double y = this.entity.posY - this.followedPlayer.posY;
                double z = this.entity.posZ - this.followedPlayer.posZ;
                double vector3D = x * x + y * y + z * z;

                if (vector3D > Math.pow(this.stopDistance, 2))
                    this.navigation.tryMoveToEntityLiving(this.followedPlayer, this.speedModifier);
                else
                    this.navigation.clearPath();
            }
        }
    }
}
