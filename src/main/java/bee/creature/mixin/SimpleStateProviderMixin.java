package bee.creature.mixin;

import bee.creature.registry.BlockStateProviderOverrides;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.stateproviders.RotatedBlockProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SimpleStateProvider.class)
public class SimpleStateProviderMixin {
	@ModifyReturnValue(at = @At("RETURN"), method = "getState")
	private BlockState init(BlockState original, final LevelAccessor level, final RandomSource random, final BlockPos pos) {
		BlockState state = BlockStateProviderOverrides.shouldOverride(original, level, random, pos);

		if (state != null) {
			return state;
		}

		return original;
	}
}