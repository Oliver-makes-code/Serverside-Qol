package gay.vulpines.duely.mixin;

import gay.vulpines.duely.duck.Duck_CommandSource;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "net.minecraft.server.level.ServerPlayer$3")
public class Mixin_ServerPlayer_CommandSource implements Duck_CommandSource {
    @Final
    @Shadow
    ServerPlayer field_54403;

    @Override
    public boolean duely$isPlayer(ServerPlayer player) {
        System.out.println(field_54403);
        return player == field_54403;
    }

    @Override
    public ServerPlayer duely$getPlayer() {
        return field_54403;
    }
}
