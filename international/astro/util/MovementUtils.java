/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package international.astro.util;

import net.minecraft.client.Minecraft;

public class MovementUtils {
    public static Minecraft mc = Minecraft.getMinecraft();

    public static void strafe() {
        MovementUtils.strafe(MovementUtils.getSpeed());
    }

    public static float getSpeed() {
        return (float) Math.sqrt(MovementUtils.mc.player.motionX * MovementUtils.mc.player.motionX + MovementUtils.mc.player.motionZ * MovementUtils.mc.player.motionZ);
    }

    public static void strafe(float speed) {
        if (!MovementUtils.isMoving()) {
            return;
        }
        double yaw = MovementUtils.getDirection();
        MovementUtils.mc.player.motionX = -Math.sin(yaw) * (double) speed;
        MovementUtils.mc.player.motionZ = Math.cos(yaw) * (double) speed;
    }

    public static boolean isMoving() {
        return MovementUtils.mc.player != null && MovementUtils.mc.player.movementInput.moveForward != 0.0f || MovementUtils.mc.player.movementInput.moveStrafe != 0.0f;
    }

    public static double getDirection() {
        float rotationYaw = MovementUtils.mc.player.rotationYaw;
        if (MovementUtils.mc.player.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float forward = 1.0f;
        if (MovementUtils.mc.player.moveForward < 0.0f) {
            forward = -0.5f;
        } else if (MovementUtils.mc.player.moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (MovementUtils.mc.player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * forward;
        }
        if (MovementUtils.mc.player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * forward;
        }
        return Math.toRadians(rotationYaw);
    }
}

