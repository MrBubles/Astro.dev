/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3i
 */
package international.astro.hack.hacks.combat;

import international.astro.hack.Hack;
import international.astro.util.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

@Hack.Construct(name = "SelfWeb", description = "web urself", category = Hack.Category.COMBAT)
public class SelfWeb
        extends Hack {
    @Override
    public void onEnable() {
        if (PlayerUtil.getSlot(Item.getItemFromBlock((Block) Blocks.WEB)) != -1) {
            int prevSlot = SelfWeb.mc.player.inventory.currentItem;
            PlayerUtil.changeSlot(PlayerUtil.getSlot(Item.getItemFromBlock((Block) Blocks.WEB)));
            PlayerUtil.placeBlock(new BlockPos((Vec3i) PlayerUtil.getPosFloored((EntityPlayer) SelfWeb.mc.player)), EnumHand.MAIN_HAND, false);
            SelfWeb.mc.player.connection.sendPacket((Packet) new CPacketHeldItemChange(prevSlot));
        }
        this.setEnabled(false);
        super.onEnable();
    }
}

