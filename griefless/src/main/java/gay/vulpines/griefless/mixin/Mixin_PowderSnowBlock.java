package gay.vulpines.griefless.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gay.vulpines.griefless.Griefless;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PowderSnowBlock.class)
public class Mixin_PowderSnowBlock {
    @WrapOperation(
            method = "method_67681",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"
            )
    )
    private static boolean replaceMobGriefing(GameRules instance, GameRules.Key<GameRules.BooleanValue> key, Operation<Boolean> original) {
        if (instance.getBoolean(Griefless.MOBS_ON_FIRE_DESTROY_POWDERED_SNOW))
            return original.call(instance, key);
        return false;
    }
}
