package eu.dudko.create_copyable_cats.mixin;

import com.copycatsplus.copycats.foundation.copycat.multistate.IMultiStateCopycatBlock;
import com.copycatsplus.copycats.foundation.copycat.multistate.IMultiStateCopycatBlockEntity;
import com.copycatsplus.copycats.foundation.copycat.multistate.MaterialItemStorage;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = IMultiStateCopycatBlockEntity.class, remap = false)
public interface IMultiStateCopycatBlockEntityMixin {

    @Shadow
    MaterialItemStorage getMaterialItemStorage();

    @Shadow
    IMultiStateCopycatBlock getBlock();

    @Unique
    default MaterialItemStorage.MaterialItem getMaterialItem(String property) {
        MaterialItemStorage.MaterialItem materialItem = getMaterialItemStorage().getMaterialItem(property);
        if (materialItem == null) {
            return new MaterialItemStorage.MaterialItem(Blocks.CHAIN_COMMAND_BLOCK.defaultBlockState(),
                    Blocks.CHAIN_COMMAND_BLOCK.asItem().getDefaultInstance());
        }
        return materialItem;
    }

    /**
     * @author dudko_
     * @reason prevent crashes
     */
    @Overwrite
    default BlockState getMaterial() {
        return getMaterialItem(getBlock().defaultProperty()).material();
    }

    /**
     * @author dudko_
     * @reason prevent crashes
     */
    @Overwrite
    default ItemStack getConsumedItem() {
        return getMaterialItem(getBlock().defaultProperty()).consumedItem();
    }

    /**
     * @author dudko_
     * @reason prevent crashes
     */
    @Overwrite
    default boolean isCTEnabled() {
        return getMaterialItem(getBlock().defaultProperty()).enableCT();
    }

    /**
     * @author dudko_
     * @reason prevent crashes
     */
    @Overwrite
    default void setMaterialInternal(BlockState material) {
        getMaterialItem(getBlock().defaultProperty()).setMaterial(material);
    }

    /**
     * @author dudko_
     * @reason prevent crashes
     */
    @Overwrite
    default void setConsumedItemInternal(ItemStack consumedItem) {
        getMaterialItem(getBlock().defaultProperty()).setConsumedItem(consumedItem);
    }

    /**
     * @author dudko_
     * @reason prevent crashes
     */
    @Overwrite
    default void setCTEnabledInternal(boolean value) {
        getMaterialItem(getBlock().defaultProperty()).setEnableCT(value);
    }

}
