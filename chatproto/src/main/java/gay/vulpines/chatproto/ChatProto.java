package gay.vulpines.chatproto;

import com.mojang.serialization.Codec;
import gay.vulpines.chatproto.mixin.Accessor_JsonRpcNotificationService;
import gay.vulpines.chatproto.mixin.Accessor_NotificationManager;
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
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Consumer;

public class ChatProto implements ModInitializer {
    public static final String MODID = "chatproto";

    public static final Holder.Reference<OutgoingRpcMethod.Notification<PlayerChatMessageNotification>> PLAYER_CHAT_MESSAGE = registerNotification(
            PlayerChatMessageNotification.CODEC,
            ResourceLocation.fromNamespaceAndPath(MODID, "notification/chat_message"),
            it -> it
                    .param(new ParamInfo("message", PlayerChatMessageNotification.SCHEMA.schema()))
                    .description("Player sends a chat message")
    );

    @Override
    public void onInitialize() {
        ServerMessageEvents.CHAT_MESSAGE.register(ChatProto::onMessage);
    }

    public static void onMessage(PlayerChatMessage message, ServerPlayer player, ChatType.Bound bound) {
        var manager = (Accessor_NotificationManager)player.level().getServer().notificationManager();
        var notification = new PlayerChatMessageNotification(
                message.decoratedContent().getString(),
                PlayerDto.from(player)
        );

        for (var service : manager.getNotificationServices()) {
            if (service instanceof Accessor_JsonRpcNotificationService json)
                broadcastNotification(json, PLAYER_CHAT_MESSAGE, notification);
        }
    }

    private static <T> void broadcastNotification(Accessor_JsonRpcNotificationService service, Holder.Reference<? extends OutgoingRpcMethod<T, ?>> reference, T object) {
        service.invokeBroadcastNotification(reference, object);
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
