/*
 * Decompiled with CFR 0.150.
 */
package international.astro.command.commands;

import international.astro.command.Command;

public class TableFlipCommand
        extends Command {
    private final String LENNY = "(\u256f\u00b0\u25a1\u00b0)\u256f\ufe35 \u253b\u2501\u253b";

    public TableFlipCommand(String name, String[] alias) {
        super(name, alias);
    }

    @Override
    public void onTrigger(String arguments) {
        TableFlipCommand.mc.player.sendChatMessage("(\u256f\u00b0\u25a1\u00b0)\u256f\ufe35 \u253b\u2501\u253b");
        super.onTrigger(arguments);
    }
}

