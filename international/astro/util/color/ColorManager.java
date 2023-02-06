/*
 * Decompiled with CFR 0.150.
 */
package international.astro.util.color;

import java.awt.Color;

public class ColorManager {
    int red;
    int green;
    int blue;
    int alpha;

    public ColorManager(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public int getRed() {
        return this.red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return this.green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return this.blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getAlpha() {
        return this.alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getRGBA() {
        return new Color(this.red, this.green, this.blue, this.alpha).getRGB();
    }

    public Color getColor() {
        return new Color(this.red, this.green, this.blue, this.alpha);
    }
}

