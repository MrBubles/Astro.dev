/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package international.astro.hack.hacks.combat;

import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
import international.astro.hack.option.options.ODouble;
import international.astro.util.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "HoleFill", description = "fill holes", category = Hack.Category.COMBAT)
public class HoleFill
        extends Hack {
    public static ODouble Range = new ODouble("Range", 2.0, 10.0, 1.0, 5.0);
    public static OBoolean PacketPlace = new OBoolean("PacketPlace", false);

    public HoleFill() {
        this.addOption(Range);
        this.addOption(PacketPlace);
    }

    public boolean FemboyHoleIsFillable(BlockPos p) {
        return HoleFill.mc.world.getBlockState(p).getBlock() == Blocks.AIR && HoleFill.mc.world.getBlockState(p.north()).getBlock() != Blocks.AIR && HoleFill.mc.world.getBlockState(p.east()).getBlock() != Blocks.AIR && HoleFill.mc.world.getBlockState(p.south()).getBlock() != Blocks.AIR && HoleFill.mc.world.getBlockState(p.west()).getBlock() != Blocks.AIR && p != HoleFill.mc.player.getPosition();
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        if (this.nullCheck()) {
            return;
        }
        for (TileEntity cock : HoleFill.mc.world.loadedTileEntityList) {
            if (cock.getBlockType() != Blocks.AIR || !(cock.getDistanceSq(HoleFill.mc.player.posX, HoleFill.mc.player.posY, HoleFill.mc.player.posZ) <= (double) Range.getIntValue()))
                continue;
            if (this.FemboyHoleIsFillable(cock.getPos())) {
                if (PlayerUtil.getSlot(Item.getItemFromBlock((Block) Blocks.OBSIDIAN)) == -1) continue;
                PlayerUtil.changeSlot(PlayerUtil.getSlot(Item.getItemFromBlock((Block) Blocks.OBSIDIAN)));
                PlayerUtil.placeBlock(cock.getPos(), EnumHand.MAIN_HAND, PacketPlace.isEnabled());
                continue;
            }
            this.disable();
        }
    }
}

