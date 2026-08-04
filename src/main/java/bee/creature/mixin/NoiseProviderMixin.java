package bee.creature.mixin;

import bee.creature.registry.BlockStateProviderOverrides;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(NoiseProvider.class)
public class NoiseProviderMixin {
	@ModifyReturnValue(at = @At("RETURN"), method = "getState")
	private BlockState init(BlockState original, RandomSource randomSource, BlockPos blockPos) {
		BlockState state = BlockStateProviderOverrides.shouldOverride(original, randomSource, blockPos);

		if (state != null) {
			return state;
		}

		return original;
	}
}