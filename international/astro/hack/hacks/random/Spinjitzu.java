/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package international.astro.hack.hacks.random;

import international.astro.hack.Hack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "Spinjitzu", description = "nigjago", category = Hack.Category.RANDOM)
public class Spinjitzu
        extends Hack {
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        if (this.nullCheck()) {
            return;
        }
        Spinjitzu.mc.player.rotationYaw += 45.0f;
    }
}

