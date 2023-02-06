/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package international.astro.hack.hacks.combat;

import international.astro.events.AttackEvent;
import international.astro.events.PacketSendEvent;
import international.astro.hack.Hack;
import international.astro.hack.option.options.OList;
import international.astro.mixins.accessor.ICPacketPlayer;
import international.astro.mixins.accessor.IEntityPlayerSP;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Hack.Construct(name = "Criticals", description = "hit harder", category = Hack.Category.COMBAT)
public class Crits
        extends Hack {
    OList mode = new OList("Mode", "2b2t", "2b2t");
    double spoofedY = -1337.0;

    public Crits() {
        this.addOption(this.mode);
    }

    @SubscribeEvent
    public void onAttack(AttackEvent e) {
        if (this.mode.isMode("2b2t")) {
            ((IEntityPlayerSP) Crits.mc.player).setLastReportedPosY(-1337.0);
        }
    }

    @SubscribeEvent
    public void onSend(PacketSendEvent e) {
        if (this.mode.isMode("2b2t") && e.getPacket() instanceof CPacketPlayer) {
            CPacketPlayer p = (CPacketPlayer) e.getPacket();
            this.spoofedY = Crits.mc.player.onGround ? (this.spoofedY <= 0.0 ? 0.01 : (this.spoofedY -= 1.0E-5)) : -1337.0;
            ((ICPacketPlayer) p).setMoving(true);
            ((ICPacketPlayer) p).setOnGround(false);
            if (this.spoofedY >= 0.0) {
                ((ICPacketPlayer) p).setY(this.spoofedY += p.getY(Crits.mc.player.posY));
            }
        }
    }
}

