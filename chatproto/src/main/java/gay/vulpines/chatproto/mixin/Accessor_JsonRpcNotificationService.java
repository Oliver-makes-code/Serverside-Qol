package gay.vulpines.chatproto.mixin;

import net.minecraft.core.Holder;
import net.minecraft.server.jsonrpc.JsonRpcNotificationService;
import net.minecraft.server.jsonrpc.OutgoingRpcMethod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(JsonRpcNotificationService.class)
public interface Accessor_JsonRpcNotificationService {
    @Invoker
    <Params> void invokeBroadcastNotification(Holder.Reference<? extends OutgoingRpcMethod<Params, ?>> reference, Params object);
}
