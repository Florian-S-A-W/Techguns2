package techguns.tools;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.oredict.ShapedOreRecipe;
import techguns.TGItems;

import static techguns.TGItems.*;

public class Recipewriter {

	public static void writeRecipes() {
		
		registerItemRecipes();
		
		
		RecipeJsonConverter.generateConstants();
		
		
	}
	
	public static void registerItemRecipes(){
		RecipeJsonConverter.addShapedRecipe(newStack(BARREL_STONE,1), "sss","   ","sss", 's', "stone");
        RecipeJsonConverter.addShapedRecipe(newStack(STOCK_WOOD,1), "ww", " w", 'w', "logWood");
        
        RecipeJsonConverter.addShapedRecipe(newStack(BARREL_IRON,1), "iii","   ","iii", 'i', "ingotIron");
   
        
        RecipeJsonConverter.addShapedRecipe(newStack(MECHANICAL_PARTS_IRON,1)," i ", "ifi", " i ", 'f', Items.FLINT, 'i', "ingotIron");
        
        //RecipeJsonConverter.addShapedRecipe(newStack(plasticGunstock,1),"  i","nni","  n", 'i', "ingotIron", 'n', "nuggetIron"));
        RecipeJsonConverter.addShapedRecipe(newStack(STOCK_PLASTIC,1),"ppp","  p", 'p', "sheetPlastic");
        
        // RecipeJsonConverter.addShapedRecipe(newStack(mechanicalPartsT2,1), "sqg","oqo", 'q', "gemQuartz", 's', spring, 'g', "gearIron", 'o', Blocks.obsidian));
        
       //RecipeJsonConverter.addShapedRecipe(newStack(mechanicalPartsT3,1), "sbg","dbd",'b', Items.blaze_rod, 'd', "gemDiamond", 's', spring, 'g', "gearIron"));
        
        RecipeJsonConverter.addShapedRecipe(newStack(BARREL_OBSIDIAN_STEEL, 1), "ooo","   ","ooo", 'o', INGOT_OBSIDIAN_STEEL);
        
        RecipeJsonConverter.addShapedRecipe(newStack(PUMP_MECHANISM, 1), "nnn", "gpg", "nnn", 'n', "nuggetIron", 'g', "blockGlass", 'p', Blocks.PISTON);
        
        RecipeJsonConverter.addShapedRecipe(newStack(BARREL_LASER,1), "fff","ggl","fff", 'f', "electrumOrGold", 'g', "hardenedGlassOrGlass", 'l', LASER_FOCUS);
        
        RecipeJsonConverter.addShapedRecipe(newStack(HEAVY_CLOTH,3), " w ","wlw"," w ", 'w', Blocks.WOOL, 'l', Items.LEATHER);
        
        
        RecipeJsonConverter.addShapelessRecipe(newStack(STONE_BULLETS,16), "cobblestone", "cobblestone", "cobblestone", Items.GUNPOWDER);
        
        RecipeJsonConverter.addShapedRecipe(newStack(PISTOL_ROUNDS,8), "clc","cgc","ccc", 'c', "nuggetCopper", 'l', "ingotLead", 'g', Items.GUNPOWDER);
        
        RecipeJsonConverter.addShapedRecipe(newStack(SHOTGUN_ROUNDS,5), "lll","cgc","ccc", 'c', "nuggetCopper", 'l', "nuggetLead", 'g', Items.GUNPOWDER);
        
        
        RecipeJsonConverter.addShapedRecipe(newStack(PISTOL_MAGAZINE_EMPTY,4), "i i","imi","igi", 'i', "nuggetIron",'g', "ingotIron", 'm', MECHANICAL_PARTS_IRON);
        RecipeJsonConverter.addShapelessRecipe(newStack(PISTOL_MAGAZINE, 1), PISTOL_MAGAZINE_EMPTY, PISTOL_ROUNDS,PISTOL_ROUNDS,PISTOL_ROUNDS);
    
        RecipeJsonConverter.addShapedRecipe(newStack(SMG_MAGAZINE_EMPTY,4), "i i","i i","imi", 'i', "nuggetIron", 'm', MECHANICAL_PARTS_IRON);
        RecipeJsonConverter.addShapelessRecipe(newStack(SMG_MAGAZINE, 1), SMG_MAGAZINE_EMPTY, PISTOL_ROUNDS, PISTOL_ROUNDS);
       
        RecipeJsonConverter.addShapedRecipe(newStack(ASSAULTRIFLE_MAGAZINE_EMPTY,4),"s s","s s","sms", 's', "nuggetSteel",'m', MECHANICAL_PARTS_IRON);
        
        RecipeJsonConverter.addShapelessRecipe(newStack(ADVANCED_MAGAZINE, 1), ADVANCED_MAGAZINE_EMPTY, RIFLE_ROUNDS, RIFLE_ROUNDS, RIFLE_ROUNDS);
        
       
        RecipeJsonConverter.addShapedRecipe(newStack(BIO_TANK_EMPTY,1),"sss","sgs","sgs", 's', "nuggetSteel", 'g', "blockGlass");
 
        RecipeJsonConverter.addShapedRecipe(newStack(BIOMASS,4)," g ","gbg"," g ", 'g', "dyeGreen", 'b', "slimeball");
        
        RecipeJsonConverter.addShapedRecipe(newStack(ROCKET,4), " s ","sts","sgs", 's', "nuggetIron", 't', Blocks.TNT, 'g', Items.GUNPOWDER);
        
        RecipeJsonConverter.addShapedRecipe(newStack(LMG_MAGAZINE_EMPTY,4), " ii", "imi","iii", 'i', "ingotSteel", 'm', MECHANICAL_PARTS_IRON);
        RecipeJsonConverter.addShapedRecipe(newStack(LMG_MAGAZINE,1), "bbb", "bmb","bbb", 'b', RIFLE_ROUNDS, 'm', LMG_MAGAZINE_EMPTY);
        RecipeJsonConverter.addShapelessRecipe(newStack(LMG_MAGAZINE,1), LMG_MAGAZINE_EMPTY, RIFLE_ROUNDS_STACK, RIFLE_ROUNDS_STACK);
        
        RecipeJsonConverter.addShapedRecipe(newStack(AS50_MAGAZINE_EMPTY,4),"s s","s s","sms", 's', "ingotSteel", 'm', MECHANICAL_PARTS_IRON);
     
        RecipeJsonConverter.addShapelessRecipe(newStack(AS50_MAGAZINE,1),AS50_MAGAZINE_EMPTY, SNIPER_ROUNDS, SNIPER_ROUNDS);
        
        RecipeJsonConverter.addShapedRecipe(newStack(RECEIVER_IRON,1), "iii"," mn","  n", 'i', "ingotIron", 'n', "nuggetIron",'m', MECHANICAL_PARTS_IRON);
        RecipeJsonConverter.addShapedRecipe(newStack(RECEIVER_STEEL,1), "iii"," mn","  n", 'i', "ingotSteel", 'n', "nuggetSteel",'m', MECHANICAL_PARTS_OBSIDIAN_STEEL);
        RecipeJsonConverter.addShapedRecipe(newStack(RECEIVER_OBSIDIAN_STEEL,1), "ppp"," mi","  i", 'i', "ingotObsidianSteel", 'p', "plateObsidianSteel",'m', MECHANICAL_PARTS_CARBON);
        RecipeJsonConverter.addShapedRecipe(newStack(RECEIVER_CARBON,1), "ppp"," mi","  i", 'i', "ingotObsidianSteel", 'p', "plateCarbon",'m', MECHANICAL_PARTS_CARBON);
        

        
        RecipeJsonConverter.addShapedRecipe(newStack(ADVANCED_MAGAZINE_EMPTY,4),"i i","i i","imi",'i', "ingotObsidianSteel", 'm',MECHANICAL_PARTS_OBSIDIAN_STEEL);
        RecipeJsonConverter.addShapelessRecipe(newStack(ADVANCED_MAGAZINE,1), ADVANCED_MAGAZINE_EMPTY, ADVANCED_ROUNDS, ADVANCED_ROUNDS, ADVANCED_ROUNDS);
        
        RecipeJsonConverter.addShapedRecipe(newStack(WIRE_COPPER,1)," cc"," c ","cc ",'c', "nuggetCopper");
        
        RecipeJsonConverter.addShapedRecipe(newStack(ELECTRIC_ENGINE,1), "wrw","imi","wrw", 'w', "wireCopper", 'i', "ingotIron", 'r', "dustRedstone", 'm', MECHANICAL_PARTS_IRON);
	
        RecipeJsonConverter.addShapedRecipe(newStack(TGItems.CIRCUIT_BOARD_BASIC,4),"cgc","rir","cgc", 'i', "plateIron", 'c', "wireCopper", 'g', "dyeGreen", 'r', "dustRedstone");
	
       
        RecipeJsonConverter.addShapedRecipe(newStack(BARREL_CARBON,1), "iii","   ","iii", 'i', "plateCarbon");
        RecipeJsonConverter.addShapedRecipe(newStack(STOCK_CARBON,1), "iii"," pp", 'i', "plateCarbon", 'p', "sheetPlastic");
        
      
        RecipeJsonConverter.addShapelessRecipe(newStack(CIRCUIT_BOARD_ELITE,1), "circuitBasic", "wireGold", "gemLapis");
        
        RecipeJsonConverter.addShapedRecipe(newStack(FUEL_TANK_EMPTY,4), "p","g","p", 'g', "blockGlass", 'p', "sheetPlastic");
        
       
        RecipeJsonConverter.addShapelessRecipe(newStack(RIFLE_ROUNDS_STACK,1), RIFLE_ROUNDS,RIFLE_ROUNDS,RIFLE_ROUNDS,RIFLE_ROUNDS);
        RecipeJsonConverter.addShapelessRecipe(newStack(RIFLE_ROUNDS,4), RIFLE_ROUNDS_STACK);
        
        RecipeJsonConverter.addShapedRecipe(newStack(MINIGUN_DRUM_EMPTY,4), "sss","pmp","sss",'s', "ingotSteel", 'p', "sheetPlastic", 'm', MECHANICAL_PARTS_OBSIDIAN_STEEL);
        RecipeJsonConverter.addShapelessRecipe(newStack(MINIGUN_DRUM,1),MINIGUN_DRUM_EMPTY, RIFLE_ROUNDS_STACK,RIFLE_ROUNDS_STACK,RIFLE_ROUNDS_STACK,RIFLE_ROUNDS_STACK);
	
        RecipeJsonConverter.addShapedRecipe(newStack(COMPRESSED_AIR_TANK_EMPTY,7),"plp","p p","ppp", 'p', "plateIron", 'l', Blocks.LEVER);
        RecipeJsonConverter.addShapedRecipe(newStack(COMPRESSED_AIR_TANK_EMPTY,7),"plp","p p","ppp", 'p', "plateTin", 'l', Blocks.LEVER);
        
    
        RecipeJsonConverter.addShapedRecipe(newStack(COIL,1), " wi","wiw","iw ", 'i', "ingotIron", 'w', "wireCopper");
	}
	
