/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package international.astro.hack.hacks.combat;

import international.astro.hack.Hack;
import international.astro.hack.option.options.OBoolean;
import international.astro.hack.option.options.OList;
import international.astro.util.PlayerUtil;
import international.astro.util.TimerUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "Burrow", description = "self fill", category = Hack.Category.COMBAT)
public class Burrow
        extends Hack {
    OBoolean aSwitch = new OBoolean("AutoSwitch", true);
    OBoolean sneak = new OBoolean("Sneak", true);
    OList mode = new OList("Mode", "5b5t", "5b5t", "Normal");
    int oldSlot;

    public Burrow() {
        this.addOption(this.sneak);
        this.addOption(this.mode);
        this.addOption(this.aSwitch);
    }

    @Override
    public void onEnable() {
        if (this.nullCheck()) {
            return;
        }
        this.oldSlot = Burrow.mc.player.inventory.currentItem;
        if (Burrow.mc.player.onGround) {
            TimerUtil timer = new TimerUtil();
            if (this.aSwitch.isEnabled()) {
                int blockSlot = PlayerUtil.getSlot(Item.getItemFromBlock((Block) Blocks.OBSIDIAN));
                Burrow.mc.player.connection.sendPacket((Packet) new CPacketHeldItemChange(blockSlot));
            }
            if (timer.sleep(100L)) {
                if (this.mode.isMode("5b5t") || this.mode.isMode("Normal")) {
                    BlockPos pos = PlayerUtil.getPosFloored((EntityPlayer) Burrow.mc.player);
                    Burrow.mc.player.connection.sendPacket((Packet) new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 0.41999998688698, Burrow.mc.player.posZ, true));
                    Burrow.mc.player.connection.sendPacket((Packet) new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 0.7531999805211997, Burrow.mc.player.posZ, true));
                    Burrow.mc.player.connection.sendPacket((Packet) new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.00133597911214, Burrow.mc.player.posZ, true));
                    Burrow.mc.player.connection.sendPacket((Packet) new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.16610926093821, Burrow.mc.player.posZ, true));
                    PlayerUtil.placeBlock(pos, EnumHand.MAIN_HAND, true);
                    Burrow.mc.player.connection.sendPacket((Packet) new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.5, Burrow.mc.player.posZ, false));
                    if (this.sneak.isEnabled() && !Burrow.mc.player.isSneaking()) {
                        Burrow.mc.player.connection.sendPacket((Packet) new CPacketEntityAction((Entity) Burrow.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                        Burrow.mc.player.setSneaking(true);
                        Burrow.mc.playerController.updateController();
                        Burrow.mc.player.connection.sendPacket((Packet) new CPacketEntityAction((Entity) Burrow.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                        Burrow.mc.player.setSneaking(false);
                        Burrow.mc.playerController.updateController();
                    }
                }
                timer.reset();
            }
            this.toggle();
        }
    }

    @Override
    public void onDisable() {
        Burrow.mc.player.connection.sendPacket((Packet) new CPacketHeldItemChange(this.oldSlot));
    }

    @SubscribeEvent
    public void onUpdate(TickEvent.ClientTickEvent event) {
        if (this.nullCheck()) {
            return;
        }
        if (this.mode.isMode("5b5t")) {
            Burrow.mc.player.connection.sendPacket((Packet) new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY - 0.02523, Burrow.mc.player.posZ, true));
        }
    }
}

