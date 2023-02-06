/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 */
package international.astro.hack.hacks.client;

import international.astro.Astro;
import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
import net.minecraft.client.gui.GuiScreen;

@Hack.Construct(name = "ClickGui", description = "Click dat Gui", category = Hack.Category.CLIENT)
public class ClickGuiMod
        extends Hack {
    public static OBoolean blur = new OBoolean("Blur", false);

    public ClickGuiMod() {
        this.setBind(54);
        this.addOption(blur);
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen((GuiScreen) Astro.clickGui);
        super.onEnable();
    }
}

