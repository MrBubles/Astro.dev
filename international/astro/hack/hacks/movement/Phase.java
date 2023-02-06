/*
 * Decompiled with CFR 0.150.
 */
package international.astro.hack.hacks.movement;

import international.astro.hack.Hack;
import international.astro.hack.option.options.OList;

@Hack.Construct(name = "Phase", description = "Phase n shit", category = Hack.Category.MOVEMENT)
public class Phase
        extends Hack {
    public static Phase getInstance = new Phase();
    public OList mode = new OList("Mode", "Boat", "Boat");

    public Phase() {
        this.addOption(this.mode);
    }

    @Override
    public void onDisable() {
        if (Phase.mc.player.isRiding()) {
            Phase.mc.player.getRidingEntity().setNoGravity(false);
            Phase.mc.player.getRidingEntity().noClip = false;
            Phase.mc.player.noClip = false;
        }
    }

    @Override
    public void onEnable() {
        if (this.nullCheck()) {
            return;
        }
        if (this.mode.isMode("Boat") && Phase.mc.player.isRiding()) {
            Phase.mc.player.getRidingEntity().setNoGravity(true);
            Phase.mc.player.getRidingEntity().noClip = true;
            Phase.mc.player.noClip = true;
        }
    }
}

