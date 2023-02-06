/*
 * Decompiled with CFR 0.150.
 */
package international.astro.hack;

import international.astro.hack.Hack;
import international.astro.hack.hacks.client.BackupCaller;
import international.astro.hack.hacks.client.ClickGuiMod;
import international.astro.hack.hacks.client.CustomFont;
import international.astro.hack.hacks.client.HUD;
import international.astro.hack.hacks.combat.AntiAntiBed;
import international.astro.hack.hacks.combat.AutoTotem;
import international.astro.hack.hacks.combat.Burrow;
import international.astro.hack.hacks.combat.Crits;
import international.astro.hack.hacks.combat.KillAura;
import international.astro.hack.hacks.combat.SelfWeb;
import international.astro.hack.hacks.combat.SplashPotRefill;
import international.astro.hack.hacks.exploits.CornerClip;
import international.astro.hack.hacks.exploits.Disabler;
import international.astro.hack.hacks.exploits.GodMode;
import international.astro.hack.hacks.exploits.Timer;
import international.astro.hack.hacks.exploits.XCarry;
import international.astro.hack.hacks.misc.AntiReGear;
import international.astro.hack.hacks.misc.AutoIgnore;
import international.astro.hack.hacks.misc.ChatPlus;
import international.astro.hack.hacks.misc.Fakeplayer;
import international.astro.hack.hacks.misc.FastUse;
import international.astro.hack.hacks.misc.FootExp;
import international.astro.hack.hacks.misc.InstaMine;
import international.astro.hack.hacks.misc.NoRotate;
import international.astro.hack.hacks.movement.AntiKB;
import international.astro.hack.hacks.movement.Flight;
import international.astro.hack.hacks.movement.IceSpeed;
import international.astro.hack.hacks.movement.NoFall;
import international.astro.hack.hacks.movement.NoSlow;
import international.astro.hack.hacks.movement.Phase;
import international.astro.hack.hacks.random.AntiChina;
import international.astro.hack.hacks.random.Spinjitzu;
import international.astro.hack.hacks.render.Animations;
import international.astro.hack.hacks.render.CrystalModifier;
import international.astro.hack.hacks.render.Fov;
import international.astro.hack.hacks.render.FreeLook;
import international.astro.hack.hacks.render.FullBright;
import international.astro.hack.hacks.render.MoonWalk;
import international.astro.hack.hacks.render.NoRender;
import international.astro.hack.hacks.render.ViewClip;
import international.astro.hack.hacks.render.WorldTime;

import java.util.ArrayList;
import java.util.List;

public class HackManager {
    public List<Hack> hacks = new ArrayList<Hack>();

    public HackManager() {
        this.add(new CornerClip());
        this.add(new KillAura());
        this.add(new XCarry());
        this.add(new NoFall());
        this.add(new NoSlow());
        this.add(new Timer());
        this.add(new Phase());
        this.add(new ChatPlus());
        this.add(new ViewClip());
        this.add(new ClickGuiMod());
        this.add(new SelfWeb());
        this.add(new MoonWalk());
        this.add(new Burrow());
        this.add(new Fakeplayer());
        this.add(new Disabler());
        this.add(new Flight());
        this.add(new HUD());
        this.add(new FreeLook());
        this.add(new Spinjitzu());
        this.add(new NoRotate());
        this.add(new AutoIgnore());
        this.add(new AntiChina());
        this.add(new AntiAntiBed());
        this.add(new AntiKB());
        this.add(new IceSpeed());
        this.add(new FootExp());
        this.add(new Crits());
        this.add(new AutoTotem());
        this.add(new Animations());
        this.add(new FastUse());
        this.add(new InstaMine());
        this.add(new GodMode());
        this.add(new BackupCaller());
        this.add(new CrystalModifier());
        this.add(new AntiReGear());
        this.add(new FullBright());
        this.add(new WorldTime());
        this.add(new NoRender());
        this.add(new Fov());
        this.add(new CustomFont());
        this.add(new SplashPotRefill());
    }

    public List<Hack> getHacks() {
        return this.hacks;
    }

    public List<Hack> getEnabledHacks() {
        ArrayList<Hack> enabled = new ArrayList<Hack>();
        for (Hack hack : this.hacks) {
            if (!hack.isEnabled()) continue;
            enabled.add(hack);
        }
        return enabled;
    }

    public Hack getHack(String name) {
        Hack h = null;
        for (Hack hack : this.hacks) {
            if (!name.equalsIgnoreCase(hack.getName())) continue;
            h = hack;
        }
        return h;
    }

    public List<Hack> getHacksInCategory(Hack.Category category) {
        ArrayList<Hack> cathacks = new ArrayList<Hack>();
        for (Hack hack : this.hacks) {
            if (hack.getCategory() != category) continue;
            cathacks.add(hack);
        }
        return cathacks;
    }

    private void add(Hack hack) {
        this.hacks.add(hack);
    }
}

