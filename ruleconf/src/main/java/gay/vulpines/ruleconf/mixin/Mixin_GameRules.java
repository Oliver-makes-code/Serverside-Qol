package gay.vulpines.ruleconf.mixin;

import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicLike;
import com.mojang.serialization.JsonOps;
import gay.vulpines.ruleconf.RuleConf;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(GameRules.class)
public abstract class Mixin_GameRules {
    @Shadow protected abstract void loadFromTag(DynamicLike<?> dynamicLike);

    @Inject(
            method = "<init>(Ljava/util/Map;Lnet/minecraft/world/flag/FeatureFlagSet;)V",
            at = @At("RETURN")
    )
    private void injectRules(Map<?, ?> map, FeatureFlagSet featureFlagSet, CallbackInfo ci) {
        loadFromTag(new Dynamic<>(JsonOps.INSTANCE, RuleConf.getRuleMap()));
    }
}
