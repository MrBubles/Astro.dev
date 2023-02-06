/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.util.Timer
 */
package international.astro.mixins.mixin.net.minecraft.util;

import international.astro.mixins.accessor.ITimer;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = {Timer.class})
public abstract class TimerMixin
        implements ITimer {
    @Override
    @Accessor
    public abstract void setTickLength(float var1);
}

