/*
 * Decompiled with CFR 0.150.
 */
package international.astro;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class Discord {
    private static final DiscordRichPresence discordRichPresence = new DiscordRichPresence();

    public static void startRPC() {
        DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        String discordID = "1063228897719746700";
        DiscordRPC.INSTANCE.Discord_Initialize(discordID, eventHandlers, true, null);
        Discord.discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        Discord.discordRichPresence.details = "https://discord.gg/sRgsXnAK";
        Discord.discordRichPresence.largeImageKey = "large";
        Discord.discordRichPresence.state = null;
        DiscordRPC.INSTANCE.Discord_UpdatePresence(discordRichPresence);
    }

    public static void stopRPC() {
        DiscordRPC.INSTANCE.Discord_Shutdown();
        DiscordRPC.INSTANCE.Discord_ClearPresence();
    }
}

