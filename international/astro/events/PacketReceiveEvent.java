/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraftforge.fml.common.eventhandler.Cancelable
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package international.astro.events;

import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class PacketReceiveEvent
        extends Event {
    private Packet p;

    public PacketReceiveEvent(Packet p) {
        this.p = p;
    }

    public Packet getPacket() {
        return this.p;
    }
}

