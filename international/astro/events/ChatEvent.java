/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.client.event.ClientChatEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package international.astro.events;

import international.astro.Astro;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatEvent {
    private Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onChatSend(ClientChatEvent event) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (event.getMessage().startsWith(Astro.commandManager.getPrefix())) {
            event.setCanceled(true);
            Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
            Astro.commandManager.runCommand(event.getMessage());
        }
    }
}

