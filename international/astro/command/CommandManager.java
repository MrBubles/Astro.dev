/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package international.astro.command;

import international.astro.Astro;
import international.astro.command.Command;
import international.astro.command.commands.BindCommand;
import international.astro.command.commands.HelpCommand;
import international.astro.command.commands.LennyCommand;
import international.astro.command.commands.PrefixCommand;
import international.astro.command.commands.SoundCommand;
import international.astro.command.commands.TableFlipCommand;
import international.astro.command.commands.ToggleCommand;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;

public class CommandManager {
    public static Minecraft mc = Minecraft.getMinecraft();
    private final ArrayList<Command> commands = new ArrayList();
    public String prefix = "@";

    public CommandManager() {
        this.commands.add(new HelpCommand("Help", new String[]{"help"}));
        this.commands.add(new PrefixCommand("Prefix", new String[]{"prefix"}));
        this.commands.add(new BindCommand("Bind", new String[]{"bind"}));
        this.commands.add(new LennyCommand("Lenny", new String[]{"lenny"}));
        this.commands.add(new ToggleCommand("Toggle", new String[]{"toggle"}));
        this.commands.add(new TableFlipCommand("Tableflip", new String[]{"tableflip"}));
        this.commands.add(new SoundCommand("Sound", new String[]{"sound", "snd"}));
    }

    public void runCommand(String args) {
        boolean found = false;
        String[] split = args.split(" ");
        String startCommand = split[0];
        String arguments = args.substring(startCommand.length()).trim();
        for (Command command : this.getCommands()) {
            for (String alias : command.getAlias()) {
                if (!startCommand.equals(this.getPrefix() + alias)) continue;
                command.onTrigger(arguments);
                found = true;
            }
        }
        if (!found) {
            Astro.sendMsg("Unknown command");
        }
    }

    public ArrayList<Command> getCommands() {
        return this.commands;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}

