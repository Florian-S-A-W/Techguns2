package techguns.items.guns;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.RegistryEvent;
import java.util.Random;
import techguns.TGItems;
import techguns.TGPackets;
import techguns.TGSounds;
import techguns.TGuns;
import techguns.Techguns;
import techguns.api.capabilities.ITGExtendedPlayer;
import techguns.api.damagesystem.DamageType;
import techguns.api.render.IItemTGRenderer;
import techguns.capabilities.TGExtendedPlayer;
import techguns.capabilities.TGExtendedPlayerCapProvider;
import techguns.client.ClientProxy;
import techguns.client.ShooterValues;
import techguns.client.audio.TGSoundCategory;
import techguns.damagesystem.TGDamageSource;
import techguns.deatheffects.EntityDeathUtils.DeathType;
import techguns.entities.ai.EntityAIRangedAttack;
import techguns.entities.npcs.NPCTurret;
import techguns.entities.projectiles.EnumBulletFirePos;
import techguns.entities.projectiles.GenericProjectile;
import techguns.items.GenericItem;
import techguns.items.armors.GenericArmor;
import techguns.items.armors.ICamoChangeable;
import techguns.items.armors.TGArmorBonus;
import techguns.items.guns.ammo.AmmoType;
import techguns.items.guns.ammo.AmmoTypes;
import techguns.packets.GunFiredMessage;
import techguns.packets.ReloadStartedMessage;
import techguns.util.InventoryUtil;
import techguns.util.SoundUtil;
import techguns.util.TextUtil;
import techguns.api.guns.GunHandType;
import techguns.api.guns.IGenericGun;

public class GenericGun extends GenericItem implements IGenericGun, IItemTGRenderer, ICamoChangeable {
	protected static final float SOUND_DISTANCE=4.0f;
	
	// Weapon Stats
	boolean semiAuto=false; // = false;
	int minFiretime=4; // = 0;
	int clipsize=10; // = 10;
	int reloadtime=40; // = 40; //ticks
	float damage = 2.0f; // = 10;
	SoundEvent firesound = TGSounds.M4_FIRE;
	SoundEvent reloadsound = TGSounds.M4_RELOAD;
	SoundEvent firesoundStart = TGSounds.M4_FIRE;
	SoundEvent rechamberSound = null;
	int ammoCount; // ammo per reload
	// float recoil = 25.0f;
	float zoomMult = 1.0f;
	boolean canZoom = false;
	boolean toggleZoom = false;
	boolean fireCenteredZoomed=false;
	int ticksToLive = 40;
	float speed = 2.0f;

	float damageMin=1.0f;
	int damageDropStart=20;
	int damageDropEnd=40;
	float penetration = 0.0f;

	AmmoType ammoType = AmmoTypes.PISTOL_ROUNDS;

	boolean shotgun = false;
	boolean burst = false;
	float spread = 0.015f;
	int bulletcount = 7;
	float accuracy = 0.0f;

	int maxLoopDelay = 0;
	int recoiltime = 5; // ticks;

	int muzzleFlashtime = 5;
	boolean silenced = false;

	boolean checkRecoil = false;
	boolean checkMuzzleFlash = false;
	
	float AI_attackRange = 15.0F; //sqrt of actual distance
	int AI_attackTime = 60;
	int AI_burstCount = 0;
	int AI_burstAttackTime = 0;
	
	int camoCount=1;
	
	int lockOnTicks = 0; //MaximumLockOnTime
	int lockOnPersistTicks = 0;
	
	public ArrayList<ResourceLocation> textures;
	
	protected IProjectileFactory projectile;
	
	GunHandType handType = GunHandType.TWO_HANDED; 
	//Ammount of bullets the gun gets per bullet item
	//protected float shotsPerBullet;
	
	int miningAmmoConsumption = 1;
	
	float meleeDamagePwr = 6.0f;
	float meleeDamageEmpty = 2.0f;
	float digSpeed=1.0f;
	
	/**
	 * spread multiplier while shooting zoomed
	 */
	float zoombonus=1.0f;
	
	float radius=1.0f;
	double gravity=0.0f;

	boolean shootWithLeftClick=true;
	
	public float turretPosOffsetX=0.0f;
	public float turretPosOffsetY=0.0f;
	public float turretPosOffsetZ=0.0f;
	
	public static ArrayList<GenericGun> guns = new ArrayList<>();
	
	/**
	 * Should bind the texture on rendering?
	 */
	public boolean hasCustomTexture=true;

	
	private GenericGun(String name) {
		super(name,false);
		this.setMaxStackSize(1);
		this.setNoRepair();
		//TGuns.ITEMREGISTRY.register(this);
	}

	public GenericGun(String name, IProjectileFactory projectilefactory, AmmoType ammo, boolean semiAuto, int minFiretime, int clipsize, int reloadtime, float damage, SoundEvent firesound, SoundEvent reloadsound, int TTL, float accuracy){
		this(true, name,projectilefactory,ammo, semiAuto, minFiretime, clipsize, reloadtime, damage, firesound, reloadsound, TTL, accuracy);
	}
	
	public GenericGun setNoCustomTexture() {
		this.hasCustomTexture=false;
		return this;
	}
	
