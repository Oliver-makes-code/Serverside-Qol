package gay.vulpines.harvester;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class Harvester implements ModInitializer {
    public static final TagKey<Block> ALLOWED_HARVEST = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("harvester", "harvestable"));

    public static final IntegerProperty[] AGES = {
            BlockStateProperties.AGE_1,
            BlockStateProperties.AGE_2,
            BlockStateProperties.AGE_3,
            BlockStateProperties.AGE_4,
            BlockStateProperties.AGE_5,
            BlockStateProperties.AGE_7,
            BlockStateProperties.AGE_15,
            BlockStateProperties.AGE_25,
    };

    public static final int[] MATURITY = {
            1,
            2,
            3,
            4,
            5,
            7,
            15,
            25,
    };

    @Override
    public void onInitialize() {
        UseBlockCallback.EVENT.register(((player, level, hand, hit) -> {
            if (player.isCrouching())
                return InteractionResult.PASS;

            var pos = hit.getBlockPos();
            var state = level.getBlockState(pos);

            if (!state.is(ALLOWED_HARVEST))
                return InteractionResult.PASS;

            for (int i = 0; i < AGES.length; i++) {
                if (state.hasProperty(AGES[i]) && state.getValue(AGES[i]) == MATURITY[i]) {
                    level.destroyBlock(pos, true, player);
                    level.setBlock(pos, state.setValue(AGES[i], 0), Block.UPDATE_ALL);

                    return InteractionResult.SUCCESS;
                }
            }

            return InteractionResult.PASS;
        }));
    }
}
