/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.network.play.client.CPacketPlayer
 */
package international.astro.mixins.mixin.net.minecraft.network.play.client;

import international.astro.mixins.accessor.ICPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = {CPacketPlayer.class})
public abstract class MixinCPacketPlayer
        implements ICPacketPlayer {
    @Override
    @Accessor(value = "x")
    public abstract void setX(double var1);

    @Override
    @Accessor(value = "y")
    public abstract void setY(double var1);

    @Override
    @Accessor(value = "z")
    public abstract void setZ(double var1);

    @Override
    @Accessor
    public abstract void setYaw(float var1);

    @Override
    @Accessor
    public abstract void setPitch(float var1);

    @Override
    @Accessor
    public abstract void setOnGround(boolean var1);

    @Override
    @Accessor
    public abstract void setMoving(boolean var1);
}

