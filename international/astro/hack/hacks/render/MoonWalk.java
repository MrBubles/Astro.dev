/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package international.astro.hack.hacks.render;

import international.astro.hack.Hack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "MoonWalk", description = "Walk like michael jackson", category = Hack.Category.RENDER)
public class MoonWalk
        extends Hack {
    @SubscribeEvent
    public void onUpdate(TickEvent.ClientTickEvent event) {
        if (this.nullCheck()) {
            return;
        }
        for (EntityPlayer p : MoonWalk.mc.world.playerEntities) {
            if (p == null) continue;
            p.limbSwing = 0.0f;
            p.limbSwingAmount = 0.0f;
            p.prevLimbSwingAmount = 0.0f;
        }
    }
}

