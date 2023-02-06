/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.util.Timer
 */
package international.astro.mixins.accessor;

import net.minecraft.util.Timer;

public interface IMinecraft {
    public Timer getTimer();

    public void setRightClickDelayTimer(int var1);
}

