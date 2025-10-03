package gay.vulpines.griefless.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gay.vulpines.griefless.Griefless;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerLevel.class)
public abstract class Mixin_ServerLevel {
    @Shadow public abstract GameRules getGameRules();

    @WrapOperation(
            method = "explode",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"
            )
    )
    private boolean dontExplode(GameRules instance, GameRules.Key<GameRules.BooleanValue> key, Operation<Boolean> original) {
        if (key == GameRules.RULE_MOBGRIEFING && !getGameRules().getBoolean(Griefless.CREEPERS_DESTROY_BLOCKS))
            return false;
        return original.call(instance, key);
    }
}
