/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 */
package international.astro.graphics.clickgui.comp;

import international.astro.Astro;
import international.astro.graphics.clickgui.ClickGui;
import international.astro.graphics.clickgui.OptionButton;
import international.astro.hack.Hack;
import international.astro.hack.option.Option;
import international.astro.hack.option.options.OBoolean;
import international.astro.util.RenderUtils;

import java.awt.Color;

import net.minecraft.client.gui.Gui;

public class Boolean
        extends OptionButton {
    private final OBoolean option;

    public Boolean(Hack hack, Option option, int X, int Y, int W, int H) {
        super(hack, X, Y, W, H);
        this.option = (OBoolean) option;
    }

    @Override
    public void render(int mX, int mY) {
        Gui.drawRect((int) this.getX(), (int) this.getY(), (int) (this.getX() + 105), (int) (this.getY() + 15), (int) new Color(0, 0, 0, 180).getRGB());
        if (this.option.isEnabled()) {
            ClickGui.drawRect((int) (this.getX() + 88), (int) (this.getY() + 2), (int) (this.getX() + 89), (int) (this.getY() + 15 - 2), (int) Astro.colorManager.getRGBA());
            ClickGui.drawRect((int) (this.getX() + 99), (int) (this.getY() + 2), (int) (this.getX() + 100), (int) (this.getY() + 15 - 2), (int) Astro.colorManager.getRGBA());
            ClickGui.drawRect((int) (this.getX() + 88), (int) (this.getY() + 1), (int) (this.getX() + 100), (int) (this.getY() + 2), (int) Astro.colorManager.getRGBA());
            ClickGui.drawRect((int) (this.getX() + 88), (int) (this.getY() + 15 - 3), (int) (this.getX() + 100), (int) (this.getY() + 15 - 2), (int) Astro.colorManager.getRGBA());
            ClickGui.drawRect((int) (this.getX() + 90), (int) (this.getY() + 3), (int) (this.getX() + 98), (int) (this.getY() + 15 - 4), (int) Astro.colorManager.getRGBA());
        } else {
            ClickGui.drawRect((int) (this.getX() + 88), (int) (this.getY() + 2), (int) (this.getX() + 89), (int) (this.getY() + 15 - 2), (int) Astro.colorManager.getRGBA());
            ClickGui.drawRect((int) (this.getX() + 99), (int) (this.getY() + 2), (int) (this.getX() + 100), (int) (this.getY() + 15 - 2), (int) Astro.colorManager.getRGBA());
            ClickGui.drawRect((int) (this.getX() + 88), (int) (this.getY() + 1), (int) (this.getX() + 100), (int) (this.getY() + 2), (int) Astro.colorManager.getRGBA());
            ClickGui.drawRect((int) (this.getX() + 88), (int) (this.getY() + 15 - 3), (int) (this.getX() + 100), (int) (this.getY() + 15 - 2), (int) Astro.colorManager.getRGBA());
        }
        if (Astro.hackManager.getHack("CustomFont").isEnabled()) {
            Astro.font.drawString(this.option.getName(), this.getX() + 3, this.getY() + 4, new Color(175, 175, 175, 255).getRGB());
        } else {
            this.mc.fontRenderer.drawString(this.option.getName(), this.getX() + 3, this.getY() + 4, new Color(175, 175, 175, 255).getRGB());
        }
        Gui.drawRect((int) this.getX(), (int) this.getY(), (int) (this.getX() + 1), (int) (this.getY() + 15), (int) Astro.colorManager.getRGBA());
        Gui.drawRect((int) (this.getX() + 104), (int) this.getY(), (int) (this.getX() + 105), (int) (this.getY() + 15), (int) Astro.colorManager.getRGBA());
    }

    @Override
    public void mouseDown(int mX, int mY, int mB) {
        if (RenderUtils.isHovered(this.getX(), this.getY(), this.getW(), this.getH() - 1, mX, mY) && (mB == 0 || mB == 1)) {
            this.option.setEnabled(!this.option.isEnabled());
        }
    }
}

