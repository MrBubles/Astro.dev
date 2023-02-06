/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.item.ItemExpBottle
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package international.astro.hack.hacks.misc;

import international.astro.events.PacketSendEvent;
import international.astro.hack.Hack;
import international.astro.mixins.accessor.ICPacketPlayer;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Hack.Construct(name = "FootExp", description = "xp too yo feet", category = Hack.Category.MISC)
public class FootExp
        extends Hack {
    @SubscribeEvent
    public void onPacketSend(PacketSendEvent e) {
        if (this.nullCheck()) {
            return;
        }
        if (e.getPacket() instanceof CPacketPlayer && FootExp.mc.player.getHeldItemMainhand().getItem() instanceof ItemExpBottle) {
            CPacketPlayer packet = (CPacketPlayer) e.getPacket();
            ((ICPacketPlayer) packet).setPitch(90.0f);
        }
    }
}

