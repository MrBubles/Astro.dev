/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.client.event.EntityViewRenderEvent$CameraSetup
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent
 */
package international.astro.hack.hacks.render;

import international.astro.events.TurnEvent;
import international.astro.hack.Hack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "FreeLook", description = "F5 but better", category = Hack.Category.RENDER)
public class FreeLook
        extends Hack {
    private float dYaw;
    private float dPitch;

    @SubscribeEvent
    public void onTick(TickEvent e) {
        if (this.nullCheck()) {
            return;
        }
        if (FreeLook.mc.gameSettings.thirdPersonView != 1) {
            this.disable();
        }
    }

    @Override
    public void onEnable() {
        this.dYaw = 0.0f;
        this.dPitch = 0.0f;
        FreeLook.mc.gameSettings.thirdPersonView = 1;
        super.onEnable();
    }

    @SubscribeEvent
    public void cameraSetup(EntityViewRenderEvent.CameraSetup event) {
        if (this.nullCheck()) {
            return;
        }
        if (FreeLook.mc.gameSettings.thirdPersonView > 0) {
            event.setYaw(event.getYaw() + this.dYaw);
            event.setPitch(event.getPitch() + this.dPitch);
        }
    }

    @Override
    public void onDisable() {
        FreeLook.mc.gameSettings.thirdPersonView = 0;
        super.onDisable();
    }

    @SubscribeEvent
    public void onTurn(TurnEvent event) {
        if (FreeLook.mc.gameSettings.thirdPersonView > 0) {
            this.dYaw = (float) ((double) this.dYaw + (double) event.getYaw() * 0.15);
            this.dPitch = (float) ((double) this.dPitch - (double) event.getPitch() * 0.15);
            this.dPitch = MathHelper.clamp((float) this.dPitch, (float) -90.0f, (float) 90.0f);
            event.setCanceled(true);
        }
    }
}

