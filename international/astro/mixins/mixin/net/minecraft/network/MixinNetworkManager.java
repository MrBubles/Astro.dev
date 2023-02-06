/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketPlayer$PositionRotation
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package international.astro.mixins.mixin.net.minecraft.network;

import international.astro.events.PacketReceiveEvent;
import international.astro.events.PacketSendEvent;
import international.astro.mixins.accessor.ICPacketPlayer;
import international.astro.util.RotationManager;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {NetworkManager.class})
public class MixinNetworkManager {
    @Inject(method = {"sendPacket(Lnet/minecraft/network/Packet;)V"}, at = {@At(value = "HEAD")}, cancellable = true)
    private void onPacketSend(Packet<?> packet, CallbackInfo ci) {
        PacketSendEvent event = new PacketSendEvent(packet);
        MinecraftForge.EVENT_BUS.post((Event) event);
        if (event.isCanceled()) {
            ci.cancel();
        }
        if (event.getPacket() instanceof CPacketPlayer.Rotation || event.getPacket() instanceof CPacketPlayer.PositionRotation) {
            if (RotationManager.isRotating()) {
                CPacketPlayer p = (CPacketPlayer) event.getPacket();
                ((ICPacketPlayer) p).setYaw(RotationManager.getYaw());
                ((ICPacketPlayer) p).setPitch(RotationManager.getPitch());
            }
        }
    }

    @Inject(method = {"channelRead0"}, at = {@At(value = "HEAD")}, cancellable = true)
    public void channelRead0(ChannelHandlerContext p_channelRead0_1_, Packet<?> p_channelRead0_2_, CallbackInfo ci) {
        PacketReceiveEvent event = new PacketReceiveEvent(p_channelRead0_2_);
        MinecraftForge.EVENT_BUS.post((Event) event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }
}

