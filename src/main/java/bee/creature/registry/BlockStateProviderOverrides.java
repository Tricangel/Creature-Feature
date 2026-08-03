package bee.creature.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class BlockStateProviderOverrides {
    private static final List<StateOverride> blockStateOverrides = new ArrayList<>();
    private static final List<BlockOverride> blockOverrides = new ArrayList<>();

    public static BlockState shouldOverride(BlockState state, RandomSource random) {

        for (BlockOverride override : blockOverrides) {
            if (override.target().equals(state.getBlock()) && random.nextInt(override.chance()) == 0) {
                return override.replacement();
            }
        }

        for (StateOverride override : blockStateOverrides) {
            if (override.target().equals(state) && random.nextInt(override.chance()) == 0) {
                return override.replacement();
            }
        }

        return null;

    }

    public static void addOverride(Block target, BlockState replacement, int chance) {
        blockOverrides.add(new BlockOverride(target, replacement, chance));
    }

    public static void addOverride(BlockState target, BlockState replacement, int chance) {
        blockStateOverrides.add(new StateOverride(target, replacement, chance));
    }


    private record BlockOverride(Block target, BlockState replacement, int chance) {

    }

    private record StateOverride(BlockState target, BlockState replacement, int chance) {

    }


}
