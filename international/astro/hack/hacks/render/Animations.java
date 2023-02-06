/*
 * Decompiled with CFR 0.150.
 */
package international.astro.hack.hacks.render;

import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;
import international.astro.hack.option.options.OList;

@Hack.Construct(name = "Animations", description = "make stuff do cool things", category = Hack.Category.RENDER)
public class Animations
        extends Hack {
    public static Animations getInstance = new Animations();
    public OList mode = new OList("Mode", "Dortware", "Dortware");
    public ODouble swingSpeed = new ODouble("Speed", 1.0, 15.0, 1.0, 5.0);

    public Animations() {
        this.addOption(this.mode);
        this.addOption(this.swingSpeed);
    }
}

