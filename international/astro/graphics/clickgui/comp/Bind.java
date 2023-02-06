/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  org.lwjgl.input.Keyboard
 */
package international.astro.graphics.clickgui.comp;

import international.astro.Astro;
import international.astro.graphics.clickgui.OptionButton;
import international.astro.hack.Hack;
import international.astro.util.RenderUtils;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;

public class Bind
        extends OptionButton {
    private final Hack hack;
    private boolean binding;

    public Bind(Hack hack, int x, int y, int w, int h) {
        super(hack, x, y, w, h);
        this.hack = hack;
    }

    @Override
    public void render(int mX, int mY) {
        Gui.drawRect((int) this.getX(), (int) this.getY(), (int) (this.getX() + 105), (int) (this.getY() + 15), (int) new Color(0, 0, 0, 180).getRGB());
        if (Astro.hackManager.getHack("CustomFont").isEnabled()) {
            Astro.font.drawString("Bind", this.getX() + 3, this.getY() + 4, new Color(255, 255, 255, 255).getRGB());
        } else {
            this.mc.fontRenderer.drawString("Bind", this.getX() + 3, this.getY() + 4, new Color(255, 255, 255, 255).getRGB());
        }
        if (this.binding) {
            if (Astro.hackManager.getHack("CustomFont").isEnabled()) {
                Astro.font.drawString("...", this.getX() + this.getW() - 6 - this.mc.fontRenderer.getStringWidth("..."), this.getY() + 4, new Color(255, 255, 255, 255).getRGB());
            } else {
                this.mc.fontRenderer.drawString("...", this.getX() + this.getW() - 6 - this.mc.fontRenderer.getStringWidth("..."), this.getY() + 4, new Color(255, 255, 255, 255).getRGB());
            }
        } else {
            try {
                if (Astro.hackManager.getHack("CustomFont").isEnabled()) {
                    Astro.font.drawString(Keyboard.getKeyName((int) this.hack.getBind()), this.getX() + this.getW() - 3 - this.mc.fontRenderer.getStringWidth(Keyboard.getKeyName((int) this.hack.getBind())), this.getY() + 4, new Color(255, 255, 255, 255).getRGB());
                } else {
                    this.mc.fontRenderer.drawString(Keyboard.getKeyName((int) this.hack.getBind()), this.getX() + this.getW() - 3 - this.mc.fontRenderer.getStringWidth(Keyboard.getKeyName((int) this.hack.getBind())), this.getY() + 4, new Color(255, 255, 255, 255).getRGB());
                }
            } catch (Exception e) {
                if (Astro.hackManager.getHack("CustomFont").isEnabled()) {
                    Astro.font.drawString("NONE", this.getX() + this.getW() - this.mc.fontRenderer.getStringWidth("NONE"), this.getY() + 4, new Color(255, 255, 255, 255).getRGB());
                }
                this.mc.fontRenderer.drawString("NONE", this.getX() + this.getW() - this.mc.fontRenderer.getStringWidth("NONE"), this.getY() + 4, new Color(255, 255, 255, 255).getRGB());
            }
        }
        Gui.drawRect((int) this.getX(), (int) this.getY(), (int) (this.getX() + 1), (int) (this.getY() + 15), (int) Astro.colorManager.getRGBA());
        Gui.drawRect((int) (this.getX() + 104), (int) this.getY(), (int) (this.getX() + 105), (int) (this.getY() + 15), (int) Astro.colorManager.getRGBA());
        Gui.drawRect((int) this.getX(), (int) (this.getY() + 15), (int) (this.getX() + 105), (int) (this.getY() + 16), (int) Astro.colorManager.getRGBA());
    }

    @Override
    public void mouseDown(int mX, int mY, int mB) {
        if (RenderUtils.isHovered(this.getX(), this.getY(), this.getW(), this.getH() - 1, mX, mY)) {
            this.binding = !this.binding;
        }
    }

    @Override
    public void keyPress(int key) {
        if (this.binding) {
            if (key == 211 || key == 1 || key == 14 || key == 0) {
                this.getHack().setBind(256);
            } else {
                this.getHack().setBind(key);
            }
            this.binding = false;
        }
    }
}

