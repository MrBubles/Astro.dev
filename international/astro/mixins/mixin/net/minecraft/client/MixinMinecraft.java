/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.Timer
 */
package international.astro.mixins.mixin.net.minecraft.client;

import international.astro.Astro;
import international.astro.graphics.menu.AstroMainMenu;
import international.astro.mixins.accessor.IMinecraft;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {Minecraft.class})
public abstract class MixinMinecraft
        implements IMinecraft {
    @Shadow
    private Timer timer;

    @Override
    public Timer getTimer() {
        return this.timer;
    }

    @Override
    @Accessor
    public abstract void setRightClickDelayTimer(int var1);

    @Shadow
    public abstract void displayGuiScreen(@Nullable GuiScreen var1);

    @Inject(method = {"init"}, at = {@At(value = "TAIL")})
    private void init(CallbackInfo ci) {
        this.displayGuiScreen(new AstroMainMenu());
    }

    @Inject(method = {"shutdownMinecraftApplet"}, at = {@At(value = "HEAD")})
    public void shutdownMinecraftApplet(CallbackInfo ci) {
        Astro.onShutdown();
    }
}

