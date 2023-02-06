/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package international.astro.mixins.mixin.net.minecraft.entity;

import international.astro.events.TurnEvent;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {Entity.class})
public abstract class MixinEntity {
    @Inject(method = {"turn"}, at = {@At(value = "HEAD")}, cancellable = true)
    public void onTurnHook(float yaw, float pitch, CallbackInfo ci) {
        TurnEvent e = new TurnEvent(yaw, pitch);
        MinecraftForge.EVENT_BUS.post((Event) e);
        if (e.isCanceled()) {
            ci.cancel();
        }
    }
}