	public GenericGun(boolean addToGunList,String name, IProjectileFactory projectilefactory, AmmoType ammo, boolean semiAuto, int minFiretime, int clipsize, int reloadtime, float damage, SoundEvent firesound, SoundEvent reloadsound, int TTL, float accuracy){
		this(name);
		setMaxDamage(clipsize);
		
		this.ammoType = ammo;
		this.semiAuto = semiAuto;
		this.minFiretime = minFiretime;
		this.clipsize = clipsize;
		this.reloadtime = reloadtime;
		this.damage = damage;
		this.firesound = firesound;
		this.reloadsound = reloadsound;

		this.ammoCount=1;
		//this.shotsPerBullet=clipsize;
		this.ticksToLive=TTL;
		this.accuracy =accuracy;
		
		//Defaults to no drop
		this.damageDropStart=TTL;
		this.damageDropEnd=TTL;
		this.damageMin=damage;
		
		this.projectile = projectilefactory;
		
		if (addToGunList) {
			guns.add(this);
		}
	}
	
	public GenericGun setGravity(double grav) {
		this.gravity=grav;
		return this;
	}
	
	public GenericGun setAmmoCount(int count) {
		this.ammoCount=count;
		//this.shotsPerBullet=(this.clipsize*1.0f)/(this.ammoCount*1.0f);
		return this;
	}

	public GenericGun setZoom(float mult, boolean toggle, float bonus, boolean fireCenteredWhileZooming){
		this.canZoom=true;
		this.zoomMult = mult;
		this.toggleZoom = toggle;
		this.zoombonus=bonus;
		this.fireCenteredZoomed=fireCenteredWhileZooming;
		return this;
	}
	
	
	public GenericGun setShotgunSpred(int count, float spread){
		return this.setShotgunSpread(count, spread, false);
	}
	
	public GenericGun setShotgunSpread(int count, float spread, boolean burst){
		this.shotgun=true;
		this.spread=spread;
		this.bulletcount=count;
		this.burst=burst;
		return this;
	}
	
	public GenericGun setBulletSpeed(float speed){
		this.speed=speed;
		return this;
	}
	
	public GenericGun setPenetration(float pen){
		this.penetration = pen;
		return this;
	}
	
	public GenericGun setMuzzleFlashTime(int time) {
		this.muzzleFlashtime = time;
		return this;
	}

	public GenericGun setFiresoundStart(SoundEvent firesoundStart) {
		this.firesoundStart = firesoundStart;
		return this;
	}
	
	public GenericGun setMaxLoopDelay(int maxLoopDelay) {
		this.maxLoopDelay = maxLoopDelay;
		return this;
	}
	
	public GenericGun setRecoiltime(int recoiltime){
		this.recoiltime = recoiltime;
		return this;
	}
	
	public GenericGun setRechamberSound(SoundEvent rechamberSound) {
		this.rechamberSound = rechamberSound;
		return this;
	}
	
	/**
	 * @param x - Offset sideways, +right -left
	 * @param y - Offset height
	 * @param z - offset forward/backward
	 * @return
	 */
	public GenericGun setTurretPosOffset(float x, float y, float z){
		this.turretPosOffsetX=x;
		this.turretPosOffsetY=y;
		this.turretPosOffsetZ=z;
		return this;
	}
	
	protected int getScaledTTL(){
		return (int) Math.ceil(this.ticksToLive/this.speed);
	}
	
	/**
	 * @param ticks Time required to lock target
	 * @param lockExtraTicks Extra time that is added when the lock is complete
	 */
	public GenericGun setLockOn(int ticks, int lockExtraTicks) {
		this.lockOnTicks = ticks;
		this.lockOnPersistTicks = lockExtraTicks;
		return this;
	}
	

	public int getLockOnTicks() {
		return lockOnTicks;
	}

	public int getLockOnPersistTicks() {
		return lockOnPersistTicks;
	}

	/**
	 * Called only clientside!, requires packets for actions other than zoom (clientside
	 * @param player
	 * @param stack
	 */
	public boolean gunSecondaryAction(EntityPlayer player, ItemStack stack) {
		if (player.world.isRemote && canZoom  && this.toggleZoom && !player.isSneaking() && !ShooterValues.getPlayerIsReloading(player, false)) {
			ClientProxy cp = ClientProxy.get();
			if (cp.player_zoom != 1.0f) {
				cp.player_zoom= 1.0f;
			} else {
				cp.player_zoom = this.zoomMult;
			}
			return true;
    	}
		return false;
	}
	
	
	
