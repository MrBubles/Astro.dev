/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package international.astro.command;

import net.minecraft.client.Minecraft;

public class Command {
    public static Minecraft mc = Minecraft.getMinecraft();
    private String name;
    private String[] alias;

    public Command(String name, String[] alias) {
        this.setName(name);
        this.setAlias(alias);
    }

    public void onTrigger(String arguments) {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAlias() {
        return this.alias;
    }

    public void setAlias(String[] alias) {
        this.alias = alias;
    }

    public void sendMsg(String s) {
        Command.mc.player.sendChatMessage("[Astro.dev]" + s);
    }
}

