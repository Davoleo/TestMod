package com.davoleo.testmod.entity.sphere;

import com.davoleo.testmod.init.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.init.Particles;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

/*************************************************
 * Author: Davoleo
 * Date / Hour: 13/04/2019 / 18:09
 * Class: EntitySphere
 * Project: Test_mod
 * Copyright - © - Davoleo - 2019
 **************************************************/

public class EntitySphere extends Entity {

    private EntityLivingBase shootingEntity;

    private int ticksAlive;
    private int ticksInAir;
    private double accelerationX;
    private double accelerationY;
    private double accelerationZ;

    public EntitySphere(World worldIn) {
        super(ModEntities.TYPE_SPHERE, worldIn);
        this.setSize(1.0F, 1.0F);
    }

    public EntitySphere(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(ModEntities.TYPE_SPHERE, worldIn);
        this.shootingEntity = shooter;
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(shooter.posX, shooter.posY, shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        double d0 = MathHelper.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
        this.accelerationX = accelX / d0 * 0.1D;
        this.accelerationY = accelY / d0 * 0.1D;
        this.accelerationZ = accelZ / d0 * 0.1D;
    }

    @Override
    public void tick() {
        if (this.world.isRemote || this.world.isBlockLoaded(new BlockPos(this))) {
            super.tick();

            ++this.ticksInAir;
            RayTraceResult raytraceresult = ProjectileHelper.forwardsRaycast(this, true, this.ticksInAir >= 25, this.shootingEntity);

            if (raytraceresult != null && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                this.onImpact(raytraceresult);
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            ProjectileHelper.rotateTowardsMovement(this, 0.2F);
            float f = this.getMotionFactor();

            if (this.isInWater()) {
                for (int i = 0; i < 4; ++i) {
                    this.world.spawnParticle(Particles.BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ);
                }

                f = 0.8F;
            }

            this.motionX += this.accelerationX;
            this.motionY += this.accelerationY;
            this.motionZ += this.accelerationZ;
            this.motionX *= f;
            this.motionY *= f;
            this.motionZ *= f;
            this.setPosition(this.posX, this.posY, this.posZ);
        } else {
            this.remove();
        }
    }

    protected float getMotionFactor() {
        return 0.95F;
    }

    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (result.entity != null) {
                result.entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.shootingEntity), 10.0F);
            }
            this.remove();
        }
    }

    @Override
    protected void registerData() {

    }

    @Override
    protected void readAdditional(@Nonnull NBTTagCompound compound) {
        if (compound.hasKey("power")) {
            NBTTagList nbttaglist = compound.getList("power", 6);

            if (nbttaglist.size() == 3) {
                this.accelerationX = nbttaglist.getDouble(0);
                this.accelerationY = nbttaglist.getDouble(1);
                this.accelerationZ = nbttaglist.getDouble(2);
            }
        }

        this.ticksAlive = compound.getInt("life");

        if (compound.hasKey("direction") && compound.getList("direction", 6).size() == 3) {
            NBTTagList nbttaglist1 = compound.getList("direction", 6);
            this.motionX = nbttaglist1.getDouble(0);
            this.motionY = nbttaglist1.getDouble(1);
            this.motionZ = nbttaglist1.getDouble(2);
        } else {
            this.remove();
        }
    }

    @Override
    protected void writeAdditional(@Nonnull NBTTagCompound compound) {
        compound.setTag("direction", this.newDoubleNBTList(this.motionX, this.motionY, this.motionZ));
        compound.setTag("power", this.newDoubleNBTList(this.accelerationX, this.accelerationY, this.accelerationZ));
        compound.setInt("life", this.ticksAlive);
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public float getCollisionBorderSize() {
        return 1.0F;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            this.markVelocityChanged();

            if (source.getTrueSource() != null) {
                Vec3d vec3d = source.getTrueSource().getLookVec();

                if (vec3d != null) {
                    this.motionX = vec3d.x;
                    this.motionY = vec3d.y;
                    this.motionZ = vec3d.z;
                    this.accelerationX = this.motionX * 0.1D;
                    this.accelerationY = this.motionY * 0.1D;
                    this.accelerationZ = this.motionZ * 0.1D;
                }

                if (source.getTrueSource() instanceof EntityLivingBase) {
                    this.shootingEntity = (EntityLivingBase) source.getTrueSource();
                }

                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public float getBrightness() {
        return 1.0F;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getBrightnessForRender() {
        return 15728880;
    }
}
