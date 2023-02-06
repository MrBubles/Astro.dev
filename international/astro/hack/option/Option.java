/*
 * Decompiled with CFR 0.150.
 */
package international.astro.hack.option;

public class Option {
    private String name;
    private boolean visible = true;

    public Option(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}

