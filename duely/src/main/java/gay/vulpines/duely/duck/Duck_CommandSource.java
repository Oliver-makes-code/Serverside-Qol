package gay.vulpines.duely.duck;

import net.minecraft.server.level.ServerPlayer;

public interface Duck_CommandSource {
    boolean duely$isPlayer(ServerPlayer player);
    ServerPlayer duely$getPlayer();
}
