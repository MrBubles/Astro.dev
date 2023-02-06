/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent
 */
package international.astro.hack.hacks.render;

import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "Fov", description = "change fov", category = Hack.Category.RENDER)
public class Fov
        extends Hack {
    public ODouble swingSpeed = new ODouble("Time", 1.0, 50.0, 1.0, 6.0);
    ODouble fovSlider = new ODouble("Fov", 75.0, 150.0, 1.0, 120.0);

    public Fov() {
        this.addOption(this.fovSlider);
    }

    @SubscribeEvent
    public void onTick(TickEvent event) {
        Fov.mc.gameSettings.fovSetting = this.fovSlider.getIntValue();
    }
}

