/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Enchantments
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 */
package international.astro.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class PlayerUtil {
    public static Minecraft mc = Minecraft.getMinecraft();

    public static void changeSlot(int slot) {
        if (slot == -1) {
            return;
        }
        PlayerUtil.mc.player.inventory.currentItem = slot;
    }

    public static BlockPos getPosFloored(EntityPlayer p) {
        return new BlockPos(Math.floor(p.posX), Math.floor(p.posY), Math.floor(p.posZ));
    }

    public static int getSlot(Item items) {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            Item item = PlayerUtil.mc.player.inventory.getStackInSlot(i).getItem();
            if (item != items) continue;
            slot = i;
            break;
        }
        return slot;
    }

    public static boolean placeBlock(BlockPos pos, EnumHand hand, boolean packet) {
        EnumFacing side = PlayerUtil.getFirstFacing(pos);
        if (side == null) {
            return false;
        }
        BlockPos neighbour = pos.offset(side);
        EnumFacing opposite = side.getOpposite();
        Vec3d hitVec = new Vec3d((Vec3i) neighbour).add(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
        PlayerUtil.rightClickBlock(neighbour, hitVec, hand, opposite, packet);
        PlayerUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        return true;
    }

    public static EnumFacing getFirstFacing(BlockPos pos) {
        Iterator<EnumFacing> iterator = PlayerUtil.getPossibleSides(pos).iterator();
        if (iterator.hasNext()) {
            EnumFacing facing = iterator.next();
            return facing;
        }
        return null;
    }

    public static List<EnumFacing> getPossibleSides(BlockPos pos) {
        ArrayList<EnumFacing> facings = new ArrayList<EnumFacing>();
        for (EnumFacing side : EnumFacing.values()) {
            IBlockState blockState;
            BlockPos neighbour = pos.offset(side);
            if (PlayerUtil.mc.world.getBlockState(neighbour) == null || PlayerUtil.mc.world.getBlockState(neighbour).getBlock() == null) {
                return facings;
            }
            if (!PlayerUtil.mc.world.getBlockState(neighbour).getBlock().canCollideCheck(PlayerUtil.mc.world.getBlockState(neighbour), false) || (blockState = PlayerUtil.mc.world.getBlockState(neighbour)).getMaterial().isReplaceable())
                continue;
            facings.add(side);
        }
        return facings;
    }

    public static EnumFacing getRayTraceFacing(BlockPos blockPos) {
        RayTraceResult rayTraceBlocks = PlayerUtil.mc.world.rayTraceBlocks(new Vec3d(PlayerUtil.mc.player.posX, PlayerUtil.mc.player.posY + (double) PlayerUtil.mc.player.getEyeHeight(), PlayerUtil.mc.player.posZ), new Vec3d((double) blockPos.getX() + 0.5, (double) blockPos.getX() - 0.5, (double) blockPos.getX() + 0.5));
        if (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) {
            return EnumFacing.UP;
        }
        return rayTraceBlocks.sideHit;
    }

    public static int getBestAvailableToolSlot(IBlockState blockState) {
        int n = -1;
        double n2 = 0.0;
        for (int i = 0; i < 9; ++i) {
            float f;
            float f2;
            ItemStack getStackInSlot = PlayerUtil.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot.isEmpty()) continue;
            float getDestroySpeed = getStackInSlot.getDestroySpeed(blockState);
            if (!(f2 > 1.0f)) continue;
            int getEnchantmentLevel = EnchantmentHelper.getEnchantmentLevel((Enchantment) Enchantments.EFFICIENCY, (ItemStack) getStackInSlot);
            float n3 = (float) ((double) getDestroySpeed + (getEnchantmentLevel > 0 ? Math.pow(getEnchantmentLevel, 2.0) + 1.0 : 0.0));
            if (!((double) f > n2)) continue;
            n2 = n3;
            n = i;
        }
        return n;
    }

    public static void rightClickBlock(BlockPos pos, Vec3d vec, EnumHand hand, EnumFacing direction, boolean packet) {
        if (packet) {
            float f = (float) (vec.x - (double) pos.getX());
            float f1 = (float) (vec.y - (double) pos.getY());
            float f2 = (float) (vec.z - (double) pos.getZ());
            PlayerUtil.mc.player.connection.sendPacket((Packet) new CPacketPlayerTryUseItemOnBlock(pos, direction, hand, f, f1, f2));
        } else {
            PlayerUtil.mc.playerController.processRightClickBlock(PlayerUtil.mc.player, PlayerUtil.mc.world, pos, direction, vec, hand);
        }
    }

    public static float[] getRotations(Entity e) {
        double deltaX = e.posX + (e.posX - e.lastTickPosX) - PlayerUtil.mc.player.posX;
        double deltaY = e.posY - 3.5 + (double) e.getEyeHeight() - PlayerUtil.mc.player.posY + (double) PlayerUtil.mc.player.getEyeHeight();
        double deltaZ = e.posZ + (e.posZ - e.lastTickPosZ) - PlayerUtil.mc.player.posZ;
        double distance = Math.sqrt(Math.pow(deltaX, 2.0) + Math.pow(deltaZ, 2.0));
        float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ));
        float pitch = (float) (-Math.toDegrees(Math.atan(deltaY / distance)));
        if (deltaX < 0.0 && deltaZ < 0.0) {
            yaw = (float) (90.0 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
        } else if (deltaX > 0.0 && deltaZ < 0.0) {
            yaw = (float) (-90.0 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
        }
        return new float[]{yaw, pitch};
    }

    public static List<BlockPos> getSphere(BlockPos blockPos, float n, int n2, boolean b, boolean b2, int n3) {
        ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        int getX = blockPos.getX();
        int getY = blockPos.getY();
        int getZ = blockPos.getZ();
        int n4 = getX - (int) n;
        while ((float) n4 <= (float) getX + n) {
            int n5 = getZ - (int) n;
            while ((float) n5 <= (float) getZ + n) {
                int n6 = b2 ? getY - (int) n : getY;
                while (true) {
                    float f = n6;
                    float f2 = b2 ? (float) getY + n : (float) (getY + n2);
                    if (!(f < f2)) break;
                    double n7 = (getX - n4) * (getX - n4) + (getZ - n5) * (getZ - n5) + (b2 ? (getY - n6) * (getY - n6) : 0);
                    if (n7 < (double) (n * n) && (!b || n7 >= (double) ((n - 1.0f) * (n - 1.0f)))) {
                        list.add(new BlockPos(n4, n6 + n3, n5));
                    }
                    ++n6;
                }
                ++n5;
            }
            ++n4;
        }
        return list;
    }
}

