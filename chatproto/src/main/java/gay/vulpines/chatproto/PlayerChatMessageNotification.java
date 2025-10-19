package gay.vulpines.chatproto;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import gay.vulpines.chatproto.mixin.Accessor_Schema;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.server.jsonrpc.OutgoingRpcMethod;
import net.minecraft.server.jsonrpc.api.PlayerDto;
import net.minecraft.server.jsonrpc.api.Schema;
import net.minecraft.server.jsonrpc.api.SchemaComponent;
import net.minecraft.server.jsonrpc.methods.Message;
import net.minecraft.server.jsonrpc.methods.ServerStateService;

import java.util.Optional;

public record PlayerChatMessageNotification(
        String message,
        PlayerDto player
) {
    public static final Codec<PlayerChatMessageNotification> CODEC = RecordCodecBuilder.create(instance -> instance
            .group(
                    Codec.STRING.fieldOf("message").forGetter(PlayerChatMessageNotification::message),
                    PlayerDto.CODEC.fieldOf("player").forGetter(PlayerChatMessageNotification::player)
            ).apply(instance, PlayerChatMessageNotification::new)
    );

    public static final SchemaComponent SCHEMA = Accessor_Schema.invokeRegisterSchema("chatproto:chat_message", Schema.record()
            .withField("message", Schema.STRING_SCHEMA)
            .withField("player", Schema.PLAYER_SCHEMA.schema())
    );
}
