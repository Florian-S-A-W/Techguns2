package techguns.entities.npcs;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import techguns.TGConfig;
import techguns.TGItems;
import techguns.TGuns;
import techguns.Techguns;
import techguns.damagesystem.TGDamageSource;
import techguns.debug.Keybinds;

public class SuperMutantElite extends SuperMutantBasic {

	public SuperMutantElite(World world) {
		super(world);
	}
	
	public int gettype() {
		return 2;
	};

	public double getModelHeightOffset(){
		return 0.95d;
	}
	
	public float getModelScale() {
		return 1.65f;
	}
	
	@Override
	public float getWeaponPosY() {
		return 2.4f;//+(float)Keybinds.Y;
	}
	
	@Override
	public float getWeaponPosX() {
		return -0.25f;//+(float)Keybinds.X;
	}

	
	@Override
	public float getTotalArmorAgainstType(TGDamageSource dmgsrc) {
		switch(dmgsrc.damageType){
		case EXPLOSION:
		case LIGHTNING:
		case ENERGY:
		case FIRE:
		case ICE:
			return 14.0f;
		case PHYSICAL:
		case PROJECTILE:
			return 10.0f;
		case POISON:
		case RADIATION:
			return 16.0f;
		case UNRESISTABLE:
	default:
		return 0.0f;
		}
	}

	@Override
	public int getTotalArmorValue() {
		return 10;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(11);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
	}
}
