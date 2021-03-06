package techguns;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class TGConfig {
	public static Configuration config;
	
	public static boolean addCopperIngots;
	public static boolean addLeadNuggets;
	public static boolean addCopperNuggets;
	public static boolean addBronzeIngots;
	public static boolean addTinIngots;
	public static boolean addLeadIngots;
	public static boolean addSteelIngots;
	public static boolean addSteelNuggets;

	public static boolean cl_lockSpeedFov;
	public static float cl_fixedSprintFov;
	
	public static boolean cl_enableDeathFX;
	public static boolean cl_enableDeathFX_Gore;
	
	public static boolean disableAutofeeder;
	public static boolean machinesNeedNoPower;
	/**
	 * IDS
	 */
	public static int dataWatcherID_FaceSlot;
	public static int dataWatcherID_BackSlot;
	
	//public static int GUI_ID_tgplayerInventory;
	
	/**
	 * CATEGORIES
	 */
	private static final String CATEGORY_ENABLING_ITEMS = "Disable Items";
	
	private static final String CLIENTSIDE = "Clientside";
	private static final String ID_CONFLICTS = "ID Conflicts";
	
	public static void init(FMLPreInitializationEvent event){
		//Load the config file
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		disableAutofeeder = config.getBoolean("disableAutofeeder", config.CATEGORY_GENERAL, false, "Disable automatic feeding of Food in the Techguns tab. Disable autofeeding if you think it breaks the balance");	
		machinesNeedNoPower = config.getBoolean("machinesNeedNoPower", config.CATEGORY_GENERAL, false, "Machines don't need power, activate this if you don't want to install a mod with generators and still be able to use the machines");
		
		addCopperIngots = config.getBoolean("addCopperIngot", CATEGORY_ENABLING_ITEMS, true, "Add copper ingots.");
		addCopperNuggets = config.getBoolean("addCopperNugget", CATEGORY_ENABLING_ITEMS, true, "Add copper nuggets.");
		addTinIngots = config.getBoolean("addTinIngot", CATEGORY_ENABLING_ITEMS, true, "Add tin ingots.");
		addBronzeIngots = config.getBoolean("addBronzeIngot", CATEGORY_ENABLING_ITEMS, true, "Add bronze ingots.");
		
		addLeadIngots = config.getBoolean("addLeadIngot", CATEGORY_ENABLING_ITEMS, true, "Add lead ingots.");
		addLeadNuggets = config.getBoolean("addLeadNugget", CATEGORY_ENABLING_ITEMS, true, "Add Lead nuggets.");
		
		addSteelIngots = config.getBoolean("addSteelIngot", CATEGORY_ENABLING_ITEMS, true, "Adds Steel ingots.");
		addSteelNuggets = config.getBoolean("addSteelNugget", CATEGORY_ENABLING_ITEMS, true, "Adds Steel nuggets.");
	
		
		dataWatcherID_FaceSlot = config.getInt("DataWatcherID_FaceSlot", ID_CONFLICTS, 23, 2,31, "The ID used for DataWatcher synchronization of the face slot for Players, the ID must not conflict with vanilla or other mods slots, see http://www.minecraftforge.net/wiki/Datawatcher for details. Never useable for EntityPlayer (used by vanilla minecraft): 0,1, 6,7,8,9, 16,17,18");
		
		dataWatcherID_BackSlot = config.getInt("DataWatcherID_BackSlot", ID_CONFLICTS, 24, 2,31, "The ID used for DataWatcher synchronization of the back slot for Players, the ID must not conflict with vanilla or other mods slots, see http://www.minecraftforge.net/wiki/Datawatcher for details. Never useable for EntityPlayer (used by vanilla minecraft): 0,1, 6,7,8,9, 16,17,18");
		
	//	GUI_ID_tgplayerInventory = config.getInt("TechgunsGUI_TabID", ID_CONFLICTS, 17, 0, 1000, "ID for the button used by the Techguns inventory tab.");
		
	
		
		
		cl_enableDeathFX = config.getBoolean("EnableDeathEffects", CLIENTSIDE, true, "Enable Death Effects, pure clientside check.");
		cl_enableDeathFX_Gore = config.getBoolean("EnableGoreDeathEffect", CLIENTSIDE, true, "Enable the gore Death Effect, requires DeathEffects to be enabled, pure clientside check.");
	
		
		cl_lockSpeedFov = config.getBoolean("LockSpeedDependantFov", CLIENTSIDE, true, "Counters the speed dependant FOV change. This also stops FOV changes while sprinting. Don't activate if another mod does this too, pure clientside check.");
		
		cl_fixedSprintFov = config.getFloat("FixedSprintFovMultiplier", CLIENTSIDE, 1.15f, 1.0f, 10.0f, "Multiply the FOV while sprinting by this value independent from the actual speed, has no effect when LockSpeedDependantFov is false, pure clientside check.");
		
		config.save();
	}

}
