/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.MovementInput
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.client.event.InputUpdateEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent
 */
package international.astro.hack.hacks.movement;

import international.astro.events.PostMotionEvent;
import international.astro.events.PreMotionEvent;
import international.astro.hack.Hack;
import international.astro.hack.option.options.OList;
import international.astro.util.TimerUtil;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "NoSlow", description = "NoSlowdown", category = Hack.Category.MOVEMENT)
public class NoSlow
        extends Hack {
    OList mode = new OList("Mode:", "Vanilla", "TooBeeTooTea", "Vanilla", "Hypixel");
    TimerUtil timer = new TimerUtil();

    public NoSlow() {
        this.addOption(this.mode);
    }

    @SubscribeEvent
    public void onInput(InputUpdateEvent e) {
        if (this.nullCheck()) {
            return;
        }
        if (this.mode.getMode().equalsIgnoreCase("Vanilla") && NoSlow.mc.player.isHandActive()) {
            MovementInput movementInput = e.getMovementInput();
            movementInput.moveStrafe *= 5.0f;
            movementInput.moveForward *= 5.0f;
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent e) {
        if (this.nullCheck()) {
            return;
        }
        if (this.mode.getMode().equalsIgnoreCase("TooBeeTooTea") && NoSlow.mc.player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() == Items.GOLDEN_APPLE && NoSlow.mc.gameSettings.keyBindUseItem.isKeyDown()) {
            NoSlow.mc.player.connection.sendPacket((Packet) new CPacketHeldItemChange(this.findGappleInHotbar()));
        }
    }

    @SubscribeEvent
    public void onPreMotionEvent(PreMotionEvent e) {
        if (this.nullCheck()) {
            return;
        }
        if (this.mode.isMode("Hypixel")) {
            if (!NoSlow.mc.player.onGround) {
                return;
            }
            if (NoSlow.mc.player.ticksExisted % 2 == 0) {
                NoSlow.mc.player.connection.sendPacket((Packet) new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(-1, -1, -1), EnumFacing.DOWN));
            }
        }
    }

    @SubscribeEvent
    public void onPostMotionEvent(PostMotionEvent e) {
        if (this.nullCheck()) {
            return;
        }
        if (this.mode.isMode("Hypixel")) {
            if (!NoSlow.mc.player.onGround) {
                return;
            }
            if (NoSlow.mc.player.ticksExisted % 2 != 0) {
                NoSlow.mc.player.connection.sendPacket((Packet) new CPacketPlayerTryUseItemOnBlock(new BlockPos(-1, -1, -1), EnumFacing.DOWN, EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            }
        }
    }

    private int findGappleInHotbar() {
        int slot = 0;
        for (int i = 0; i < 9; ++i) {
            if (NoSlow.mc.player.inventory.getStackInSlot(i).getItem() != Items.GOLDEN_APPLE) continue;
            slot = i;
            break;
        }
        return slot;
    }
}

