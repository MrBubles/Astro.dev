/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.item.ItemPotion
 *  net.minecraft.item.ItemStack
 */
package international.astro.hack.hacks.combat;

import international.astro.hack.Hack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;

public class SplashPotRefill
        extends Hack {

    @Hack.Construct(name = "SplashPotRefill", description = "self fill", category = Hack.Category.COMBAT)
    public static class splashPotRefill
            extends Hack {
        public static void onUpdate() {
            if (!splashPotRefill.mc.player.inventory.getCurrentItem().getItem().equals((Object) Items.SPLASH_POTION)) {
                for (int i = 0; i < 45; ++i) {
                    ItemStack itemStack = splashPotRefill.mc.player.openContainer.getSlot(i).getStack();
                    if (itemStack == ItemStack.EMPTY) continue;
                    ItemPotion itemMine = Items.SPLASH_POTION;
                    if (splashPotRefill.mc.player.getHeldItemMainhand().isEmpty()) continue;
                    splashPotRefill.mc.playerController.windowClick(0, i, 1, ClickType.PICKUP, (EntityPlayer) splashPotRefill.mc.player);
                    splashPotRefill.mc.playerController.windowClick(0, 36, 1, ClickType.PICKUP, (EntityPlayer) splashPotRefill.mc.player);
                }
            }
        }
    }
}

