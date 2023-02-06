/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package international.astro.mixins.mixin.net.minecraft.client.multiplayer;

import international.astro.events.AttackEvent;
import international.astro.events.DamageBlockEvent;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {PlayerControllerMP.class})
public class MixinPlayerControllerMP {
    @Inject(method = {"attackEntity"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;syncCurrentPlayItem()V")}, cancellable = true)
    private void attackEntity(EntityPlayer entityPlayer, Entity target, CallbackInfo ci) {
        if (target == null) {
            return;
        }
        AttackEvent event = new AttackEvent(target);
        MinecraftForge.EVENT_BUS.register((Object) event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method = {"onPlayerDamageBlock"}, at = {@At(value = "HEAD")}, cancellable = true)
    private void onPlayerDamageBlock(BlockPos blockPos, EnumFacing enumFacing, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        MinecraftForge.EVENT_BUS.post((Event) new DamageBlockEvent(blockPos, enumFacing));
    }
}

