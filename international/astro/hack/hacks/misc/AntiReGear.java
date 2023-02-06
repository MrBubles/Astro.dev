/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.block.BlockShulkerBox
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent
 */
package international.astro.hack.hacks.misc;

import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
import international.astro.hack.option.options.ODouble;
import international.astro.util.PlayerUtil;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockShulkerBox;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "AntiReGear", description = "Stop them chink fags from re-gearing", category = Hack.Category.MISC)
public class AntiReGear
        extends Hack {
    public ODouble range = new ODouble("Range", 1.0, 6.0, 1.0, 4.0);
    public OBoolean silent = new OBoolean("SilentSwitch", true);

    public AntiReGear() {
        this.addOption(this.range);
        this.addOption(this.silent);
    }

    @SubscribeEvent
    public void onTick(TickEvent e) {
        if (this.nullCheck()) {
            return;
        }
        List<BlockPos> bubble = PlayerUtil.getSphere(new BlockPos(Math.floor(AntiReGear.mc.player.posX), Math.floor(AntiReGear.mc.player.posY), Math.floor(AntiReGear.mc.player.posZ)), this.range.getFloatValue(), 0, false, true, 0);
        ArrayList<BlockPos> shulkList = new ArrayList<BlockPos>();
        for (BlockPos pos : bubble) {
            if (!(AntiReGear.mc.world.getBlockState(pos).getBlock() instanceof BlockShulkerBox)) continue;
            shulkList.add(pos);
        }
        for (BlockPos shulk : shulkList) {
            int tool = PlayerUtil.getBestAvailableToolSlot(AntiReGear.mc.player.world.getBlockState(shulk));
            if (this.silent.isEnabled()) {
                AntiReGear.mc.player.connection.sendPacket((Packet) new CPacketHeldItemChange(tool));
                AntiReGear.mc.playerController.updateController();
            } else {
                AntiReGear.mc.player.connection.sendPacket((Packet) new CPacketHeldItemChange(tool));
                AntiReGear.mc.player.inventory.currentItem = tool;
                AntiReGear.mc.playerController.updateController();
            }
            AntiReGear.mc.player.swingArm(EnumHand.MAIN_HAND);
            AntiReGear.mc.playerController.onPlayerDamageBlock(shulk, PlayerUtil.getRayTraceFacing(shulk));
        }
    }
}

