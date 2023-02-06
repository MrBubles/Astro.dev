/*
 * Decompiled with CFR 0.150.
 */
package international.astro.command.commands;

import international.astro.command.Command;

public class LennyCommand
        extends Command {
    private final String LENNY = "( \u0361\u00b0 \u035c\u0296 \u0361\u00b0)";

    public LennyCommand(String name, String[] alias) {
        super(name, alias);
    }

    @Override
    public void onTrigger(String arguments) {
        LennyCommand.mc.player.sendChatMessage("( \u0361\u00b0 \u035c\u0296 \u0361\u00b0)");
        super.onTrigger(arguments);
    }
}

