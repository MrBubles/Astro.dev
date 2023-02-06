/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 */
package international.astro.mixins.mixin.net.minecraft.client.entity;

import com.mojang.authlib.GameProfile;
import international.astro.Astro;
import international.astro.events.PostMotionEvent;
import international.astro.events.PreMotionEvent;
import international.astro.hack.hacks.misc.ChatPlus;
import international.astro.mixins.accessor.IEntityPlayerSP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {EntityPlayerSP.class})
public abstract class MixinEntityPlayerSP
        extends AbstractClientPlayer
        implements IEntityPlayerSP {
    public MixinEntityPlayerSP(World worldIn, GameProfile playerProfile) {
        super(worldIn, playerProfile);
    }

    @Override
    @Accessor
    public abstract void setLastReportedPosY(double var1);

    @Redirect(method = {"onLivingUpdate"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;closeScreen()V"))
    public void closeScreen(EntityPlayerSP entityPlayerSP) {
        if (Astro.hackManager.getHack("ChatPlus").isEnabled() && ChatPlus.getInstance.portalChat.isEnabled()) {
            return;
        }
    }

    @Redirect(method = {"onLivingUpdate"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V"))
    public void closeScreen(Minecraft minecraft, GuiScreen screen) {
        if (Astro.hackManager.getHack("ChatPlus").isEnabled() && ChatPlus.getInstance.portalChat.isEnabled()) {
            return;
        }
    }

    @Inject(method = {"onUpdateWalkingPlayer"}, at = {@At(value = "HEAD")}, cancellable = true)
    protected void onUpdateWalkingPlayer_Head(CallbackInfo callbackInfo) {
        PreMotionEvent motionEvent = new PreMotionEvent(this.posX, this.getEntityBoundingBox().minY, this.posZ, this.rotationYaw, this.rotationPitch, this.onGround);
        MinecraftForge.EVENT_BUS.register((Object) motionEvent);
        if (motionEvent.isCanceled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = {"onUpdateWalkingPlayer"}, at = {@At(value = "RETURN")})
    protected void onUpdateWalkingPlayer_Return(CallbackInfo callbackInfo) {
        PostMotionEvent postMotionUpdate = new PostMotionEvent();
        postMotionUpdate.setCanceled(postMotionUpdate.isCanceled());
        MinecraftForge.EVENT_BUS.register((Object) postMotionUpdate);
    }
}

