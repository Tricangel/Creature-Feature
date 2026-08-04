package bee.creature;

import bee.creature.registry.BlockStateProviderOverrides;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreatureFeature implements ModInitializer {
	public static final String MOD_ID = "creature-feature";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
