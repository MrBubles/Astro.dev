/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package international.astro.graphics.clickgui;

import international.astro.Astro;
import international.astro.hack.Hack;

import java.awt.Color;

import net.minecraft.client.Minecraft;

public class OptionButton {
    public final Minecraft mc = Minecraft.getMinecraft();
    private final int H;
    private Hack hack;
    private int X;
    private int Y;
    private int W;

    public OptionButton(Hack hack, int x, int y, int w, int h) {
        this.hack = hack;
        this.X = x;
        this.Y = y;
        this.W = w;
        this.H = h;
    }

    public void render(int mX, int mY) {
    }

    public void mouseDown(int mX, int mY, int mB) {
    }

    public void mouseUp(int mX, int mY) {
    }

    public void keyPress(int key) {
    }

    public void close() {
    }

    public void drawButton(int mX, int mY) {
        if (this.hack.isEnabled()) {
            Astro.clickGui.drawGradient(this.X, this.Y, this.X + this.W, this.Y + this.H, new Color(150, 150, 250, 225).getRGB(), new Color(150, 150, 250, 225).getRGB());
            Astro.clickGui.drawGradient(this.X, this.Y, this.X + 2, this.Y + this.H, new Color(120, 120, 210, 225).getRGB(), new Color(120, 120, 210, 225).getRGB());
        } else {
            Astro.clickGui.drawGradient(this.X, this.Y, this.X + this.W, this.Y + this.H, new Color(70, 70, 70, 225).getRGB(), new Color(70, 70, 70, 225).getRGB());
            Astro.clickGui.drawGradient(this.X, this.Y, this.X + 2, this.Y + this.H, new Color(120, 120, 210, 225).getRGB(), new Color(120, 120, 210, 225).getRGB());
        }
    }

    public Hack getHack() {
        return this.hack;
    }

    public void setModule(Hack hack) {
        this.hack = hack;
    }

    public int getX() {
        return this.X;
    }

    public void setX(int x) {
        this.X = x;
    }

    public int getY() {
        return this.Y;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public int getW() {
        return this.W;
    }

    public int getH() {
        return this.H;
    }
}

