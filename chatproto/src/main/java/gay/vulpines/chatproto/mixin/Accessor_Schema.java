package gay.vulpines.chatproto.mixin;

import net.minecraft.server.jsonrpc.api.Schema;
import net.minecraft.server.jsonrpc.api.SchemaComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Schema.class)
public interface Accessor_Schema {
    @Invoker
    static SchemaComponent invokeRegisterSchema(String string, Schema schema) {
        return null;
    }
}
