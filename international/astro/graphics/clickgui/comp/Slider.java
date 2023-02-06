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
import international.astro.hack.option.options.ODouble;
import international.astro.util.RenderUtils;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

import net.minecraft.client.gui.Gui;

public class Slider
        extends OptionButton {
    public final ODouble option;
    protected boolean dragging = false;
    protected int sliderWidth = 0;

    public Slider(Hack hack, Option option, int X, int Y, int W, int H) {
        super(hack, X, Y, W, H);
        this.option = (ODouble) option;
    }

    @Override
    public void render(int mX, int mY) {
        double diff = Math.min(this.getW(), Math.max(0, mX - this.getX()));
        if (this.dragging) {
            if (diff == 0.0) {
                this.option.setValue(this.option.getMin());
            } else {
                this.option.setValue(this.roundToPlace(diff / (double) this.getW() * (this.option.getMax() - this.option.getMin()) + this.option.getMin(), 1));
            }
        }
        this.sliderWidth = (int) ((double) (this.getW() - 6) * (this.option.getValue() - this.option.getMin()) / (this.option.getMax() - this.option.getMin()));
        ClickGui.drawRect((int) this.getX(), (int) this.getY(), (int) (this.getX() + this.getW()), (int) (this.getY() + this.getH()), (int) new Color(0, 0, 0, 180).getRGB());
        ClickGui.drawRect((int) (this.getX() + 2), (int) this.getY(), (int) (this.getX() + this.sliderWidth + 4), (int) (this.getY() + this.getH()), (int) Astro.colorManager.getRGBA());
        if (Astro.hackManager.getHack("CustomFont").isEnabled()) {
            Astro.font.drawString(this.option.getName(), this.getX() + 3, this.getY() + 4, new Color(255, 255, 255, 255).getRGB());
            Astro.font.drawString(String.valueOf(this.option.getValue()), (float) (this.getX() + this.getW() - this.mc.fontRenderer.getStringWidth(String.valueOf(this.option.getValue()))) - 2.0f, this.getY() + 4, new Color(255, 255, 255, 255).getRGB());
        } else {
            this.mc.fontRenderer.drawString(this.option.getName(), this.getX() + 3, this.getY() + 4, new Color(255, 255, 255, 255).getRGB());
            this.mc.fontRenderer.drawString(String.valueOf(this.option.getValue()), this.getX() + this.getW() - this.mc.fontRenderer.getStringWidth(String.valueOf(this.option.getValue())) - 2, this.getY() + 4, new Color(255, 255, 255, 255).getRGB());
        }
        Gui.drawRect((int) this.getX(), (int) this.getY(), (int) (this.getX() + 1), (int) (this.getY() + 15), (int) Astro.colorManager.getRGBA());
        Gui.drawRect((int) (this.getX() + 104), (int) this.getY(), (int) (this.getX() + 105), (int) (this.getY() + 15), (int) Astro.colorManager.getRGBA());
    }

    @Override
    public void mouseDown(int mX, int mY, int mB) {
        if (RenderUtils.isHovered(this.getX(), this.getY(), this.getW(), this.getH() - 1, mX, mY)) {
            this.dragging = true;
        }
    }

    @Override
    public void mouseUp(int mouseX, int mouseY) {
        this.dragging = false;
        this.render(mouseX, mouseY);
    }

    @Override
    public void close() {
        this.dragging = false;
    }

    public double roundToPlace(double value, int place) {
        if (place < 0) {
            return value;
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(place, RoundingMode.HALF_DOWN);
        return bd.doubleValue();
    }
}

