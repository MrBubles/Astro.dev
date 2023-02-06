/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketChatMessage
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package international.astro.hack.hacks.random;

import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;
import international.astro.util.TimerUtil;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "AntiChina", description = "taiwan gang on top", category = Hack.Category.RANDOM)
public class AntiChina
        extends Hack {
    ODouble delay = new ODouble("Delay", 20.0, 500.0, 1.0, 50.0);
    TimerUtil timer = new TimerUtil();

    public AntiChina() {
        this.addOption(this.delay);
    }

    @Override
    public void onEnable() {
        this.timer.reset();
    }

    @Override
    public void onDisable() {
        this.timer.reset();
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (this.nullCheck()) {
            return;
        }
        if (this.timer.passedMs(this.delay.getValue())) {
            Random random = new Random();
            int nextInt = random.nextInt(0x1000000);
            String hex = String.format("#%06x", nextInt);
            ArrayList<String> testicle = new ArrayList<String>();
            testicle.clear();
            testicle.add("> Taiwan owning " + hex);
            testicle.add("> Chinese ppl love little boys " + hex);
            testicle.add("> Taiwan > China" + hex);
            testicle.add("> Xi Jinping < Tsai Ing-wen " + hex);
            testicle.add("> Taiwan owns china" + hex);
            testicle.add("> Chinese players are robot pvper's lel " + hex);
            testicle.add("> Glory to Taiwan " + hex);
            int index = (int) (Math.random() * (double) testicle.size());
            AntiChina.mc.player.connection.sendPacket((Packet) new CPacketChatMessage((String) testicle.get(index)));
            this.timer.reset();
        }
    }
}

