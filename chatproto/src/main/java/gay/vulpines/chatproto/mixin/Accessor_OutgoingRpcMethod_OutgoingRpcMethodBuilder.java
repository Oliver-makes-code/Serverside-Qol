package gay.vulpines.chatproto.mixin;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.jsonrpc.OutgoingRpcMethod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(OutgoingRpcMethod.OutgoingRpcMethodBuilder.class)
public interface Accessor_OutgoingRpcMethod_OutgoingRpcMethodBuilder<T> {
    @Invoker
    Holder.Reference<OutgoingRpcMethod.Notification<T>> invokeRegister(ResourceLocation resourceLocation);
}
