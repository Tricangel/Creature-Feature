package bee.creature.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.ArrayList;
import java.util.List;

public class BlockStateProviderOverrides {
    private static final List<BlockOverride> blockOverrides = new ArrayList<>();

    public static BlockState shouldOverride(BlockState state, RandomSource random, BlockPos pos) {

        for (BlockOverride override : blockOverrides) {
            if ((override.target().equals(state) || override.target().equals(state.getBlock()))) {
                if (override.chance == 0 || random.nextInt(override.chance()) == 0) {
                    return override.replacement().getState(random, pos);
                }
            }
        }


        return null;

    }

    public static void addOverride(Block target, BlockStateProvider replacement, int chance) {
        blockOverrides.add(new BlockOverride(target, replacement, chance));
    }

    public static void addOverride(BlockState target, BlockStateProvider replacement, int chance) {
        blockOverrides.add(new BlockOverride(target, replacement, chance));
    }

    public static void addOverride(Block target, BlockStateProvider replacement) {
        blockOverrides.add(new BlockOverride(target, replacement, 0));
    }

    public static void addOverride(BlockState target, BlockStateProvider replacement) {
        blockOverrides.add(new BlockOverride(target, replacement, 0));
    }


    private record BlockOverride(Object target, BlockStateProvider replacement, int chance) {

    }


}
