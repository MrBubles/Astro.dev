/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.network.play.server.SPacketPlayerPosLook
 */
package international.astro.mixins.mixin.net.minecraft.network.play.server;

import international.astro.mixins.accessor.ISPacketPlayerPosLook;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = {SPacketPlayerPosLook.class})
public abstract class MixinSPacketPlayerPosLook
        implements ISPacketPlayerPosLook {
    @Override
    @Accessor
    public abstract void setYaw(float var1);

    @Override
    @Accessor
    public abstract void setPitch(float var1);
}

