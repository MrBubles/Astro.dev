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
import international.astro.hack.option.options.OList;
import international.astro.util.RenderUtils;

import java.awt.Color;

import net.minecraft.client.gui.Gui;

public class Mode
        extends OptionButton {
    private final OList option;

    public Mode(Hack hack, Option option, int X, int Y, int W, int H) {
        super(hack, X, Y, W, H);
        this.option = (OList) option;
    }

    @Override
    public void render(int mX, int mY) {
        ClickGui.drawRect((int) this.getX(), (int) this.getY(), (int) (this.getX() + 105), (int) (this.getY() + 15), (int) new Color(0, 0, 0, 180).getRGB());
        if (Astro.hackManager.getHack("CustomFont").isEnabled()) {
            Astro.font.drawString(this.option.getName(), this.getX() + 3, this.getY() + 4, new Color(255, 255, 255, 255).getRGB());
            Astro.font.drawString(this.option.getMode(), this.getX() + this.getW() - Astro.font.getStringWidth(this.option.getMode()), this.getY() + 4, new Color(255, 255, 255, 255).getRGB());
        } else {
            this.mc.fontRenderer.drawString(this.option.getName(), this.getX() + 3, this.getY() + 4, new Color(255, 255, 255, 255).getRGB());
            this.mc.fontRenderer.drawString(this.option.getMode(), this.getX() + this.getW() - Astro.font.getStringWidth(this.option.getMode()), this.getY() + 4, new Color(255, 255, 255, 255).getRGB());
        }
        Gui.drawRect((int) this.getX(), (int) this.getY(), (int) (this.getX() + 1), (int) (this.getY() + 15), (int) Astro.colorManager.getRGBA());
        Gui.drawRect((int) (this.getX() + 104), (int) this.getY(), (int) (this.getX() + 105), (int) (this.getY() + 15), (int) Astro.colorManager.getRGBA());
    }

    @Override
    public void mouseDown(int mX, int mY, int mB) {
        block12:
        {
            block13:
            {
                if (!RenderUtils.isHovered(this.getX(), this.getY(), this.getW(), this.getH() - 1, mX, mY))
                    break block12;
                if (mB != 0) break block13;
                int i = 0;
                int listIndex = 0;
                for (String listName : this.option.getModes()) {
                    if (listName.equals(this.option.getMode())) {
                        listIndex = i;
                    }
                    ++i;
                }
                if (listIndex == this.option.getModes().size() - 1) {
                    this.option.setMode(this.option.getModes().get(0));
                } else {
                    ++listIndex;
                    i = 0;
                    for (String listName : this.option.getModes()) {
                        if (i == listIndex) {
                            this.option.setMode(listName);
                        }
                        ++i;
                    }
                }
                break block12;
            }
            if (mB != 1) break block12;
            int i = 0;
            int listIndex = 0;
            for (String listName : this.option.getModes()) {
                if (listName.equals(this.option.getMode())) {
                    listIndex = i;
                }
                ++i;
            }
            if (listIndex == 0) {
                this.option.setMode(this.option.getModes().get(this.option.getModes().size() - 1));
            } else {
                --listIndex;
                i = 0;
                for (String listName : this.option.getModes()) {
                    if (i == listIndex) {
                        this.option.setMode(listName);
                    }
                    ++i;
                }
            }
        }
    }
}

