/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.world.World
 */
package international.astro.mixins.mixin.net.minecraft.entity;

import international.astro.Astro;
import international.astro.hack.hacks.render.Animations;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {EntityLivingBase.class})
public abstract class MixinEntityLivingBase
        extends Entity {
    public MixinEntityLivingBase(World worldIn) {
        super(worldIn);
    }

    @Inject(method = {"getArmSwingAnimationEnd"}, at = {@At(value = "HEAD")}, cancellable = true)
    private void getArmSwingAnimationEnd(CallbackInfoReturnable<Integer> info) {
        if (Astro.hackManager.getHack("Animations").isEnabled()) {
            info.setReturnValue(Animations.getInstance.swingSpeed.getIntValue());
        }
    }
}

