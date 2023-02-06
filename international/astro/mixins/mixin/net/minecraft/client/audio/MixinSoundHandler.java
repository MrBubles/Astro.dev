/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.audio.SoundManager
 */
package international.astro.mixins.mixin.net.minecraft.client.audio;

import international.astro.mixins.accessor.ISoundHandler;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = {SoundHandler.class})
public class MixinSoundHandler
        implements ISoundHandler {
    @Shadow
    @Final
    private SoundManager sndManager;

    @Override
    public SoundManager getManager() {
        return this.sndManager;
    }
}

