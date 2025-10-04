package gay.vulpines.chatproto;

import com.mojang.serialization.Codec;
import gay.vulpines.chatproto.mixin.Accessor_JsonRpcNotificationService;
import gay.vulpines.chatproto.mixin.Accessor_OutgoingRpcMethod_OutgoingRpcMethodBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.jsonrpc.OutgoingRpcMethod;
import net.minecraft.server.jsonrpc.api.ParamInfo;
import net.minecraft.server.jsonrpc.api.PlayerDto;
import net.minecraft.server.jsonrpc.api.Schema;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.notifications.NotificationManager;

import java.util.function.Consumer;

public class ChatProto implements ModInitializer {
    public static final String MODID = "chatproto";

    public static final Holder.Reference<OutgoingRpcMethod.Notification<PlayerChatMessageNotification>> PLAYER_CHAT_MESSAGE = registerNotification(
            PlayerChatMessageNotification.CODEC,
            ResourceLocation.fromNamespaceAndPath(MODID, "notification/chat_message"),
            it -> it
                    .param(new ParamInfo("message", Schema.MESSAGE_SCHEMA.schema()))
                    .param(new ParamInfo("player", Schema.PLAYER_SCHEMA.schema()))
                    .description("Player sends a chat message")
    );

    @Override
    public void onInitialize() {
        ServerMessageEvents.CHAT_MESSAGE.register(ChatProto::onMessage);
    }

    public static void onMessage(PlayerChatMessage message, ServerPlayer player, ChatType.Bound bound) {
        broadcastNotification(
                player.level().getServer().notificationManager(),
                PLAYER_CHAT_MESSAGE,
                new PlayerChatMessageNotification(
                        message.decoratedContent(),
                        PlayerDto.from(player)
                )
        );
    }

    private static <T> void broadcastNotification(NotificationManager manager, Holder.Reference<? extends OutgoingRpcMethod<T, ?>> reference, T object) {
        ((Accessor_JsonRpcNotificationService) manager).invokeBroadcastNotification(reference, object);
    }

    @SuppressWarnings("unchecked")
    private static <T> Holder.Reference<OutgoingRpcMethod.Notification<T>> registerNotification(
            Codec<T> codec,
            ResourceLocation location,
            Consumer<OutgoingRpcMethod.OutgoingRpcMethodBuilder<OutgoingRpcMethod.Notification<T>>> consumer
    ) {
        var builder = OutgoingRpcMethod.notification(codec);
        consumer.accept(builder);
        return ((Accessor_OutgoingRpcMethod_OutgoingRpcMethodBuilder<T>) builder).invokeRegister(location);
    }
}
