package gay.vulpines.griefless.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gay.vulpines.griefless.Griefless;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SnowGolem.class)
public class Mixin_SnowGolem {
    @WrapOperation(
            method = "aiStep",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"
            )
    )
    boolean replaceMobGriefing(GameRules instance, GameRules.Key<GameRules.BooleanValue> key, Operation<Boolean> original) {
        if (instance.getBoolean(Griefless.SNOW_GOLEMS_EXCRETE_SNOW))
            return original.call(instance, key);
        return false;
    }
}
