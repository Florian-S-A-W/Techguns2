package techguns.damagesystem;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import techguns.api.damagesystem.DamageType;
import techguns.deatheffects.EntityDeathUtils.DeathType;

public class TGExplosion {
	
	 /** whether or not the explosion sets fire to blocks around it */
    //private final boolean causesFire;
    /** whether or not this explosion spawns smoke particles */
    boolean damagesTerrain;
    Random random;
    World world;
    double x;
    double y;
    double z;
    Entity exploder;
    /** A list of ChunkPositions of blocks affected by this explosion */
    List<BlockPos> affectedBlockPositions;
    
    float[][][] dmgVolume;
    
    /** Maps players to the knockback vector applied by the explosion, to send to the client */
    //Map<EntityPlayer, Vec3d> playerKnockbackMap;
    Vec3d position;

    double primaryRadius;
    double primaryDamage;
    double secondaryRadius;
    double secondaryDamage;
    double blockDamageFactor;
    
     
//    @SideOnly(Side.CLIENT)
//    public TGExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, List<BlockPos> affectedPositions)
//    {
//        this(worldIn, entityIn, x, y, z, size, false, true, affectedPositions);
//    }
//
//    @SideOnly(Side.CLIENT)
//    public TGExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, boolean causesFire, boolean damagesTerrain, List<BlockPos> affectedPositions)
//    {
//        this(worldIn, entityIn, x, y, z, size, causesFire, damagesTerrain);
//        this.affectedBlockPositions.addAll(affectedPositions);
//    }

    public TGExplosion(World world, Entity exploder, double x, double y, double z, double primaryDamage, double secondaryDamage, double primaryRadius, double secondaryRadius, double blockDamageFactor)
    {
        this.random = new Random();
        this.affectedBlockPositions = Lists.<BlockPos>newArrayList();
        //this.playerKnockbackMap = Maps.<EntityPlayer, Vec3d>newHashMap();
        this.world = world;
        this.exploder = exploder;
   
        this.x = x;
        this.y = y;
        this.z = z;

        this.primaryDamage = primaryDamage;
        this.secondaryDamage = secondaryDamage;
        this.primaryRadius = primaryRadius;
        this.secondaryRadius = secondaryRadius;

        this.blockDamageFactor = blockDamageFactor;
        this.damagesTerrain = (blockDamageFactor > 0.01);
        
        this.position = new Vec3d(this.x, this.y, this.z);
    }

    /**
     * Does the first part of the explosion (destroy blocks)
     */
    public void doExplosion()
    {
        Set<BlockPos> set = Sets.<BlockPos>newHashSet();
        double totalRadius = Math.max(primaryRadius, secondaryRadius);
        int radius = (int) Math.ceil(totalRadius);
        
        int s = radius*2;
        dmgVolume = new float[s][s][s];
        
        double stepOffset = 0.30000001192092896D;
        int steps = (int)Math.ceil((double)radius/stepOffset);

        System.out.println(String.format("Radius = %d, VolumeSize = %d", radius, s));
        
        for (int j = -radius; j < radius; ++j)
        {
            for (int k = -radius; k < radius; ++k)
            {
                for (int l = -radius; l < radius; ++l)
                {
                    if (j == -radius || j == radius-1 || k == -radius || k == radius-1 || l == -radius || l == radius-1)
                    {
                        double dx = (double)((float)j / (float)radius);
                        double dy = (double)((float)k / (float)radius);
                        double dz = (double)((float)l / (float)radius);
                        //normalize
                        double length = Math.sqrt(dx * dx + dy * dy + dz * dz);
                        dx = dx / length;
                        dy = dy / length;
                        dz = dz / length;
                        //float f = this.size * (0.7F + this.world.rand.nextFloat() * 0.6F);
                        double px = 0; //this.x;
                        double py = 0; //this.y;
                        double pz = 0; //this.z;
                        
                        float dmgFactor = 1.0f;
                        
                        //for (float f = (float) totalRadius; f > 0.0F; f -= stepOffset) //0.22500001F)
                        
                        double explosionDamping = 0.0;
                        double explosionPower = this.primaryDamage;
                        
                        BlockPos prevPos = null;
                        
                        for (int i = 0; i < steps && explosionPower > 0.0; i++)
                        {
                            BlockPos blockpos = new BlockPos(x +px, y +py, z +pz);
                            IBlockState iblockstate = this.world.getBlockState(blockpos);
                            
                            double distance = this.position.distanceTo(new Vec3d(blockpos.getX()+0.5,blockpos.getY()+0.5,blockpos.getZ()+0.5));
                            if (distance <= primaryRadius) explosionPower = primaryDamage;
                            else if (distance <= secondaryRadius) explosionPower = secondaryDamage + ((distance-primaryRadius)/(secondaryRadius-primaryRadius)) * (primaryDamage-secondaryDamage);
                            else explosionPower = 0.0;
                            
                            float resistance = 0.0f;
                            if (iblockstate.getMaterial() != Material.AIR)
                            {
                            	resistance = iblockstate.getBlock().getExplosionResistance(world, blockpos, exploder, null);
//                                float f2 = this.exploder != null ? this.exploder.getExplosionResistance(this, this.world, blockpos, iblockstate) : iblockstate.getBlock().getExplosionResistance(world, blockpos, (Entity)null, this);
//                                f -= (f2 + 0.3F) * 0.3F;
                            	
                            	if (explosionPower-explosionDamping > 0.0f && resistance < explosionPower*blockDamageFactor && (this.exploder == null || this.exploder.canExplosionDestroyBlock(null, this.world, blockpos, iblockstate, (float)explosionPower)))
                                {
                                    set.add(blockpos);
                                    if (prevPos == null || !(prevPos.getX() == blockpos.getX() && prevPos.getY() == blockpos.getY() && prevPos.getZ() == blockpos.getZ())) {
                                    	explosionDamping += resistance;
                                    	int ix = (int) (Math.floor(px)+radius);
                                    	int iy = (int) (Math.floor(py)+radius);
                                    	int iz = (int) (Math.floor(pz)+radius);
                                    	//System.out.println(String.format("[%d][%d[%d]", ix, iy, iz));
                                    	if (ix >= 0 && iy >= 0 && iz >= 0)
                                    		dmgVolume[ix][iy][iz] = (float) explosionDamping;
                                    }
                                }else {
                                	explosionPower = 0.0;
                                }
                            }

                            

                            px += dx * stepOffset;
                            py += dy * stepOffset;
                            pz += dz * stepOffset;
                            
                            prevPos = blockpos;
                        }
                    }
                }
            }
        }

        this.affectedBlockPositions.addAll(set);
        float f3 = (float) (totalRadius);
        int k1 = MathHelper.floor(this.x - (double)f3 - 1.0D);
        int l1 = MathHelper.floor(this.x + (double)f3 + 1.0D);
        int i2 = MathHelper.floor(this.y - (double)f3 - 1.0D);
        int i1 = MathHelper.floor(this.y + (double)f3 + 1.0D);
        int j2 = MathHelper.floor(this.z - (double)f3 - 1.0D);
        int j1 = MathHelper.floor(this.z + (double)f3 + 1.0D);
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB((double)k1, (double)i2, (double)j2, (double)l1, (double)i1, (double)j1));
        net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(this.world, new Explosion(world, exploder, x, y, z, (float)Math.max(primaryRadius, secondaryRadius),false, (blockDamageFactor > 0.01)), list, f3);
        Vec3d vec3d = new Vec3d(this.x, this.y, this.z);

