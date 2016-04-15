package org.nulla.kcrw.entity;

import java.util.List;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class KCEntityThrowable extends Entity implements IProjectile {

	private int tileX = -1;
	private int tileY = -1;
	private int tileZ = -1;
	private Block field_145785_f;
	protected boolean inGround;
	public int throwableShake;
	/** 扔出Entity的生物 */
	protected EntityLivingBase thrower;
	private String throwerName;
	private int ticksInGround;
	private int ticksInAir;
	protected float mGravity = 0.03F;

	public KCEntityThrowable(World world, float width, float height) {
		super(world);
		this.setSize(width, height);
	}

	protected void entityInit() {
	}

	/**
	 * Checks if the entity is in range to render by using the past in distance
	 * and comparing it to its average edge length * 64 * renderDistanceWeight
	 * Args: distance
	 */
	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double distance) {
		double d1 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0D;

		if (Double.isNaN(d1)) {
			d1 = 4.0D;
		}

		d1 *= 64.0D;
		return distance < d1 * d1;
	}

	public KCEntityThrowable(World world, EntityLivingBase thrower, float width, float height, float gravity) {
		super(world);
		this.thrower = thrower;
		this.setSize(width, height);
		this.mGravity = gravity;
		this.setLocationAndAngles(thrower.posX, thrower.posY + (double) thrower.getEyeHeight(), thrower.posZ,
				thrower.rotationYaw, thrower.rotationPitch);
		this.posX -= (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.posY -= 0.10000000149011612D;
		this.posZ -= (double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.setPosition(this.posX, this.posY, this.posZ);
		float f = 0.4F;
		this.motionX = (double) (-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI)
				* MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * f);
		this.motionZ = (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI)
				* MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * f);
		this.motionY = (double) (-MathHelper.sin((this.rotationPitch) / 180.0F * (float) Math.PI) * f);
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
	}

	public KCEntityThrowable(World world, double posX, double posY, double posZ, float width, float height) {
		super(world);
		this.ticksInGround = 0;
		this.setSize(width, height);
		this.setPosition(posX, posY, posZ);
	}

	/**
	 * Similar to setArrowHeading, it's point the throwable entity to a x, y, z
	 * direction.
	 */
	public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) {
		float f = MathHelper.sqrt_double(x * x + y * y + z * z);
		x = x / (double) f;
		y = y / (double) f;
		z = z / (double) f;
		x = x + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
		y = y + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
		z = z + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
		x = x * (double) velocity;
		y = y * (double) velocity;
		z = z * (double) velocity;
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
		float f1 = MathHelper.sqrt_double(x * x + z * z);
		this.prevRotationYaw = this.rotationYaw = (float) (MathHelper.atan2(x, z) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float) (MathHelper.atan2(y, (double) f1) * 180.0D / Math.PI);
		this.ticksInGround = 0;
	}

	/** 设置Entity速度。 */
	@SideOnly(Side.CLIENT)
	public void setVelocity(double vx, double vy, double vz) {
		this.motionX = vx;
		this.motionY = vy;
		this.motionZ = vz;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(vx * vx + vz * vz);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(vx, vz) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(vy, (double) f) * 180.0D / Math.PI);
		}
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
		super.onUpdate();

		if (this.throwableShake > 0) {
			--this.throwableShake;
		}

		if (this.inGround)
        {
            if (this.worldObj.getBlockState(new BlockPos(this.tileX, this.tileY, this.tileZ)).getBlock() == this.field_145785_f)
            {
                ++this.ticksInGround;

                if (this.ticksInGround == 1200)
                {
                    this.setDead();
                }

                return;
            }

            this.inGround = false;
            this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
            this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
            this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
        }
        else
        {
            ++this.ticksInAir;
        }

		Vec3 vec3 = new Vec3(this.posX, this.posY, this.posZ);
		Vec3 vec31 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31);
		vec3 = new Vec3(this.posX, this.posY, this.posZ);
		vec31 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

		if (movingobjectposition != null) {
			vec31 = new Vec3(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord,
					movingobjectposition.hitVec.zCoord);
		}

		if (!this.worldObj.isRemote) {
			Entity entity = null;
			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox()
					.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d0 = 0.0D;
			EntityLivingBase entitylivingbase = this.getThrower();

			for (int j = 0; j < list.size(); ++j) {
				Entity entity1 = (Entity) list.get(j);

				if (entity1.canBeCollidedWith() && (entity1 != entitylivingbase || this.ticksInAir >= 5)) {
					float f = 0.3F;
					AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand((double) f, (double) f,
							(double) f);
					MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);

					if (movingobjectposition1 != null) {
						double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

						if (d1 < d0 || d0 == 0.0D) {
							entity = entity1;
							d0 = d1;
						}
					}
				}
			}

			if (entity != null) {
				movingobjectposition = new MovingObjectPosition(entity);
			}
		}

		if (movingobjectposition != null) {
			if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
					&& this.worldObj.getBlockState(movingobjectposition.getBlockPos()).getBlock() == Blocks.portal) {
				this.setPortal(movingobjectposition.getBlockPos());
			} else {
				this.onImpact(movingobjectposition);
			}
		}

		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

		for (this.rotationPitch = (float) (Math.atan2(this.motionY, (double) f1) * 180.0D / Math.PI); this.rotationPitch
				- this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
			;
		}

		while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
			this.prevRotationPitch += 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
			this.prevRotationYaw -= 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
			this.prevRotationYaw += 360.0F;
		}

		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
		float f2 = 0.99F;
		float f3 = this.getGravityVelocity();

		if (this.isInWater()) {
			for (int i = 0; i < 4; ++i) {
				float f4 = 0.25F;
				this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * (double) f4,
						this.posY - this.motionY * (double) f4, this.posZ - this.motionZ * (double) f4, this.motionX,
						this.motionY, this.motionZ);
			}

			f2 = 0.8F;
		}

		this.motionX *= (double) f2;
		this.motionY *= (double) f2;
		this.motionZ *= (double) f2;
		// 不因为重力下降
		this.motionY -= (double) getGravityVelocity();
		this.setPosition(this.posX, this.posY, this.posZ);
		if (this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ <= 0.0064F)
			this.setDead();
	}

	/**
	 * Gets the amount of gravity to apply to the thrown entity with each tick.
	 */
	protected float getGravityVelocity() {
		return mGravity;
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected abstract void onImpact(MovingObjectPosition p_70184_1_);

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setShort("xTile", (short) this.tileX);
		nbt.setShort("yTile", (short) this.tileY);
		nbt.setShort("zTile", (short) this.tileZ);
		ResourceLocation resourcelocation = (ResourceLocation) Block.blockRegistry
				.getNameForObject(this.field_145785_f);
		nbt.setString("inTile", resourcelocation == null ? "" : resourcelocation.toString());
		nbt.setByte("shake", (byte) this.throwableShake);
		nbt.setByte("inGround", (byte) (this.inGround ? 1 : 0));

		if ((this.throwerName == null || this.throwerName.length() == 0) && this.thrower != null
				&& this.thrower instanceof EntityPlayer) {
			this.throwerName = this.thrower.getName();
		}

		nbt.setString("ownerName", this.throwerName == null ? "" : this.throwerName);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound nbt) {
		this.tileX = nbt.getShort("xTile");
		this.tileY = nbt.getShort("yTile");
		this.tileZ = nbt.getShort("zTile");
		if (nbt.hasKey("inTile", 8)) {
			this.field_145785_f = Block.getBlockFromName(nbt.getString("inTile"));
		} else {
			this.field_145785_f = Block.getBlockById(nbt.getByte("inTile") & 255);
		}
		this.throwableShake = nbt.getByte("shake") & 255;
		this.inGround = nbt.getByte("inGround") == 1;
		this.throwerName = nbt.getString("ownerName");

		if (this.throwerName != null && this.throwerName.length() == 0) {
			this.throwerName = null;
		}
	}

	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0F;
	}

	public EntityLivingBase getThrower() {
		if (this.thrower == null && this.throwerName != null && this.throwerName.length() > 0) {
			this.thrower = this.worldObj.getPlayerEntityByName(this.throwerName);

			if (this.thrower == null && this.worldObj instanceof WorldServer) {
				try {
					Entity entity = ((WorldServer) this.worldObj).getEntityFromUuid(UUID.fromString(this.throwerName));

					if (entity instanceof EntityLivingBase) {
						this.thrower = (EntityLivingBase) entity;
					}
				} catch (Throwable var2) {
					this.thrower = null;
				}
			}
		}

		return this.thrower;
	}
}