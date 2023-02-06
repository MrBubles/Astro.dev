/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketChatMessage
 *  net.minecraftforge.client.event.ClientChatReceivedEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package international.astro.hack.hacks.misc;

import international.astro.hack.Hack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Hack.Construct(name = "AutoIgnore", description = "mutes everyone that speaks", category = Hack.Category.MISC)
public class AutoIgnore
        extends Hack {
    public static AutoIgnore getInstance = new AutoIgnore();

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent eve) {
        if (this.nullCheck()) {
            return;
        }
        AutoIgnore.mc.player.connection.sendPacket((Packet) new CPacketChatMessage("/ignore " + eve.getType().name()));
    }
}