        for (int k2 = 0; k2 < list.size(); ++k2)
        {
            Entity entity = list.get(k2);

            int ix = (int) Math.floor(entity.getPosition().getX() - this.x) + radius;
            int iy = (int) Math.floor(entity.getPosition().getY()+entity.getEyeHeight() - this.y) + radius;
            int iz = (int) Math.floor(entity.getPosition().getZ() - this.z) + radius;
            
            //System.out.println(String.format("[%d][%d[%d]", ix, iy, iz));
            
            float dmgReduction = 0.0f; 
            if (ix >= 0 && iy >= 0 && iz >= 0 && ix < s && iy < s && iz < s) dmgReduction = dmgVolume[ix][iy][iz]; //TODO
            
            double damage;            
            double distance = this.position.distanceTo(new Vec3d(entity.posX, entity.posY+entity.getEyeHeight(), entity.posZ));
            if (distance <= primaryRadius) damage = primaryDamage;
            else if (distance <= secondaryRadius) damage = secondaryDamage + ((distance-primaryRadius)/(secondaryRadius-primaryRadius)) * (primaryDamage-secondaryDamage);
            else damage = 0.0;
            
            if (damage > 0.001) {
            	TGDamageSource tgs = TGDamageSource.causeExplosionDamage(null,exploder, DeathType.GORE);
            	entity.attackEntityFrom(tgs,  (float)damage * dmgReduction);        	         	
            }
            
//            if (!entity.isImmuneToExplosions())
//            {
//                double d12 = entity.getDistance(this.x, this.y, this.z) / (double)f3;
//
//                if (d12 <= 1.0D)
//                {
//                    double ex = entity.posX - this.x;
//                    double ey = entity.posY + (double)entity.getEyeHeight() - this.y;
//                    double ez = entity.posZ - this.z;
//                    double e_dist = (double)MathHelper.sqrt(ex * ex + ey * ey + ez * ez);
//
//                    if (e_dist != 0.0D)
//                    {
//                        ex = ex / e_dist;
//                        ey = ey / e_dist;
//                        ez = ez / e_dist;
//                        double d14 = (double)this.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
//                        double d10 = (1.0D - d12) * d14;
//                        entity.attackEntityFrom(DamageSource.causeExplosionDamage(this), (float)((int)((d10 * d10 + d10) / 2.0D * 7.0D * (double)f3 + 1.0D)));
//                        double d11 = d10;
//
//                        if (entity instanceof EntityLivingBase)
//                        {
//                            d11 = EnchantmentProtection.getBlastDamageReduction((EntityLivingBase)entity, d10);
//                        }
//
//                        entity.motionX += ex * d11;
//                        entity.motionY += ey * d11;
//                        entity.motionZ += ez * d11;
//
//                        if (entity instanceof EntityPlayer)
//                        {
//                            EntityPlayer entityplayer = (EntityPlayer)entity;
//
//                            if (!entityplayer.isSpectator() && (!entityplayer.isCreative() || !entityplayer.capabilities.isFlying))
//                            {
//                                this.playerKnockbackMap.put(entityplayer, new Vec3d(ex * d10, ey * d10, ez * d10));
//                            }
//                        }
//                    }
//                }
//            }
        }
        
        breakBlocks();
    }
    
    private void breakBlocks() {
    	if (this.damagesTerrain)
        {
            for (BlockPos blockpos : this.affectedBlockPositions)
            {
                IBlockState iblockstate = this.world.getBlockState(blockpos);
                Block block = iblockstate.getBlock();


                if (iblockstate.getMaterial() != Material.AIR)
                {
                    if (block.canDropFromExplosion(null))
                    {
                        //block.dropBlockAsItemWithChance(this.world, blockpos, this.world.getBlockState(blockpos), 1.0f, 0);
                    }

                    block.onBlockExploded(this.world, blockpos, null);
                }
            }
        }

    }

}
