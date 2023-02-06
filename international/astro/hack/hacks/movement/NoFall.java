/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package international.astro.hack.hacks.movement;

import international.astro.hack.Hack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "NoFall", description = "no fall dmg", category = Hack.Category.MOVEMENT)
public class NoFall
        extends Hack {
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (this.nullCheck()) {
            return;
        }
        if (NoFall.mc.player.fallDistance > 5.0f) {
            mc.getConnection().sendPacket((Packet) new CPacketPlayer(true));
        }
    }
}

