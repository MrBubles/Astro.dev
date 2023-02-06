/*
 * Decompiled with CFR 0.150.
 */
package international.astro.hack.hacks.render;

import international.astro.hack.Hack;

@Hack.Construct(name = "FullBright", description = "see in the dark", category = Hack.Category.RENDER)
public class FullBright
        extends Hack {
    float oldGamma;

    @Override
    public void onEnable() {
        this.oldGamma = FullBright.mc.gameSettings.gammaSetting;
        FullBright.mc.gameSettings.gammaSetting = 500.0f;
    }

    @Override
    public void onDisable() {
        FullBright.mc.gameSettings.gammaSetting = this.oldGamma;
    }
}

