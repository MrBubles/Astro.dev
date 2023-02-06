/*
 * Decompiled with CFR 0.150.
 */
package international.astro.hack.hacks.render;

import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;

@Hack.Construct(name = "NoRender", description = "stop rendering stuff", category = Hack.Category.RENDER)
public class NoRender
        extends Hack {
    public static NoRender getInstance = new NoRender();
    public OBoolean hurtCam = new OBoolean("NoHurtCam", true);
    public OBoolean weather = new OBoolean("NoWeather", true);

    public NoRender() {
        this.addOption(this.hurtCam);
        this.addOption(this.weather);
    }

    @Override
    public void onEnable() {
        if (this.weather.isEnabled()) {
            NoRender.mc.world.setRainStrength(0.0f);
            NoRender.mc.world.setThunderStrength(0.0f);
        }
        super.onEnable();
    }
}

