/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.network.play.server.SPacketTimeUpdate
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package international.astro.hack.hacks.render;

import international.astro.events.PacketReceiveEvent;
import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "WorldTime", description = "Change World Time", category = Hack.Category.RENDER)
public class WorldTime
        extends Hack {
    public ODouble worldTime = new ODouble("Time", 1.0, 20.0, 1.0, 10.0);

    public WorldTime() {
        this.addOption(this.worldTime);
    }

    @SubscribeEvent
    public void onUpdate(TickEvent.ClientTickEvent event) {
        if (this.nullCheck()) {
            return;
        }
        WorldTime.mc.world.setWorldTime((long) this.worldTime.getValue() * 1000L);
    }

    @SubscribeEvent
    public void onPacketReceived(PacketReceiveEvent e) {
        if (e.getPacket() instanceof SPacketTimeUpdate) {
            e.setCanceled(true);
        }
    }
}

