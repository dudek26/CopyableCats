package eu.dudko.create_copyable_cats.mixin;

import com.copycatsplus.copycats.foundation.copycat.multistate.IMultiStateCopycatBlockEntity;
import com.copycatsplus.copycats.foundation.copycat.multistate.MaterialItemStorage;
import com.copycatsplus.copycats.foundation.copycat.multistate.MultiStateCopycatBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultiStateCopycatBlockEntity.class)
public abstract class MultiStateCopycatBlockEntityMixin implements IMultiStateCopycatBlockEntity {

    @Inject(method = "getMaterialItemStorage", at = @At("RETURN"), cancellable = true)
    public void getMaterialItemStorage(CallbackInfoReturnable<MaterialItemStorage> cir) {
        MaterialItemStorage original = cir.getReturnValue();
        if (original == null) {
            cir.setReturnValue(MaterialItemStorage.create());
        }
    }

}
