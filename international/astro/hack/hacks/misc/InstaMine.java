/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent
 */
package international.astro.hack.hacks.misc;

import international.astro.Astro;
import international.astro.events.DamageBlockEvent;
import international.astro.events.PacketSendEvent;
import international.astro.hack.Hack;
import international.astro.util.PlayerUtil;
import international.astro.util.RenderUtils;
import international.astro.util.TimerUtil;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "InstaMine", description = "best instant mine", category = Hack.Category.MISC)
public class InstaMine
        extends Hack {
    double manxi;
    Block block;
    TimerUtil breakSuccess = new TimerUtil();
    boolean empty = false;
    TimerUtil Rendertimer = new TimerUtil();
    EnumFacing facing;
    List<Block> blacklist = Arrays.asList(new Block[]{Blocks.AIR, Blocks.FLOWING_LAVA, Blocks.LAVA, Blocks.FLOWING_WATER, Blocks.WATER, Blocks.BEDROCK, Blocks.END_PORTAL_FRAME, Blocks.PORTAL});
    BlockPos breakPos;
    BlockPos breakPos2;
    boolean cancelStart = false;

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent renderWorldLastEvent) {
        if (this.cancelStart && !this.nullCheck()) {
            if (this.blacklist.contains((Object) InstaMine.mc.world.getBlockState(this.breakPos).getBlock())) {
                this.empty = true;
            }
            if ((this.breakPos != null || InstaMine.mc.world.getBlockState(this.breakPos).getBlock() == Blocks.AIR) && InstaMine.mc.player != null && InstaMine.mc.player.getDistanceSq(this.breakPos) > 65536.0) {
                this.breakPos = null;
                this.breakPos2 = null;
                this.cancelStart = false;
                return;
            }
            float n = InstaMine.mc.world.getBlockState(this.breakPos).getBlockHardness((World) InstaMine.mc.world, this.breakPos) / 5.0f;
            if (InstaMine.mc.world.getBlockState(this.breakPos).getBlock() == Blocks.OBSIDIAN) {
                n = 11.0f;
            }
            if (this.Rendertimer.passedMs((int) n)) {
                if (this.manxi <= 10.0) {
                    this.manxi += 0.11;
                }
                this.Rendertimer.reset();
            }
            RenderUtils.drawBoxESP(this.breakPos, Astro.colorManager.getColor(), 1.0f, true, true, 100);
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent e) {
        if (this.nullCheck()) {
            return;
        }
        if (InstaMine.mc.player.capabilities.isCreativeMode) {
            return;
        }
        if (!this.cancelStart) {
            return;
        }
        if (this.breakPos != null && InstaMine.mc.player != null && InstaMine.mc.player.getDistanceSq(this.breakPos) > 65536.0) {
            this.breakPos = null;
            this.breakPos2 = null;
            this.cancelStart = false;
            return;
        }
        if (this.blacklist.contains((Object) InstaMine.mc.world.getBlockState(this.breakPos).getBlock())) {
            return;
        }
        if (this.breakPos != null) {
            float getBlockHardness = InstaMine.mc.world.getBlockState(this.breakPos).getBlockHardness((World) InstaMine.mc.world, this.breakPos);
            int currentItem2 = InstaMine.mc.player.inventory.currentItem;
            if (!this.breakSuccess.passedMs((int) getBlockHardness)) {
                return;
            }
            try {
                this.block = InstaMine.mc.world.getBlockState(this.breakPos).getBlock();
            } catch (Exception exception) {
                // empty catch block
            }
            int bestAvailableToolSlot = PlayerUtil.getBestAvailableToolSlot(this.block.getBlockState().getBaseState());
            if (InstaMine.mc.player.inventory.currentItem != bestAvailableToolSlot && bestAvailableToolSlot != -1) {
                InstaMine.mc.player.inventory.currentItem = bestAvailableToolSlot;
                InstaMine.mc.playerController.updateController();
                InstaMine.mc.player.connection.sendPacket((Packet) new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.breakPos, this.facing));
                InstaMine.mc.player.inventory.currentItem = currentItem2;
                InstaMine.mc.playerController.updateController();
                return;
            }
        }
    }

    @SubscribeEvent
    public void onPacketSend(PacketSendEvent e) {
        if (this.nullCheck()) {
            return;
        }
        if (InstaMine.mc.player.capabilities.isCreativeMode) {
            return;
        }
        if (e.getPacket() instanceof CPacketPlayerDigging && ((CPacketPlayerDigging) e.getPacket()).getAction() == CPacketPlayerDigging.Action.START_DESTROY_BLOCK) {
            e.setCanceled(this.cancelStart);
        }
    }

    public boolean canBreak(BlockPos blockPos) {
        IBlockState getBlockState = InstaMine.mc.world.getBlockState(blockPos);
        return getBlockState.getBlock().getBlockHardness(getBlockState, (World) InstaMine.mc.world, blockPos) != -1.0f;
    }

    @SubscribeEvent
    public void onBlocDamagekEvent(DamageBlockEvent damageBlockEvent) {
        if (this.nullCheck() || InstaMine.mc.player.capabilities.isCreativeMode) {
            return;
        }
        if (this.breakPos != null && this.breakPos.getX() == damageBlockEvent.getPos().getX() && this.breakPos.getY() == damageBlockEvent.getPos().getY() && this.breakPos.getZ() == damageBlockEvent.getPos().getZ()) {
            return;
        }
        if (this.canBreak(damageBlockEvent.pos)) {
            this.manxi = 0.0;
            this.breakPos2 = this.breakPos;
            this.empty = false;
            this.cancelStart = false;
            this.breakPos = damageBlockEvent.pos;
            this.breakSuccess.reset();
            this.facing = damageBlockEvent.facing;
            if (this.breakPos != null) {
                InstaMine.mc.player.swingArm(EnumHand.MAIN_HAND);
                InstaMine.mc.player.connection.sendPacket((Packet) new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.breakPos, this.facing));
                this.cancelStart = true;
                InstaMine.mc.player.connection.sendPacket((Packet) new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.breakPos, this.facing));
                damageBlockEvent.setCanceled(true);
            }
        }
    }
}

