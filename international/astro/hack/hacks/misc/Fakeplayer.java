/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.GameType
 *  net.minecraft.world.World
 */
package international.astro.hack.hacks.misc;

import com.mojang.authlib.GameProfile;
import international.astro.hack.Hack;

import java.util.UUID;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.world.GameType;
import net.minecraft.world.World;

@Hack.Construct(name = "FakePlayer", description = "Spawns in fakeplayer for testing n stuff", category = Hack.Category.MISC)
public class Fakeplayer
        extends Hack {
    private EntityOtherPlayerMP fakePlayer = null;

    @Override
    public void onEnable() {
        this.fakePlayer = new EntityOtherPlayerMP((World) Fakeplayer.mc.world, new GameProfile(UUID.fromString("a3e50241-c8e1-4090-aa05-58c37daacee4"), "Astolfo"));
        this.fakePlayer.copyLocationAndAnglesFrom((Entity) Fakeplayer.mc.player);
        this.fakePlayer.inventory.copyInventory(Fakeplayer.mc.player.inventory);
        this.fakePlayer.setHealth(20.0f);
        this.fakePlayer.setGameType(GameType.SURVIVAL);
        Fakeplayer.mc.world.spawnEntity((Entity) this.fakePlayer);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        if (this.fakePlayer != null) {
            Fakeplayer.mc.world.removeEntity((Entity) this.fakePlayer);
            Fakeplayer.mc.world.removeEntityDangerously((Entity) this.fakePlayer);
            this.fakePlayer = null;
        }
    }
}

