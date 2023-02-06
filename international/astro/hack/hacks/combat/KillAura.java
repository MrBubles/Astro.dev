/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.util.EnumHand
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package international.astro.hack.hacks.combat;

import international.astro.events.PacketSendEvent;
import international.astro.hack.Hack;
import international.astro.hack.option.options.ODouble;
import international.astro.hack.option.options.OList;
import international.astro.mixins.accessor.ICPacketPlayer;
import international.astro.util.PlayerUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Hack.Construct(name = "KillAura", description = "killing aura(real)", category = Hack.Category.COMBAT)
public class KillAura
        extends Hack {
    OList mode = new OList("Mode", "Closest", "Multi", "Closest");
    ODouble range = new ODouble("Range", 4.0, 7.0, 1.0, 5.0);
    int delay = 0;
    float yaw;
    float pitch;

    public KillAura() {
        this.addOption(this.mode);
        this.addOption(this.range);
    }

    @Override
    public void onDisable() {
    }

    @SubscribeEvent
    public void onUpdate(TickEvent.ClientTickEvent event) {
        if (this.nullCheck()) {
            return;
        }
        if (KillAura.mc.player.getCooledAttackStrength(0.0f) == 1.0f) {
            ++this.delay;
            if (this.delay >= 20) {
                if (this.mode.getMode().equalsIgnoreCase("Multi")) {
                    this.attackTargetMulti();
                } else {
                    this.attackTargetClosest();
                }
            }
        }
    }

    public void attackTargetClosest() {
        List closestPlayer = KillAura.mc.world.playerEntities.stream().filter(player -> player.getDistance((Entity) KillAura.mc.player) < (float) this.range.getIntValue() && player != KillAura.mc.player && player.isEntityAlive()).collect(Collectors.toList());
        closestPlayer.sort(Comparator.comparingDouble(player -> player.getDistanceSq((Entity) KillAura.mc.player)));
        if (!closestPlayer.isEmpty()) {
            EntityPlayer p = (EntityPlayer) closestPlayer.get(0);
            this.yaw = PlayerUtil.getRotations((Entity) p)[0];
            this.pitch = PlayerUtil.getRotations((Entity) p)[1];
            KillAura.mc.playerController.attackEntity((EntityPlayer) KillAura.mc.player, (Entity) p);
            KillAura.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    private void attackTargetMulti() {
        for (Entity p : KillAura.mc.world.loadedEntityList) {
            if (!(p instanceof EntityPlayer) || p == KillAura.mc.player || !((double) KillAura.mc.player.getDistance(p) <= this.range.getValue()))
                continue;
            this.yaw = PlayerUtil.getRotations(p)[0];
            this.pitch = PlayerUtil.getRotations(p)[1];
            KillAura.mc.playerController.attackEntity((EntityPlayer) KillAura.mc.player, p);
            KillAura.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    @SubscribeEvent
    public void onUpdate(PacketSendEvent event) {
        if (this.nullCheck()) {
            return;
        }
        if (event.getPacket() instanceof CPacketPlayer) {
            ((ICPacketPlayer) event.getPacket()).setYaw(this.yaw);
            ((ICPacketPlayer) event.getPacket()).setPitch(this.pitch);
        }
    }
}

