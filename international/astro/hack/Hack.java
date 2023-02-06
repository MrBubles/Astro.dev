/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.common.MinecraftForge
 */
package international.astro.hack;

import international.astro.Astro;
import international.astro.hack.option.Option;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class Hack {
    public static final Minecraft mc = Minecraft.getMinecraft();
    private Construct construct = this.getClass().getAnnotation(Construct.class);
    private String name = this.construct.name();
    private String description = this.construct.description();
    private Category category = this.construct.category();
    private int bind;
    private boolean enabled;
    private List<Option> options = new ArrayList<Option>();

    public static void onUpdate() {
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void enable() {
        this.setEnabled(true);
        this.onEnable();
        Astro.sendMsg("toggled " + this.getName() + " To True");
        MinecraftForge.EVENT_BUS.register((Object) this);
    }

    public void disable() {
        this.setEnabled(false);
        this.onDisable();
        Astro.sendMsg("toggled " + this.getName() + " To False");
        MinecraftForge.EVENT_BUS.unregister((Object) this);
    }

    public void toggle() {
        if (this.isEnabled()) {
            this.disable();
        } else {
            this.enable();
        }
    }

    public boolean nullCheck() {
        return Hack.mc.player == null || Hack.mc.world == null;
    }

    public List<Option> getOptions() {
        return this.options;
    }

    public void addOption(Option option) {
        this.options.add(option);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getBind() {
        return this.bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public static enum Category {
        COMBAT("Combat"),
        EXPLOIT("Exploit"),
        CLIENT("Client"),
        RENDER("Render"),
        MOVEMENT("Movement"),
        RANDOM("Random"),
        MISC("Misc");

        private String name;

        private Category(String name) {
            this.setName(name);
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Retention(value = RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.TYPE})
    public static @interface Construct {
        public String name();

        public String description();

        public Category category();
    }
}

