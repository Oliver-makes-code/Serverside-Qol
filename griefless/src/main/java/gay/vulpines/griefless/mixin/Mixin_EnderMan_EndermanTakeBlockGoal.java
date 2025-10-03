package gay.vulpines.griefless.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gay.vulpines.griefless.Griefless;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = {"net.minecraft.world.entity.monster.EnderMan.EndermanTakeBlockGoal"})
public abstract class Mixin_EnderMan_EndermanTakeBlockGoal extends Goal {
    @Shadow @Final private EnderMan enderman;

    @WrapOperation(
            method = "canUse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"
            )
    )
    private boolean dontPickUp(GameRules instance, GameRules.Key<GameRules.BooleanValue> key, Operation<Boolean> original) {
        if (key == GameRules.RULE_MOBGRIEFING && getServerLevel(enderman).getGameRules().getBoolean(Griefless.ENDERMEN_PICK_UP_BLOCKS))
            return false;

        return original.call(instance, key);
    }
}
