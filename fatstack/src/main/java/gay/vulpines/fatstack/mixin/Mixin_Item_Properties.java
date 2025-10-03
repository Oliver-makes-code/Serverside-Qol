package gay.vulpines.fatstack.mixin;

import gay.vulpines.fatstack.DefaultComponentModification;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.Properties.class)
public class Mixin_Item_Properties {
    @Shadow private @Nullable ResourceKey<Item> id;

    @Shadow @Final private DataComponentMap.Builder components;

    @Inject(
            method = "buildAndValidateComponents",
            at = @At("HEAD")
    )
    void modifyDefaultComponents(Component component, ResourceLocation resourceLocation, CallbackInfoReturnable<DataComponentMap> cir) {
        // This will error later on.
        if (id == null)
            return;

        DefaultComponentModification.modify(
                id,
                new DefaultComponentModification.Builder(DataComponentPatch.builder(), components)
        );
    }
}
