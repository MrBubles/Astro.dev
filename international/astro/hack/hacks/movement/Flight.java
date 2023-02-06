/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent
 */
package international.astro.hack.hacks.movement;

import international.astro.hack.Hack;
import international.astro.hack.option.options.OList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "Flight", description = "Fly", category = Hack.Category.MOVEMENT)
public class Flight
        extends Hack {
    public static Flight getInstance = new Flight();
    public OList mode = new OList("Mode", "Vanilla", "Vanilla", "Minemora");
    int glideDelay;

    public Flight() {
        this.addOption(this.mode);
    }

    @Override
    public void onEnable() {
        if (this.mode.isMode("Vanilla")) {
            Flight.mc.player.capabilities.isFlying = true;
            if (Flight.mc.player.capabilities.isCreativeMode) {
                return;
            }
            Flight.mc.player.capabilities.allowFlying = true;
            Flight.mc.player.capabilities.setFlySpeed(20.0f);
            super.onEnable();
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent e) {
        if (this.mode.isMode("Minemora")) {
            Flight.mc.player.motionY = -0.07;
            if (!Flight.mc.player.onGround) {
                ++this.glideDelay;
            }
            if (this.glideDelay >= 2 && !Flight.mc.player.onGround) {
                Flight.mc.player.motionY = 0.06999999999999999;
                this.glideDelay = 0;
            }
        }
    }

    @Override
    public void onDisable() {
        Flight.mc.player.capabilities.isFlying = false;
        Flight.mc.player.capabilities.allowFlying = false;
        super.onDisable();
    }
}

