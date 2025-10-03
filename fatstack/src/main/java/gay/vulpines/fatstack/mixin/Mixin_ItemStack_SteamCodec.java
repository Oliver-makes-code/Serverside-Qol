package gay.vulpines.fatstack.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gay.vulpines.fatstack.DefaultComponentModification;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = {"net/minecraft/world/item/ItemStack$1"})
public class Mixin_ItemStack_SteamCodec {
    @WrapOperation(
            method = "encode(Lnet/minecraft/network/RegistryFriendlyByteBuf;Lnet/minecraft/world/item/ItemStack;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/core/component/PatchedDataComponentMap;asPatch()Lnet/minecraft/core/component/DataComponentPatch;"
            )
    )
    DataComponentPatch modifyPatchForClients(
            PatchedDataComponentMap instance,
            Operation<DataComponentPatch> original,
            RegistryFriendlyByteBuf registryFriendlyByteBuf,
            ItemStack itemStack
    ) {
        return DefaultComponentModification.applyPatch(
                BuiltInRegistries.ITEM
                        .getResourceKey(itemStack.getItem())
                        .get(),
                original.call(instance)
        );
    }
}
