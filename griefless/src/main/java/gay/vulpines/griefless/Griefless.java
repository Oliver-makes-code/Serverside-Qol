package gay.vulpines.griefless;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;

public class Griefless implements ModInitializer {
    public static final GameRules.Key<GameRules.BooleanValue> MOBS_EXPLODE_BLOCKS = createBoolean("griefless:mobsExplodeBlocks", true);
    public static final GameRules.Key<GameRules.BooleanValue> ENDERMEN_PICK_UP_BLOCKS = createBoolean("griefless:endermenPickUpBlocks", true);
    public static final GameRules.Key<GameRules.BooleanValue> ENDERMEN_PLACE_BLOCKS = createBoolean("griefless:endermenPlaceBlocks", true);
    public static final GameRules.Key<GameRules.BooleanValue> MOBS_BREAK_DOWN_DOORS = createBoolean("griefless:mobsBreakDownDoors", true);
    public static final GameRules.Key<GameRules.BooleanValue> WEAVING_CREATES_COBWEBS = createBoolean("griefless:weavingCreatesCobwebs", true);
    public static final GameRules.Key<GameRules.BooleanValue> WITHER_CREATES_ROSES = createBoolean("griefless:witherCreatesRoses", true);
    public static final GameRules.Key<GameRules.BooleanValue> MOBS_PICK_UP_ITEMS = createBoolean("griefless:mobsPickUpItems", true);
    public static final GameRules.Key<GameRules.BooleanValue> VILLAGERS_HARVEST_FARMLAND = createBoolean("griefless:villagersHarvestFarmland", true);
    public static final GameRules.Key<GameRules.BooleanValue> SHEEP_EAT_GRASS = createBoolean("griefless:sheepEatGrass", true);
    public static final GameRules.Key<GameRules.BooleanValue> ZOMBIES_CRUSH_TURTLE_EGGS = createBoolean("griefless:zombiesCrushTurtleEggs", true);
    /// Please do not starve the fuck,,,,
    public static final GameRules.Key<GameRules.BooleanValue> FOXES_EAT_BERRIES = createBoolean("griefless:foxesEatBerries", true);
    public static final GameRules.Key<GameRules.BooleanValue> RABBITS_EAT_CROPS = createBoolean("griefless:rabbitsEatCrops", true);
    public static final GameRules.Key<GameRules.BooleanValue> SNOW_GOLEMS_EXCRETE_SNOW = createBoolean("griefless:snowGolemsExcreteSnow", true);
    public static final GameRules.Key<GameRules.BooleanValue> ALLAYS_PICK_UP_ITEMS = createBoolean("griefless:allaysPickUpItems", true);
    public static final GameRules.Key<GameRules.BooleanValue> GOATS_RAM_MOBS = createBoolean("griefless:goatsRamMobs", true);
    public static final GameRules.Key<GameRules.BooleanValue> ENDER_DRAGON_BREAKS_BLOCKS = createBoolean("griefless:enderDragonBreaksBlocks", true);
    public static final GameRules.Key<GameRules.BooleanValue> WITHER_BREAKS_BLOCKS = createBoolean("griefless:witherBreaksBlocks", true);
    public static final GameRules.Key<GameRules.BooleanValue> MOBS_BREAK_ARMOR_STANDS = createBoolean("griefless:mobsBreakArmorStands", true);
    public static final GameRules.Key<GameRules.BooleanValue> MOBS_BREAK_LEASH_KNOTS = createBoolean("griefless:mobsBreakLeashKnots", true);
    public static final GameRules.Key<GameRules.BooleanValue> MOBS_DESTROY_ITEMS = createBoolean("griefless:mobsDestroyItems", true);
    public static final GameRules.Key<GameRules.BooleanValue> EVOKERS_WOLOLO_SHEEP = createBoolean("griefless:evokersWololoSheep", true);
    public static final GameRules.Key<GameRules.BooleanValue> RAVAGERS_BREAK_BLOCKS = createBoolean("griefless:ravagersBreakBlocks", true);
    public static final GameRules.Key<GameRules.BooleanValue> RAVAGERS_BREAK_ARMOR_STANDS = createBoolean("griefless:ravagersBreakArmorStands", true);
    public static final GameRules.Key<GameRules.BooleanValue> SILVERFISH_INFEST_STONE = createBoolean("griefless:silverfishInfestStone", true);
    public static final GameRules.Key<GameRules.BooleanValue> SILVERFISH_CALL_FOR_BACKUP = createBoolean("griefless:silverfishCallForBackup", true);
    public static final GameRules.Key<GameRules.BooleanValue> PIGLINS_PICK_UP_ITEMS = createBoolean("griefless:piglinsPickUpItems", true);
    public static final GameRules.Key<GameRules.BooleanValue> GHAST_FIREBALLS_CREATE_FIRE = createBoolean("griefless:ghastFireballsCreateFire", true);
    public static final GameRules.Key<GameRules.BooleanValue> FIREBALLS_CREATE_FIRE = createBoolean("griefless:fireballsCreateFire", true);
    public static final GameRules.Key<GameRules.BooleanValue> MOB_PROJECTILES_TRIGGER_BLOCKS = createBoolean("griefless:mobProjectilesTriggerBlocks", true);
    public static final GameRules.Key<GameRules.BooleanValue> MOBS_EXPLODE_VEHICLES = createBoolean("griefless:mobsExplodeVehicles", true);
    public static final GameRules.Key<GameRules.BooleanValue> BREEZES_TRIGGER_BLOCKS = createBoolean("griefless:breezesTriggerBlocks", true);
    public static final GameRules.Key<GameRules.BooleanValue> EXPLOSIONS_DISRUPT_SHULKERS = createBoolean("griefless:explosionsDisruptShulkers", true);
    public static final GameRules.Key<GameRules.BooleanValue> RAVAGERS_BREAK_CROPS = createBoolean("griefless:ravagersBreakCrops", true);
    public static final GameRules.Key<GameRules.BooleanValue> MOBS_TRAMPLE_FARMLAND = createBoolean("griefless:mobsTrampleFarmland", true);
    public static final GameRules.Key<GameRules.BooleanValue> MOBS_ON_FIRE_DESTROY_POWDERED_SNOW = createBoolean("griefless:mobsOnFireDestroyPowderedSnow", true);
    public static final GameRules.Key<GameRules.BooleanValue> MOBS_BREAK_TURTLE_EGGS = createBoolean("griefless:mobsBreakTurtleEggs", true);

    @Override
    public void onInitialize() {
    }

    public static GameRules.Key<GameRules.BooleanValue> createBoolean(String name, boolean defaultValue) {
        return GameRuleRegistry.register(name, GameRules.Category.MISC, GameRuleFactory.createBooleanRule(defaultValue));
    }
}
