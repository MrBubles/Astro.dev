/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Post
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package international.astro.hack.hacks.client;

import international.astro.Astro;
import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Hack.Construct(name = "HUD", description = "hud thingy", category = Hack.Category.CLIENT)
public class HUD
        extends Hack {
    ODouble wx = new ODouble("WaterMarkX", 0.0, 500.0, 1.0, 0.0);
    ODouble wy = new ODouble("WaterMarkY", 0.0, 500.0, 1.0, 0.0);

    public HUD() {
        this.addOption(this.wx);
        this.addOption(this.wy);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post e) {
        if (this.nullCheck()) {
            return;
        }
        ScaledResolution sr = new ScaledResolution(mc);
        if (e.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            String watermark = "Astro.dev b2.2.5";
            if (Astro.hackManager.getHack("CustomFont").isEnabled()) {
                Astro.font.drawString(watermark, this.wx.getFloatValue(), this.wy.getFloatValue(), Astro.colorManager.getRGBA());
            } else {
                HUD.mc.fontRenderer.drawStringWithShadow(watermark, (float) this.wx.getIntValue(), (float) this.wy.getIntValue(), Astro.colorManager.getRGBA());
            }
            int y = 2;
            ArrayList<String> list = new ArrayList<String>();
            for (Hack hack : Astro.hackManager.getEnabledHacks()) {
                list.add(hack.getName());
            }
            list.sort(Comparator.comparingInt(s -> Astro.font.getStringWidth((String) s)));
            Collections.reverse(list);
            for (String name : list) {
                if (Astro.hackManager.getHack("CustomFont").isEnabled()) {
                    Astro.font.drawString(name, (float) ((double) (sr.getScaledWidth() - Astro.font.getStringWidth(name)) - 1.5), y + 2, Astro.colorManager.getRGBA());
                } else {
                    HUD.mc.fontRenderer.drawStringWithShadow(name, (float) (sr.getScaledWidth() - Astro.font.getStringWidth(name) - 3), (float) (y + 2), Astro.colorManager.getRGBA());
                }
                y += 10;
            }
        }
    }
}

