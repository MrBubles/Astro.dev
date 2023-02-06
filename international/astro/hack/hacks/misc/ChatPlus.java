/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.network.play.server.SPacketChat
 *  net.minecraftforge.client.event.ClientChatEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package international.astro.hack.hacks.misc;

import international.astro.events.PacketReceiveEvent;
import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;

import java.util.Random;

import net.minecraft.network.play.server.SPacketChat;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Hack.Construct(name = "Chatplus", description = "Chat but better", category = Hack.Category.MISC)
public class ChatPlus
        extends Hack {
    public static ChatPlus getInstance = new ChatPlus();
    public OBoolean chatSuffix = new OBoolean("ChatSuffix", true);
    public OBoolean spamBypass = new OBoolean("SpamBypass", true);
    public OBoolean greenText = new OBoolean("GreenText", true);
    public OBoolean clearChat = new OBoolean("ClearChat", true);
    public OBoolean portalChat = new OBoolean("PortalChat", true);
    public OBoolean antiChatLag = new OBoolean("AnitiChatLag", true);
    public OBoolean RedText = new OBoolean("RedText", false);
    public OBoolean YellowText = new OBoolean("YellowText", false);
    public OBoolean BlueText = new OBoolean("BlueText", false);

    public ChatPlus() {
        this.addOption(this.chatSuffix);
        this.addOption(this.spamBypass);
        this.addOption(this.greenText);
        this.addOption(this.clearChat);
        this.addOption(this.portalChat);
        this.addOption(this.antiChatLag);
        this.addOption(this.RedText);
        this.addOption(this.YellowText);
        this.addOption(this.BlueText);
    }

    @SubscribeEvent
    public void onReceive(PacketReceiveEvent e) {
        if (this.antiChatLag.isEnabled() && e.getPacket() instanceof SPacketChat) {
            String text = ((SPacketChat) e.getPacket()).getChatComponent().getFormattedText();
            int symbolCount = 0;
            for (int i = 0; i < text.length(); ++i) {
                char c = text.charAt(i);
                if (this.isSymbol(c)) {
                    ++symbolCount;
                }
                if (symbolCount <= 100) continue;
                e.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent e) {
        if (this.nullCheck()) {
            return;
        }
        Random random = new Random();
        int nextInt = random.nextInt(0x1000000);
        String hex = String.format("#%06x", nextInt);
        if (e.getMessage().startsWith("/") || e.getMessage().startsWith(".") || e.getMessage().startsWith(",") || e.getMessage().startsWith("-") || e.getMessage().startsWith("$") || e.getMessage().startsWith("*") || e.getMessage().startsWith("!") || e.getMessage().startsWith("?") || e.getMessage().startsWith("+") || e.getMessage().startsWith("@") || e.getMessage().startsWith("~")) {
            return;
        }
        if (this.chatSuffix.isEnabled()) {
            e.setMessage(e.getMessage() + " | [Astro.dev]");
        }
        if (this.spamBypass.isEnabled()) {
            e.setMessage(e.getMessage() + "[" + hex + "]");
        }
        if (this.greenText.isEnabled()) {
            e.setMessage("> " + e.getMessage());
        }
        if (this.RedText.isEnabled()) {
            e.setMessage("< " + e.getMessage());
        }
        if (this.YellowText.isEnabled()) {
            e.setMessage("! " + e.getMessage());
        }
        if (this.BlueText.isEnabled()) {
            e.setMessage("` " + e.getMessage());
        }
    }

    private boolean isSymbol(char charIn) {
        return !(charIn >= 'A' && charIn <= 'Z' || charIn >= 'a' && charIn <= 'z' || charIn >= '0' && charIn <= '9');
    }
}