	public static void notyetimplemented() {
   /*     RecipeJsonConverter.addShapedRecipe(newStack(machineStackUpgrade,1), "pgp","ncn","pnp", 'p', "plateIron", 'n', "ingotGold",'g', "dyeGreen", 'c', new ItemStack(Blocks.chest,1)));
	
        RecipeJsonConverter.addShapedRecipe(newStack(turretArmorIron,1),"ppp"," p "," p ", 'p', "plateIron");
        RecipeJsonConverter.addShapedRecipe(newStack(turretArmorSteel,1),"ppp"," p "," p ", 'p', "plateSteel");
        RecipeJsonConverter.addShapedRecipe(newStack(turretArmorObsidianSteel,1),"ppp"," p "," p ", 'p', "plateObsidianSteel");
        RecipeJsonConverter.addShapedRecipe(newStack(turretArmorCarbon,1),"ppp"," p "," p ", 'p', "plateCarbon");
        
        RecipeJsonConverter.addShapedRecipe(newStack(gliderBackback,1), "hhh","shs","hhh", 'h', TGItems.heavyCloth, 's', "ingotIron"));
        RecipeJsonConverter.addShapedRecipe(newStack(gliderWing,1), "sss","hhh","hhh", 'h', TGItems.heavyCloth, 's', "ingotIron"));
        RecipeJsonConverter.addShapelessRecipe(newStack(glider,1), TGItems.gliderBackback, TGItems.gliderWing, TGItems.gliderWing));
        
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.basicMachine,1,0), "ili","cec","iri", 'i', "ingotIron", 'c', "ingotCopper", 'l', "ingotLead",'e', TGItems.electricEngine, 'r', Items.redstone));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.basicMachine,1,1), "ibi","iei","iri", 'i', "ingotIron", 'b', "blockIron",'e', TGItems.electricEngine, 'r', Items.redstone));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.basicMachine,1,1), "ibi","iei","iri", 'i', "ingotIron", 'b', "plateIron",'e', TGItems.electricEngine, 'r', Items.redstone));
        
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.basicMachine,1,2), "pip","cec","prp", 'p', "plateIron", 'c', new ItemStack(TGBlocks.concrete, 1), 'b', "plateIron",'e', TGItems.electricEngine, 'r', Items.redstone, 'i', "circuitBasic"));
        
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.basicMachine,1,3), "ddd","ici","iii", 'd', "dye", 'c', new ItemStack(Blocks.crafting_table,1), 'i', "nuggetIron"));
        
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.basicMachine,1,4), "igi","geg","iri", 'i', "ingotIron", 'g', new ItemStack(Items.glass_bottle), 'e', TGItems.electricEngine, 'r', Items.redstone));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.basicMachine,1,5), "pmp","ici","iii", 'm', MECHANICAL_PARTS_IRON, 'c', new ItemStack(Blocks.crafting_table,1), 'i', "nuggetIron", 'p', "plateIron"));
        
        
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.ladder01,16),"iii"," i ","iii", 'i', "plateTin"));
        
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.lamp01,16,0),"iii","grg","ggg",'i',"nuggetIron",'r', "dustRedstone",'g', "paneGlass");
        RecipeJsonConverter.addShapelessRecipe(new ItemStack(TGBlocks.lamp01,1,0), new ItemStack(TGBlocks.lamp01,1,8)));
        RecipeJsonConverter.addShapelessRecipe(new ItemStack(TGBlocks.lamp01,1,8), new ItemStack(TGBlocks.lamp01,1,0)));
        
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.lamp01,16,7),"iii","grg","iii",'i',"nuggetIron",'r', "dustRedstone",'g', "paneGlass"));
        RecipeJsonConverter.addShapelessRecipe(new ItemStack(TGBlocks.lamp01,1,15), new ItemStack(TGBlocks.lamp01,1,7)));
        RecipeJsonConverter.addShapelessRecipe(new ItemStack(TGBlocks.lamp01,1,7), new ItemStack(TGBlocks.lamp01,1,15)));
        
       // RecipeJsonConverter.addShapelessRecipe(newStack(TGItems.rawRubber,1), TGItems.rubberDrop,TGItems.rubberDrop,TGItems.rubberDrop,TGItems.rubberDrop,TGItems.rubberDrop,TGItems.rubberDrop,TGItems.rubberDrop,TGItems.rubberDrop,TGItems.rubberDrop));
        RecipeJsonConverter.addShapedRecipe(newStack(TGItems.gasMaskFilter,1), "iii","ici","iii", 'i', "nuggetIron", 'c', Items.coal));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGItems.gasMask,1), "grg","rrr","rfr", 'r', "itemRubber", 'f', TGItems.gasMaskFilter, 'g', "paneGlass"));
        RecipeJsonConverter.addShapelessRecipe(new ItemStack(TGItems.gasMask,1,0), new ItemStack(TGItems.gasMask,1,OreDictionary.WILDCARD_VALUE), TGItems.gasMaskFilter));
    	
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.oreDrill,8,0), "pbp","bpb","pbp", 'p', "plateIron", 'b', new ItemStack(Blocks.iron_bars,1)));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.oreDrill,8,1), "bbb","bpb","bbb", 'p', "plateIron", 'b', new ItemStack(Blocks.iron_bars,1)));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.oreDrill,4,2), "ipi","ipi","ipi", 'p', "plateIron", 'i', "ingotIron"));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.oreDrill,4,3), "bbb","beb","bbb", 'p', "plateIron", 'b', new ItemStack(Blocks.iron_bars,1),'e', TGItems.electricEngine));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.oreDrill,1,4), "pcp","cec","pcp", 'p', "plateIron", 'c', "circuitBasic",'e', TGItems.electricEngine));
	
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.container,8,0), "i i"," c ", "i i", 'i', "ingotIron", 'c', Blocks.chest));
        
        RecipeJsonConverter.addShapedRecipe(newStack(oreDrillSmallSteel,1),"sps","sps"," s ", 's', "ingotSteel", 'p', "plateSteel"));
        RecipeJsonConverter.addShapedRecipe(newStack(oreDrillSmallObsidianSteel,1),"sps","sps"," s ", 's', "ingotObsidianSteel", 'p', "plateObsidianSteel"));
        RecipeJsonConverter.addShapedRecipe(newStack(oreDrillSmallCarbon,1),"sss","sss"," s ",  's', "plateCarbon"));
        
        RecipeJsonConverter.addShapedRecipe(newStack(oreDrillMediumSteel,1),"d d"," d ", 'd', TGItems.oreDrillSmallSteel));
        RecipeJsonConverter.addShapedRecipe(newStack(oreDrillMediumObsidianSteel,1),"d d"," d ", 'd', TGItems.oreDrillSmallObsidianSteel));
        RecipeJsonConverter.addShapedRecipe(newStack(oreDrillMediumCarbon,1),"d d"," d ", 'd', TGItems.oreDrillSmallCarbon));
        
        RecipeJsonConverter.addShapedRecipe(newStack(oreDrillLargeSteel,1),"d d"," d ", 'd', TGItems.oreDrillMediumSteel));
        RecipeJsonConverter.addShapedRecipe(newStack(oreDrillLargeObsidianSteel,1),"d d"," d ", 'd', TGItems.oreDrillMediumObsidianSteel));
        RecipeJsonConverter.addShapedRecipe(newStack(oreDrillLargeCarbon,1),"d d"," d ", 'd', TGItems.oreDrillMediumCarbon));
        
        RecipeJsonConverter.addShapedRecipe(new ItemStack(nightVisionGoggles,1), "rhr","cec","gsg", 'r', "itemRubber", 'h', TGItems.heavyCloth, 'c',"circuitBasic",'e', TGItems.energyCell, 'g', "blockGlass", 's', "ingotIron"));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(nightVisionGoggles,1,nightVisionGoggles.getMaxDamage()), "rhr","cec","gsg", 'r', "itemRubber", 'h', TGItems.heavyCloth, 'c',"circuitBasic",'e', TGItems.energyCellEmpty, 'g', "blockGlass", 's', "ingotIron"));
    	
        RecipeJsonConverter.addShapelessRecipe(new ItemStack(TGBlocks.sandbags,8), "itemRubber", Blocks.SAND, Blocks.SAND, Blocks.SAND, Blocks.SAND,Blocks.SAND, Blocks.SAND, Blocks.SAND, Blocks.SAND));
        
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.camoNetPane,8), "sds", "sds", 's', Items.stick, 'd', Blocks.dirt));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.camoNetRoof,16), "wdw","dsd", "wdw", 's', Items.string, 'd', Blocks.dirt, 'w', Items.stick));
 
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGItems.door01,2), "pp","pp","pp", 'p', "plateIron"));
        //RecipeJsonConverter.addShapedRecipe(new ItemStack(TGItems.door01,2), "pp","pp","pp", 'p', "plateTin"));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.basicMachine,8,6), "rtr","tct","rtr", 'r', "itemRubber", 't', Blocks.tnt, 'c', "circuitBasic"));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.basicMachine,4,8), "rtr","tct","rtr", 'r', "sheetPlastic", 't', TGItems.tgx, 'c', "circuitBasic"));
	
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGItems.jetpack,1,0), "f f","pgp","rmr",'f', TGItems.flamethrowerTank, 'p', "plateObsidianSteel", 'g', TGItems.glider, 'r', TGItems.rocketAmmo, 'm', MECHANICAL_PARTS_OBSIDIAN_STEEL));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGItems.jetpack,1,0), "f f","pgp","rmr",'f', TGItems.flamethrowerTank, 'p', "plateObsidianSteel", 'g', new ItemStack(TGItems.jumppack,1,OreDictionary.WILDCARD_VALUE), 'r', TGItems.rocketAmmo, 'm', MECHANICAL_PARTS_OBSIDIAN_STEEL));
        
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGItems.antiGravPack,1,0), "tet","cac","mbm",'t', "plateTitanium", 'e', TGItems.nuclearPowerCell, 'b', TGItems.gliderBackback, 'c', "circuitElite", 'm', MECHANICAL_PARTS_IRONT3, 'a', TGItems.antiGravityCore));
        
        
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGItems.scubaTanks,1,0), "p p","aia","p p", 'p', "sheetPlastic", 'a', TGItems.compressedAirTank, 'i', "plateIron"));
        
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGItems.jumppack,1,0), "n n","pbp","c c", 'p', "plateIron", 'c', TGItems.compressedAirTank, 'n', "nuggetIron", 'b', TGItems.newStack(TGItems.gliderBackback, 1)));
           
        RecipeJsonConverter.addShapedRecipe(newStack(oxygenMask,1), " p ","rpr", 'p', "sheetPlastic", 'r', "itemRubber"));
        
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGItems.tacticalMask,1,0),"pnp","cgc","pop", 'p', "sheetPlastic", 'n', new ItemStack(TGItems.nightVisionGoggles,1,OreDictionary.WILDCARD_VALUE), 'c', "circuitBasic", 'g', new ItemStack(TGItems.gasMask,1,OreDictionary.WILDCARD_VALUE), 'o', TGItems.oxygenMask));
	
        ItemStack rc = new ItemStack(TGBlocks.machineMultiBlock,1, 0);
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.machineMultiBlock,9,0), "sms","pcp","ses", 's', "plateSteel", 'm', MECHANICAL_PARTS_IRONT3, 'p', TGItems.cyberneticParts, 'e', "circuitElite",'c', new ItemStack(TGBlocks.basicMachine,1, 4)));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.machineMultiBlock,1,1), " g ","crc"," g ", 'g', hardenedGlassOrGlass, 'c', "circuitElite",  'r', rc));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.machineMultiBlock,6,2), "rgr","rgr","rgr", 'r', rc, 'g', hardenedGlassOrGlass));
        
        ItemStack fab = new ItemStack(TGBlocks.machineMultiBlock,1, 3);
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.machineMultiBlock,4,3), "sms","pep","scs", 's', "plateSteel", 'm', MECHANICAL_PARTS_IRONT3, 'p', TGItems.cyberneticParts, 'e', TGItems.electricEngine, 'c', "circuitElite"));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.machineMultiBlock,1,4), " g ","cfc"," g ", 'g', hardenedGlassOrGlass, 'c', "circuitElite",  'f', fab));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.machineMultiBlock,4,5), "fgf","g g","fgf", 'f', fab, 'g', hardenedGlassOrGlass));
        
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.basicMachine,1,9), "sgs","cbc","sgs", 's', "plateSteel", 'g', "wireGold", 'b', "circuitBasic", 'c', TGItems.coil));
	
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGItems.combatKnife,1), " s","p ", 's', "plateSteel", 'p', "sheetPlastic"));
        
        RecipeJsonConverter.addShapedRecipe(TGItems.newStack(TGItems.rcHeatRay,1), "cwc","plp","plp", 'c', "circuitElite", 'w', "wireGold", 'p', "plateSteel", 'l', Blocks.redstone_lamp));
	
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGItems.oreClusterScanner,1), "g g", "pcp","pep", 'g', "wireGold", 'p', "plateSteel", 'c', "circuitElite", 'e', TGItems.energyCell));
	
	
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.metalpanel,32,0), "sss","sps","sss", 's', "stone", 'p', "plateCopper"));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.metalpanel2,64,0), "sss","psp","sss", 's', "stone", 'p', "plateCopper"));
        
        RecipeJsonConverter.addShapedRecipe(TGItems.newStack(TGItems.quartzRod,1), "q  ", " q ", "  q", 'q', "gemQuartz"));
        RecipeJsonConverter.addShapedRecipe(TGItems.newStack(TGItems.quartzRod,1), "  q", " q ", "q  ", 'q', "gemQuartz"));
        
        RecipeJsonConverter.addShapedRecipe(TGItems.newStack(TGItems.nuclearWarheadSmall, 2), "pcp","tut","pcp", 'p', "plateLead", 't', TGItems.tgx, 'c', "circuitBasic", 'u', "ingotUraniumEnriched"));
        
        RecipeJsonConverter.addShapelessRecipe(TGItems.newStack(TGItems.rocketAmmoNuclear, 1), TGItems.newStack(TGItems.rocketAmmo, 1), TGItems.newStack(TGItems.nuclearWarheadSmall, 1));
   
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGItems.camoNetPlacer,1,0),"sns",'s',"stickWood", 'n', new ItemStack(TGBlocks.camoNetPane,1,0)));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGItems.camoNetPlacer,1,1),"sns",'s',"stickWood", 'n', new ItemStack(TGBlocks.camoNetPane,1,1)));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGItems.camoNetPlacer,1,2),"sns",'s',"stickWood", 'n', new ItemStack(TGBlocks.camoNetPane,1,2)));
        

        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGItems.crowbar,1), "  p", " p ", "p  ", 'p', "plateSteel"));
                
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.tgchest,1), "n n", " c ", "n n", 'c', Blocks.chest, 'n', "nuggetIron"));
        RecipeJsonConverter.addShapedRecipe(new ItemStack(TGBlocks.tgchest_weapon,1), "pcp", 'c', Blocks.chest, 'p', "plateIron"));
        */
	}
}
