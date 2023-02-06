/*
 * Decompiled with CFR 0.150.
 */
package international.astro.hack.hacks.client;

import international.astro.hack.Hack;
import international.astro.util.DiscordWebhook;

import java.awt.Color;
import java.io.IOException;

@Hack.Construct(name = "BackupCaller", description = "calls for backup", category = Hack.Category.CLIENT)
public class BackupCaller
        extends Hack {
    DiscordWebhook discordWebhook = new DiscordWebhook("https://discord.com/api/webhooks/1066498013826125864/kRMhYcBRds2hgBwQXs71sdpr2ausTZgRnsUiuofe9zqO4YIXGTe3SOGMSD0w4sfSiZLQ");

    @Override
    public void onEnable() {
        try {
            this.discordWebhook.setContent("<@&1068683247069970452>");
            this.discordWebhook.addEmbed(new DiscordWebhook.EmbedObject().setTitle(BackupCaller.mc.player.getName() + " Requested Backup").setColor(Color.RED).addField("Server", this.serverCheck(), true).addField("Coords", "X: " + Math.round(BackupCaller.mc.player.posX) + " Y: " + Math.round(BackupCaller.mc.player.posY) + " Z: " + Math.round(BackupCaller.mc.player.posZ), true).addField("Dimension", this.dimensionCheck(), true));
            this.discordWebhook.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.toggle();
    }

    public String serverCheck() {
        String ip = mc.isSingleplayer() ? "SinglePlayer" : BackupCaller.mc.getCurrentServerData().serverIP;
        return ip;
    }

    public String dimensionCheck() {
        int dimension = BackupCaller.mc.player.dimension;
        String dim = dimension == 0 ? "OverWorld" : (dimension == -1 ? "Nether" : "End");
        return dim;
    }
}

