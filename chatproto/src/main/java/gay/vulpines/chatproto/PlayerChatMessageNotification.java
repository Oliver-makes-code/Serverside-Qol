package gay.vulpines.chatproto;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.server.jsonrpc.api.PlayerDto;

public record PlayerChatMessageNotification(
        Component message,
        PlayerDto player
) {
    public static final Codec<PlayerChatMessageNotification> CODEC = RecordCodecBuilder.create(instance -> instance
            .group(
                    ComponentSerialization.CODEC.fieldOf("message").forGetter(PlayerChatMessageNotification::message),
                    PlayerDto.CODEC.fieldOf("player").forGetter(PlayerChatMessageNotification::player)
            ).apply(instance, PlayerChatMessageNotification::new)
    );
}