	/*@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		if (handIn == EnumHand.MAIN_HAND && canZoom  && this.toggleZoom && !ShooterValues.getPlayerIsReloading(playerIn, false)) {
    		if (worldIn.isRemote) {
				ClientProxy cp = ClientProxy.get();
    			if (cp.player_zoom != 1.0f) {
    				cp.player_zoom= 1.0f;
    			} else {
    				cp.player_zoom = this.zoomMult;
    			}
    		}
    	}
		if (handIn == EnumHand.MAIN_HAND && canZoom) {
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}
		if(ShooterValues.getPlayerIsReloading(playerIn, false)) {
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
		//return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
	}*/

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if(this.shootWithLeftClick){
			return true;
		} else {
			if(this.getCurrentAmmo(stack)>=this.miningAmmoConsumption){
				return true;
			} else {
				return false;
			}
		}
	}

	public ItemStack getReloadItem(ItemStack stack) {
		return this.ammoType.getAmmo(this.getCurrentAmmoVariant(stack));
	}
	
	public int getAmmoCount() {
		return ammoCount;
	}
	
	public int getAmmoLeftCountTooltip(ItemStack item){
		int ammo = this.getCurrentAmmo(item);
		if(this.burst){
			return ammo*(this.bulletcount+1);
		} else {
			return ammo;
		}
	}

	public int getClipsizeTooltip() {
		if(this.burst){
			return clipsize*(this.bulletcount+1);
		} else {
			return clipsize;
		}
	}
	
	public int getAmmoLeftCount(int mags){
		int count=(this.clipsize/this.ammoCount) * mags; 
		if(this.burst){
			return count*(this.bulletcount+1);
		} else {
			return count;
		}
	}

	protected void spawnProjectile(final World world, final EntityLivingBase player, final ItemStack itemstack, float spread, float offset, float damagebonus, EnumBulletFirePos firePos) {
		/*GenericProjectile proj = new GenericProjectile(world, player, damage * damagebonus, speed, this.getScaledTTL(), spread, this.damageDropStart, this.damageDropEnd,
				this.damageMin * damagebonus, this.penetration, getDoBlockDamage(player), leftGun);*/
		
		GenericProjectile proj = this.projectile.createProjectile(this, world, player, damage * damagebonus, speed, this.getScaledTTL(), spread, this.damageDropStart,
				this.damageDropEnd, this.damageMin * damagebonus, this.penetration, getDoBlockDamage(player), firePos, radius, gravity);

		float f=1.0f;
		Techguns.proxy.createLightPulse(proj.posX+player.getLookVec().x*f, proj.posY+player.getLookVec().y*f, proj.posZ+player.getLookVec().z*f, 2, 3.0f, 3.0f, 1f, 0.9f, 0.2f);
		
		if (silenced) {
			proj.setSilenced();
		}
		if (offset > 0.0f) {
			proj.shiftForward(offset); // System.out.println("Shifted
										// by"+offset);
		}
		world.spawnEntity(proj);
	}

	public static boolean getDoBlockDamage(EntityLivingBase elb) {
		boolean blockdamage = false;
		if (elb instanceof EntityPlayer){
			TGExtendedPlayer caps = TGExtendedPlayer.get((EntityPlayer) elb);
			if(caps!=null){ blockdamage=!caps.enableSafemode; }
		}
		
		return blockdamage;
	}

	@Override
	public boolean isShootWithLeftClick() {
		return this.shootWithLeftClick;
	}

	@Override
	public boolean isSemiAuto() {
		return this.semiAuto;
	}
	
	public GenericGun setCheckRecoil(){
		this.checkRecoil=true;
		return this;
	}
	
	public GenericGun setCheckMuzzleFlash(){
		this.checkMuzzleFlash=true;
		return this;
	}

	@Override
	public boolean isZooming() {
		return ClientProxy.get().player_zoom==this.zoomMult;
	}

	@Override
	public void shootGunPrimary(ItemStack stack, World world, EntityPlayer player, boolean zooming, EnumHand hand) {
	
    		int ammo = this.getCurrentAmmo(stack);
    	
    		byte ATTACK_TYPE = 0;
    		TGExtendedPlayer extendedPlayer = TGExtendedPlayer.get(player);
    		if (ammo>0) {
    			//bullets left
		    	int firedelay = extendedPlayer.getFireDelay(hand);

		    	if (firedelay <=0) {
		  
		    		extendedPlayer.setFireDelay(hand,this.minFiretime);
		    
		    		if (!player.capabilities.isCreativeMode) {
	    				this.useAmmo(stack, 1);
		   
		    		}
		    		
			        if (!world.isRemote)
			        {
			        	/*
			        	 * If SERVER, create projectile
			        	 */
			        	
			        	float accuracybonus = 1.0f-GenericArmor.getArmorBonusForPlayer(player, TGArmorBonus.GUN_ACCURACY,false);
			        	
			        	EnumBulletFirePos firePos = hand==EnumHand.MAIN_HAND?EnumBulletFirePos.RIGHT:EnumBulletFirePos.LEFT;
			        	
			        	if(zooming){
			        		accuracybonus*=this.zoombonus;
			        		if (fireCenteredZoomed) {
			        			firePos=EnumBulletFirePos.CENTER;
			        		}
			        	}
			        	this.shootGun(world, player,stack, accuracybonus,1.0f,ATTACK_TYPE, hand,firePos);
			        	        	
			        } else {
			        	/*
			        	 * If CLIENT, do Effects
			        	 */	
			          	int recoiltime_l = (int) ((((float)recoiltime/20.0f)*1000.0f));
			        	int muzzleFlashtime_l = (int) ((((float)muzzleFlashtime/20.0f)*1000.0f));

			        	
			        	if (!checkRecoil || !ShooterValues.isStillRecoiling(player,hand==EnumHand.OFF_HAND, ATTACK_TYPE) ){
			        		//System.out.println("SettingRecoilTime");
				        	ShooterValues.setRecoiltime(player, hand==EnumHand.OFF_HAND,System.currentTimeMillis() + recoiltime_l, recoiltime_l,ATTACK_TYPE);
				        	
			        	}
			        	
			        	ClientProxy cp = ClientProxy.get();
			        	if (!checkMuzzleFlash || ShooterValues.getMuzzleFlashTime(player, hand==EnumHand.OFF_HAND) <= System.currentTimeMillis()) {
			        		ShooterValues.setMuzzleFlashTime(player, hand==EnumHand.OFF_HAND, System.currentTimeMillis() + muzzleFlashtime_l, muzzleFlashtime_l);
			        		Random rand = world.rand;
			        		cp.muzzleFlashJitterX = 1.0f-(rand.nextFloat()*2.0f);
			        		cp.muzzleFlashJitterY = 1.0f-(rand.nextFloat()*2.0f);
			        		cp.muzzleFlashJitterScale = 1.0f-(rand.nextFloat()*2.0f);
			        		cp.muzzleFlashJitterAngle = 1.0f-(rand.nextFloat()*2.0f);
			        	}
			        	
				        client_weaponFired();
			        }
			        /*
			         * Do sounds on Client & Server
			         */
		        	if (maxLoopDelay > 0 && extendedPlayer.getLoopSoundDelay(hand)<=0) {

		        		SoundUtil.playSoundOnEntityGunPosition(world, player, this.firesoundStart, SOUND_DISTANCE, 1.0F, false, false, TGSoundCategory.GUN_FIRE);

		        		extendedPlayer.setLoopSoundDelay(hand,this.maxLoopDelay);
		        		
		        	}else {
	
		        		SoundUtil.playSoundOnEntityGunPosition(world, player, this.firesound, SOUND_DISTANCE, 1.0F, false, false, TGSoundCategory.GUN_FIRE);
		        		if (this.maxLoopDelay>0){
		        			extendedPlayer.setLoopSoundDelay(hand,this.maxLoopDelay);
		        		}
		        	}
		        	
		        	if (!(rechamberSound==null)) {

		        		SoundUtil.playSoundOnEntityGunPosition(world, player, rechamberSound, 1.0F, 1.0F, false, false, TGSoundCategory.RELOAD);
		        	}
			        
		    	} else {
		    		//System.out.println(Thread.currentThread().toString()+": Skip shot, can't fire yet");
		    	}
		    	
    		} else {
    			//mag empty, reload needed
    			
    			//look for ammo
    			if (InventoryUtil.consumeAmmoPlayer(player,this.ammoType.getAmmo(this.getCurrentAmmoVariant(stack)))) {
    			
    				if (!this.ammoType.getEmptyMag().isEmpty()){
    					//player.inventory.addItemStackToInventory(new ItemStack(emptyMag.getItem(),1,emptyMag.getItemDamage()));
    					int amount=InventoryUtil.addAmmoToPlayerInventory(player, TGItems.newStack(this.ammoType.getEmptyMag(), 1));
    					if(amount >0 && !world.isRemote){
    						player.world.spawnEntity(new EntityItem(player.world, player.posX, player.posY, player.posZ, TGItems.newStack(this.ammoType.getEmptyMag(), amount)));
    					}
    				}
    				
    				//stop toggle zooming when reloading
    				if (world.isRemote) {
    					if (canZoom  && this.toggleZoom) {
    						ClientProxy cp = ClientProxy.get();
    		    			if (cp.player_zoom != 1.0f) {
    		    				cp.player_zoom= 1.0f;
    		    			}
    		    		}
    				}
    				
	    			//START RELOAD

    				extendedPlayer.setFireDelay(hand, this.reloadtime-this.minFiretime);
    						    			
	    			if (ammoCount >1) {
	    				int i =1;
	    				while (i<ammoCount && InventoryUtil.consumeAmmoPlayer(player,this.ammoType.getAmmo(this.getCurrentAmmoVariant(stack)))){
	    					i++;
	    				}

	    				this.reloadAmmo(stack, i);
	    			} else {
	    				this.reloadAmmo(stack);
	    			}
	    			
	    			SoundUtil.playReloadSoundOnEntity(world,player,reloadsound, 1.0F, 1.0F, false, true, TGSoundCategory.RELOAD);

	    			
    				if (world.isRemote) {
    					
    					int time = (int) (((float)reloadtime/20.0f)*1000);
    					ShooterValues.setReloadtime(player,hand==EnumHand.OFF_HAND,System.currentTimeMillis()+time, time, ATTACK_TYPE);
    					
    					client_startReload();
    				} else{
    					//send reloadpacket
    					//send pakets to clients
    					
				    	int msg_reloadtime = ((int)(((float)reloadtime/20.0f)*1000.0f));
				    	TGPackets.network.sendToAllAround(new ReloadStartedMessage(player,hand,msg_reloadtime,ATTACK_TYPE), TGPackets.targetPointAroundEnt(player, 100.0));
    			    	//
    				}

    			} else {
    			
    				
    				if (!world.isRemote)
			        {
    					//TODO emptySound
	    				//world.playSoundAtEntity(player, "mob.villager.idle", 1.0F, 1.0F );
			        }
    			}
    		}
	}
	

	/**
	 * for extra actions in subclass
	 */
	protected void client_weaponFired() {	
	}

	/**
	 * for extra actions in subclass
	 */
	protected void client_startReload() {	
	}
	
	public GenericGun setAIStats(float attackRange, int attackTime, int burstCount, int burstAttackTime) {
		this.AI_attackRange = attackRange;
		this.AI_attackTime = attackTime;
		this.AI_burstCount = burstCount;
		this.AI_burstAttackTime = burstAttackTime;
		return this;
	}
	
	public GenericGun setTexture(String path){
		return setTextures(path,1);
	}
	
	public GenericGun setTextures(String path, int variations){
		Techguns.proxy.setGunTextures(this, path, variations);
		this.camoCount=variations;
		return this;
	}
	
	@Override
	public ResourceLocation getCurrentTexture(ItemStack stack) {
		int camo = this.getCurrentCamoIndex(stack);
		if (camo < this.textures.size()) {
			return textures.get(camo);
		}
		return this.textures.get(0);
	}

	protected void shootGun(World world, EntityLivingBase player,ItemStack itemstack,float accuracybonus,float damagebonus, int attackType, EnumHand hand, EnumBulletFirePos firePos){
		
		boolean leftGun = hand == EnumHand.OFF_HAND;
		
		//send pakets to clients
		if (!world.isRemote){
	    	int msg_recoiltime = ((int)(((float)recoiltime/20.0f)*1000.0f));
	    	int msg_muzzleflashtime = ((int)(((float)muzzleFlashtime/20.0f)*1000.0f));
	    	TGPackets.network.sendToAllAround(new GunFiredMessage(player,msg_recoiltime,msg_muzzleflashtime,attackType,checkRecoil,leftGun), TGPackets.targetPointAroundEnt(player, 100.0f));
		}
    	//
		spawnProjectile(world, player,itemstack, accuracy*accuracybonus,0,damagebonus, firePos);
		
        if (shotgun){
        	float offset=0;
        	if (this.burst){
        		offset = 1.0f/(this.bulletcount);
        	}
        	
        	for (int i=0; i<bulletcount; i++) {
        		spawnProjectile(world, player,itemstack, spread*accuracybonus,offset*(i+1.0f),damagebonus, firePos);
        	}
        }		
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		//super.onCreated(stack, world, player);
		NBTTagCompound tags = stack.getTagCompound();
		if(tags==null){
			tags=new NBTTagCompound();
			stack.setTagCompound(tags);
		
			int dmg = stack.getItemDamage();
			
			tags.setByte("camo", (byte) 0);
			tags.setByte("ammovariant", (byte) 0);
			tags.setShort("ammo", dmg==0 ? (short)this.clipsize : (short)(this.clipsize-dmg));
			stack.setItemDamage(0);
		}
	}
	
	public int getCurrentAmmo(ItemStack stack){
		NBTTagCompound tags = stack.getTagCompound();
		if(tags==null){
			this.onCreated(stack, null, null); //world and player are not needed
			tags = stack.getTagCompound();
		}
		return tags.getShort("ammo");
	}
	
	public int getCurrentAmmoVariant(ItemStack stack){
		NBTTagCompound tags = stack.getTagCompound();
		if(tags==null){
			this.onCreated(stack, null, null); //world and player are not needed
			tags = stack.getTagCompound();
		}
		return tags.getByte("ammovariant");
	}
	
	/**
	 * Reduces ammo by amount, use positive values! amount=3 means ammo-=3;
	 * @param stack
	 * @param amount
	 * @return actually consumed ammo
	 */
	public int useAmmo(ItemStack stack, int amount){
		int ammo = this.getCurrentAmmo(stack);
		NBTTagCompound tags = stack.getTagCompound();
		if(ammo-amount>=0){
			tags.setShort("ammo", (short) (ammo-amount));
			return amount;
		} else {
			tags.setShort("ammo", (short) 0);
			return ammo;
		}
	}
	
	public void reloadAmmo(ItemStack stack){
		this.reloadAmmo(stack, this.clipsize);
	}
	
	public void reloadAmmo(ItemStack stack, int amount){
		int ammo = this.getCurrentAmmo(stack);
		NBTTagCompound tags = stack.getTagCompound();
		tags.setShort("ammo", (short) (ammo+amount));
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)){
		
			ItemStack gun = new ItemStack(this, 1,0);
			NBTTagCompound tags = new NBTTagCompound();
			tags.setByte("camo", (byte) 0);
			tags.setShort("ammo", (short)this.clipsize);
			gun.setTagCompound(tags);
			
			items.add(gun);
		}
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1.0-((double)this.getCurrentAmmo(stack))/((double)this.clipsize);
		
	}

	@Override
	public int getAmmoLeft(ItemStack stack) {
		return this.getCurrentAmmo(stack);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		
		return slotChanged || !oldStack.isItemEqual(newStack); 
		/*if (!b && oldStack!=newStack) {
			Techguns.proxy.fixReequipAnim(oldStack, newStack);
		}*/
		//return b;
	}
	
	public GenericGun setDamageDrop(int start, int end, float minDamage){
		this.damageDropStart=start;
		this.damageDropEnd=end;
		this.damageMin=minDamage;
		return this;
	}
	
	public GenericGun setSilenced(boolean s){
		this.silenced=s;
		return this;
	}

	@Override
	public GunHandType getGunHandType() {
		return this.handType;
	}
	
	public GenericGun setHandType(GunHandType type) {
		this.handType=type;
		return this;
	}

	@Override
	public boolean isHoldZoom() {
		return !this.toggleZoom;
	}

	@Override
	public float getZoomMult() {
		return this.zoomMult;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, list, flagIn);
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
			
			list.add(TextUtil.trans("techguns.gun.tooltip.ammo")+": "+(this.ammoCount>1 ? this.ammoCount+"x " : "")+ChatFormatting.WHITE+TextUtil.trans(this.ammoType.getAmmo(this.getCurrentAmmoVariant(stack)).getUnlocalizedName()+".name"));
			list.add(TextUtil.trans("techguns.gun.tooltip.damageType")+": "+this.getDamageType().toString());
			list.add(TextUtil.trans("techguns.gun.tooltip.damage")+(this.shotgun ? ("(x"+ (this.bulletcount+1)+")") : "" )+": "+this.damage+(this.damageMin<this.damage?"-"+this.damageMin:""));
			list.add(TextUtil.trans("techguns.gun.tooltip.range")+": "+this.damageDropStart+","+this.damageDropEnd+","+this.ticksToLive);
			list.add(TextUtil.trans("techguns.gun.tooltip.spread")+": "+this.accuracy);
			list.add(TextUtil.trans("techguns.gun.tooltip.clipsize")+": "+this.clipsize);
			list.add(TextUtil.trans("techguns.gun.tooltip.reloadTime")+": "+this.reloadtime*0.05f+"s");
			if (this.penetration>0.0f){
				list.add(TextUtil.trans("techguns.gun.tooltip.armorPen")+": "+this.penetration*100+"%");
			}
			if (this.canZoom) {
				list.add(TextUtil.trans("techguns.gun.tooltip.zoom")+":"+(this.toggleZoom ? "("+TextUtil.trans("techguns.gun.tooltip.zoom.toogle")+")":"("+TextUtil.trans("techguns.gun.tooltip.zoom.hold")+")")+" "+TextUtil.trans("techguns.gun.tooltip.zoom.multiplier")+":"+this.zoomMult);
			}
			//TODO Mininglevels
			/*if (this.mininglevels!=null){
				ItemUtil.addToolClassesTooltip(mininglevels, list);
			}*/
		} else {
			list.add(TextUtil.trans("techguns.gun.tooltip.ammo")+": "+(this.ammoCount>1 ? this.ammoCount+"x " : "")+ChatFormatting.WHITE+TextUtil.trans(this.ammoType.getAmmo(this.getCurrentAmmoVariant(stack)).getUnlocalizedName()+".name"));
			list.add(TextUtil.trans("techguns.gun.tooltip.damage")+(this.shotgun ? ("(x"+ (this.bulletcount+1)+")") : "" )+": "+this.damage+"-"+this.damageMin);
			list.add(TextUtil.trans("techguns.gun.tooltip.shift1")+" "+ChatFormatting.GREEN+TextUtil.trans("techguns.gun.tooltip.shift2")+" "+ChatFormatting.GRAY+TextUtil.trans("techguns.gun.tooltip.shift3"));
		}
		//} else {
		//	list.add("Sneak to view stats");
		//}
	}

	public DamageType getDamageType() {
		return this.projectile.getDamageType();
	}

	@Override
	public int getCamoCount() {
		return this.camoCount;
	}

	@Override
	public String getCurrentCamoName(ItemStack item) {
		NBTTagCompound tags = item.getTagCompound();
		byte camoID=0;
		if (tags!=null && tags.hasKey("camo")){
			camoID=tags.getByte("camo");
		}
		if(camoID>0){
			return TextUtil.trans(this.getUnlocalizedName()+".camoname."+camoID);
		} else {
			return TextUtil.trans("techguns.item.defaultcamo");
		}
	}

	/**
	 * Should this weapon shoot with left click instead of mine, defaults to true
	 * @param shootWithLeftClick
	 */
	public GenericGun setShootWithLeftClick(boolean shootWithLeftClick) {
		this.shootWithLeftClick = shootWithLeftClick;
		return this;
	}
	
	public GenericGun setMiningAmmoConsumption(int ammo) {
		this.miningAmmoConsumption = ammo;
		return this;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity targetEntity) {
		
		if (this.shootWithLeftClick) {
			return true;
		} else {
			if (player.world.isRemote) {

				int time = (int) (((float) this.recoiltime / 20.0f) * 1000);
				//ClientProxy.get().setplayerRecoiltime(player, System.currentTimeMillis() + time, time, (byte) 0);
				ShooterValues.setRecoiltime(player, false, System.currentTimeMillis() + time, time, (byte) 0);
			}

			/**
			 * COPY FROM ENTITYPLAYER
			 */
			if (targetEntity.canBeAttackedWithItem()) {
				if (!targetEntity.hitByEntity(player)) {
					float f = (float) player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
					float f1;

					if (targetEntity instanceof EntityLivingBase) {
						f1 = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), ((EntityLivingBase) targetEntity).getCreatureAttribute());
					} else {
						f1 = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), EnumCreatureAttribute.UNDEFINED);
					}

					float f2 = player.getCooledAttackStrength(0.5F);
					f = f * (0.2F + f2 * f2 * 0.8F);
					f1 = f1 * f2;
					player.resetCooldown();

					if (f > 0.0F || f1 > 0.0F) {
						boolean flag = f2 > 0.9F;
						boolean flag1 = false;
						int i = 0;
						i = i + EnchantmentHelper.getKnockbackModifier(player);

						if (player.isSprinting() && flag) {
							player.world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK,
									player.getSoundCategory(), 1.0F, 1.0F);
							++i;
							flag1 = true;
						}

						boolean flag2 = flag && player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater()
								&& !player.isPotionActive(MobEffects.BLINDNESS) && !player.isRiding() && targetEntity instanceof EntityLivingBase;
						flag2 = flag2 && !player.isSprinting();

						if (flag2) {
							f *= 1.5F;
						}

						f = f + f1;
						boolean flag3 = false;
						double d0 = (double) (player.distanceWalkedModified - player.prevDistanceWalkedModified);

						if (flag && !flag2 && !flag1 && player.onGround && d0 < (double) player.getAIMoveSpeed()) {
							ItemStack itemstack = player.getHeldItem(EnumHand.MAIN_HAND);

							flag3= this.hasSwordSweep(); //if (itemstack.getItem() instanceof ItemSword) {
							//	flag3 = true;
							//}
						}

						float f4 = 0.0F;
						boolean flag4 = false;
						int j = EnchantmentHelper.getFireAspectModifier(player);

						if (targetEntity instanceof EntityLivingBase) {
							f4 = ((EntityLivingBase) targetEntity).getHealth();

							if (j > 0 && !targetEntity.isBurning()) {
								flag4 = true;
								targetEntity.setFire(1);
							}
						}

						double d1 = targetEntity.motionX;
						double d2 = targetEntity.motionY;
						double d3 = targetEntity.motionZ;
						
						//EDIT: return parameter workaround for attackEntityFrom
						TGDamageSource src = getMeleeDamageSource(player,stack);
						targetEntity.attackEntityFrom(src, f);
						boolean flag5 = src.wasSuccessful();

						if (flag5) {
							if (i > 0) {
								if (targetEntity instanceof EntityLivingBase) {
									((EntityLivingBase) targetEntity).knockBack(player, (float) i * 0.5F, (double) MathHelper.sin(player.rotationYaw * 0.017453292F),
											(double) (-MathHelper.cos(player.rotationYaw * 0.017453292F)));
								} else {
									targetEntity.addVelocity((double) (-MathHelper.sin(player.rotationYaw * 0.017453292F) * (float) i * 0.5F), 0.1D,
											(double) (MathHelper.cos(player.rotationYaw * 0.017453292F) * (float) i * 0.5F));
								}

								player.motionX *= 0.6D;
								player.motionZ *= 0.6D;
								player.setSprinting(false);
							}

							if (flag3) {
								float f3 = 1.0F + EnchantmentHelper.getSweepingDamageRatio(player) * f;

								for (EntityLivingBase entitylivingbase : player.world.getEntitiesWithinAABB(EntityLivingBase.class,
										targetEntity.getEntityBoundingBox().grow(1.0D, 0.25D, 1.0D))) {
									if (entitylivingbase != player && entitylivingbase != targetEntity && !player.isOnSameTeam(entitylivingbase)
											&& player.getDistanceSqToEntity(entitylivingbase) < 9.0D) {
										entitylivingbase.knockBack(player, 0.4F, (double) MathHelper.sin(player.rotationYaw * 0.017453292F),
												(double) (-MathHelper.cos(player.rotationYaw * 0.017453292F)));
										entitylivingbase.attackEntityFrom(getMeleeDamageSource(player,stack), f3);
									}
								}

								player.world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
										player.getSoundCategory(), 1.0F, 1.0F);
								player.spawnSweepParticles();
							}

							if (targetEntity instanceof EntityPlayerMP && targetEntity.velocityChanged) {
								((EntityPlayerMP) targetEntity).connection.sendPacket(new SPacketEntityVelocity(targetEntity));
								targetEntity.velocityChanged = false;
								targetEntity.motionX = d1;
								targetEntity.motionY = d2;
								targetEntity.motionZ = d3;
							}

							if (flag2) {
								player.world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, player.getSoundCategory(),
										1.0F, 1.0F);
								player.onCriticalHit(targetEntity);
							}

							if (!flag2 && !flag3) {
								if (flag) {
									player.world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG,
											player.getSoundCategory(), 1.0F, 1.0F);
								} else {
									player.world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_WEAK,
											player.getSoundCategory(), 1.0F, 1.0F);
								}
							}

							if (f1 > 0.0F) {
								player.onEnchantmentCritical(targetEntity);
							}

							player.setLastAttackedEntity(targetEntity);

							if (targetEntity instanceof EntityLivingBase) {
								EnchantmentHelper.applyThornEnchantments((EntityLivingBase) targetEntity, player);
							}

							EnchantmentHelper.applyArthropodEnchantments(player, targetEntity);
							ItemStack itemstack1 = player.getHeldItemMainhand();
							Entity entity = targetEntity;

							if (targetEntity instanceof MultiPartEntityPart) {
								IEntityMultiPart ientitymultipart = ((MultiPartEntityPart) targetEntity).parent;

								if (ientitymultipart instanceof EntityLivingBase) {
									entity = (EntityLivingBase) ientitymultipart;
								}
							}

							if (!itemstack1.isEmpty() && entity instanceof EntityLivingBase) {
								ItemStack beforeHitCopy = itemstack1.copy();
								itemstack1.hitEntity((EntityLivingBase) entity, player);

								if (itemstack1.isEmpty()) {
									net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, beforeHitCopy, EnumHand.MAIN_HAND);
									player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
								}
							}

							if (targetEntity instanceof EntityLivingBase) {
								float f5 = f4 - ((EntityLivingBase) targetEntity).getHealth();
								player.addStat(StatList.DAMAGE_DEALT, Math.round(f5 * 10.0F));

								if (j > 0) {
									targetEntity.setFire(j * 4);
								}

								if (player.world instanceof WorldServer && f5 > 2.0F) {
									int k = (int) ((double) f5 * 0.5D);
									((WorldServer) player.world).spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR, targetEntity.posX,
											targetEntity.posY + (double) (targetEntity.height * 0.5F), targetEntity.posZ, k, 0.1D, 0.0D, 0.1D, 0.2D);
								}
							}

							player.addExhaustion(0.1F);
						} else {
							player.world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, player.getSoundCategory(),
									1.0F, 1.0F);

							if (flag4) {
								targetEntity.extinguish();
							}
						}
					}
				}
			}

			return true;
		}
	}
	
	/**
	 * Override in Subclass to define damagesource used for player melee attacks, only relevant if "shootWithLeftClick" is not set
	 * @return
	 */
	protected TGDamageSource getMeleeDamageSource(EntityPlayer player, ItemStack stack){
		TGDamageSource src = new TGDamageSource("player", player, player, DamageType.PHYSICAL, DeathType.GORE);
		return src;
	}
	
	protected boolean hasSwordSweep() {
		return true;
	}
	
	public int getMiningAmmoConsumption() {
		return miningAmmoConsumption;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
	
		Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();
		
		if (slot == EntityEquipmentSlot.MAINHAND)
        {
			double meleedmg=this.meleeDamagePwr;
			int ammoleft = this.getCurrentAmmo(stack);
			if (ammoleft <=0){
				meleedmg=this.meleeDamageEmpty;
			}
			
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", meleedmg, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
        }

		return multimap;
	}

	@Override
	public boolean isModelBase(ItemStack stack) {
		return this.hasCustomTexture;
	}
	
	public EntityAIRangedAttack getAIAttack(IRangedAttackMob shooter) {
		return new EntityAIRangedAttack(shooter, 1.0D, this.AI_attackTime/3, this.AI_attackTime, this.AI_attackRange, this.AI_burstCount, this.AI_burstAttackTime);
	}
	
	 public AmmoType getAmmoType() {
		return ammoType;
	}

	public float getAI_attackRange() {
		return AI_attackRange;
	}
	 
	/**
     * Weapon is used by NPC
     */
    public void fireWeaponFromNPC(EntityLivingBase shooter, float dmgscale, float accscale) {
    	
    	SoundUtil.playSoundOnEntityGunPosition(shooter.world, shooter ,firesound, SOUND_DISTANCE, 1.0F, false, false, TGSoundCategory.GUN_FIRE);
    	
    	
    	float dmg=0.5f;
    	float acc=1.0f;
    	
    	EnumBulletFirePos firePos = EnumBulletFirePos.RIGHT;
    	
    	if (shooter instanceof NPCTurret){
    		dmg=1.0f;
    		acc=1.0f;
    		firePos=EnumBulletFirePos.CENTER;
    	}
    	/*} else {
	    	
	    	/*EnumDifficulty difficulty = shooter.world.getDifficulty();
	    	
	    	switch(difficulty){
	    		case EASY:
	    			dmg=0.5f;
	    			acc=1.3f;
	    			break;
	    		case NORMAL:
	    			dmg = 0.6f;
	    			acc = 1.15f;
	    			break;
	    		case HARD:
	    			dmg = 0.75f;
	    			acc = 1.0f;
	    			break;
	    		case PEACEFUL:
	    			dmg = 0.0f;
	    			acc = 1.3f;
	    			break;
	    	}*/
    	//}
    	
    	if (!shooter.world.isRemote){
    		this.shootGun(shooter.world, shooter, null, this.zoombonus*accscale*acc,dmgscale*dmg,0, EnumHand.MAIN_HAND, firePos);
    	}

    }
}
