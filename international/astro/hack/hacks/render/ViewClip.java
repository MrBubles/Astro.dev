/*
 * Decompiled with CFR 0.150.
 */
package international.astro.hack.hacks.render;

import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;

@Hack.Construct(name = "ViewClip", description = "Change perspective n shit", category = Hack.Category.RENDER)
public class ViewClip
        extends Hack {
    public static ViewClip getInstance = new ViewClip();
    public ODouble distance = new ODouble("Distance", 1.0, 20.0, 1.0, 5.0);

    public ViewClip() {
        this.addOption(this.distance);
    }
}

