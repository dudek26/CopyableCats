package eu.dudko.create_copyable_cats.mixin;

import com.copycatsplus.copycats.foundation.copycat.model.neoforge.CopycatModelNeoForge;
import com.copycatsplus.copycats.foundation.copycat.multistate.IMultiStateCopycatBlock;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

@Mixin(value = CopycatModelNeoForge.class, remap = false)
public abstract class CopycatModelNeoForgeMixin {

    @WrapOperation(method = "gatherModelData",
            at = @At(value = "INVOKE",
                    target = "Lcom/copycatsplus/copycats/foundation/copycat/multistate/IMultiStateCopycatBlock;getVectorFromProperty(Lnet/minecraft/world/level/block/state/BlockState;Ljava/lang/String;)Lnet/minecraft/core/Vec3i;"))
    public Vec3i create_copyable_cats$preventCrash(IMultiStateCopycatBlock instance, BlockState blockState, String string, Operation<Vec3i> original) {
        try {
            return original.call(instance, blockState, string);
        } catch (Exception e) {
            return null;
        }
    }

    @Inject(method = "gatherModelData",
            at = @At(value = "INVOKE_ASSIGN",
                    target = "Lcom/copycatsplus/copycats/foundation/copycat/multistate/IMultiStateCopycatBlock;getVectorFromProperty(Lnet/minecraft/world/level/block/state/BlockState;Ljava/lang/String;)Lnet/minecraft/core/Vec3i;"),
            cancellable = true)
    public void create_copyable_cats$renderError(ModelData.Builder builder, BlockAndTintGetter world, BlockPos pos, BlockState state, ModelData blockEntityData, CallbackInfoReturnable<ModelData.Builder> cir, @Local(
            name = "inner") Vec3i inner) {
        if (inner == null) {
            Map<String, BlockState> materials = CopycatModelNeoForge.getMaterials(blockEntityData);
            Map<String, BlockState> errorMats = new HashMap<>();
            materials.forEach((s, bs) -> errorMats.put(s, Blocks.REPEATING_COMMAND_BLOCK.defaultBlockState()));
            builder.with(CopycatModelNeoForge.MATERIALS_PROPERTY, new HashMap<>(errorMats));
            cir.setReturnValue(builder);
        }
    }

}
