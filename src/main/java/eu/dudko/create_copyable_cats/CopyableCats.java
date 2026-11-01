package eu.dudko.create_copyable_cats;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(CopyableCats.ID)
public class CopyableCats {
    public static final String ID = "create_copyable_cats";
    public static final String NAME = "Create: WhaTT";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CopyableCats(IEventBus eventBus, ModContainer container) {
        
    }

    public static ResourceLocation id(String key) {
        return ResourceLocation.fromNamespaceAndPath(CopyableCats.ID, key);
    }

}
