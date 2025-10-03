package gay.vulpines.griefless;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import org.jetbrains.annotations.Nullable;

public class Griefless implements ModInitializer {
    public static final GameRules.Key<GameRules.BooleanValue> CREEPERS_DESTROY_BLOCKS = createBoolean("griefless:creepersDestroyBlocks", true);
    public static final GameRules.Key<GameRules.BooleanValue> ENDERMEN_PICK_UP_BLOCKS = createBoolean("griefless:endermenPickUpBlocks", true);

    public static @Nullable MinecraftServer serverInstance;

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTING.register((server) -> {
            serverInstance = server;
        });
        ServerLifecycleEvents.SERVER_STOPPING.register((server) -> {
            serverInstance = null;
        });
    }

    public static GameRules.Key<GameRules.BooleanValue> createBoolean(String name, boolean defaultValue) {
        return GameRuleRegistry.register(name, GameRules.Category.MISC, GameRuleFactory.createBooleanRule(defaultValue));
    }
}
