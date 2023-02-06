/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.item.Item
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package international.astro.hack.hacks.combat;

import international.astro.hack.Hack;
import international.astro.hack.option.options.OList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "AutoTotem", description = "self fill", category = Hack.Category.COMBAT)
public class AutoTotem
        extends Hack {
    OList mode = new OList("Hand:", "Off", "Main", "Off");
    private boolean switching = false;
    private int lastSlot;

    public AutoTotem() {
        this.addOption(this.mode);
    }

    @Override
    public void onEnable() {
        if (this.nullCheck()) {
            return;
        }
    }

    @Override
    public void onDisable() {
        if (this.nullCheck()) {
            return;
        }
    }

    @SubscribeEvent
    public void onUpdate(TickEvent.ClientTickEvent event) {
        if (this.nullCheck()) {
            return;
        }
        if (this.switching) {
            this.swapTotem(this.lastSlot, 2);
            return;
        }
        if (this.mode.getMode().equalsIgnoreCase("main") && AutoTotem.mc.player.getHeldItemOffhand().getItem() == Items.AIR) {
            this.swapTotem(this.getTotem(), 0);
        }
    }

    private int getTotem() {
        if (Items.TOTEM_OF_UNDYING == AutoTotem.mc.player.getHeldItemOffhand().getItem()) {
            return -1;
        }
        for (int i = 45; i >= 0; --i) {
            Item item = AutoTotem.mc.player.inventory.getStackInSlot(i).getItem();
            if (item != Items.TOTEM_OF_UNDYING) continue;
            if (i < 9) {
                return -1;
            }
            return i;
        }
        return -1;
    }

    public void swapTotem(int slot, int step) {
        if (slot == -1) {
            return;
        }
        if (step == 0) {
            AutoTotem.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer) AutoTotem.mc.player);
            AutoTotem.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer) AutoTotem.mc.player);
            AutoTotem.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer) AutoTotem.mc.player);
        }
        if (step == 1) {
            AutoTotem.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer) AutoTotem.mc.player);
            this.switching = true;
            this.lastSlot = slot;
        }
        if (step == 2) {
            AutoTotem.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer) AutoTotem.mc.player);
            AutoTotem.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer) AutoTotem.mc.player);
            this.switching = false;
        }
        AutoTotem.mc.playerController.updateController();
    }
}

