package eu.dudko.create_copyable_cats;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(value = CopyableCats.ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = CopyableCats.ID, value = Dist.CLIENT)
public class CopyableCatsClient {

    public CopyableCatsClient(ModContainer container) {
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {

    }
}
