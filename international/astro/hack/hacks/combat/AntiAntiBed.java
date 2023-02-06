/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package international.astro.hack.hacks.combat;

import international.astro.hack.Hack;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "AntiAntiBed", description = "FUCKING STRING", category = Hack.Category.COMBAT)
public class AntiAntiBed
        extends Hack {
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (this.nullCheck()) {
            return;
        }
        for (TileEntity string : AntiAntiBed.mc.world.loadedTileEntityList) {
            if (string.getBlockType() != Block.getBlockFromItem((Item) Items.STRING)) continue;
            if (string.isInvalid()) {
                this.disable();
            }
            for (int i = 0; i < 6; ++i) {
                AntiAntiBed.mc.player.connection.sendPacket((Packet) new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, string.getPos(), EnumFacing.DOWN));
            }
        }
    }
}

