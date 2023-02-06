/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 */
package international.astro.graphics.clickgui;

import international.astro.Astro;
import international.astro.graphics.clickgui.OptionButton;
import international.astro.graphics.clickgui.comp.Bind;
import international.astro.graphics.clickgui.comp.Boolean;
import international.astro.graphics.clickgui.comp.Mode;
import international.astro.graphics.clickgui.comp.Slider;
import international.astro.hack.Hack;
import international.astro.hack.option.Option;
import international.astro.hack.option.options.OBoolean;
import international.astro.hack.option.options.ODouble;
import international.astro.hack.option.options.OList;
import international.astro.util.RenderUtils;

import java.awt.Color;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class HackButton {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final Hack hack;
    private final ArrayList<OptionButton> buttons = new ArrayList();
    private final int W;
    private final int H;
    private int X;
    private int Y;
    private boolean open;
    private int showingModuleCount;
    private boolean opening;
    private boolean closing;

    public HackButton(Hack hack, int x, int y, int w, int h) {
        this.hack = hack;
        this.X = x;
        this.Y = y;
        this.W = w;
        this.H = h;
        int n = 0;
        for (Option option : hack.getOptions()) {
            OptionButton optionButton = null;
            if (option instanceof OBoolean) {
                optionButton = new Boolean(hack, option, this.X, this.Y + this.H + n, this.W, this.H);
            } else if (option instanceof ODouble) {
                optionButton = new Slider(hack, option, this.X, this.Y + this.H + n, this.W, this.H);
            } else if (option instanceof OList) {
                optionButton = new Mode(hack, option, this.X, this.Y + this.H + n, this.W, this.H);
            }
            if (optionButton == null) continue;
            this.buttons.add(optionButton);
            n += this.H;
        }
        this.buttons.add(new Bind(hack, this.X, this.Y + this.H + n, this.W, this.H));
    }

    public void render(int mX, int mY) {
        Gui.drawRect((int) this.X, (int) this.Y, (int) (this.X + this.W), (int) (this.Y + this.H), (int) new Color(0, 0, 0, 180).getRGB());
        if (this.hack.isEnabled()) {
            Gui.drawRect((int) this.X, (int) this.Y, (int) (this.X + this.W), (int) (this.Y + this.H), (int) Astro.colorManager.getRGBA());
            if (Astro.hackManager.getHack("CustomFont").isEnabled()) {
                Astro.font.drawString(this.hack.getName(), this.X + 3, this.Y + 4, new Color(255, 255, 255).getRGB());
            } else {
                this.mc.fontRenderer.drawString(this.hack.getName(), this.X + 3, this.Y + 4, new Color(255, 255, 255).getRGB());
            }
        } else if (Astro.hackManager.getHack("CustomFont").isEnabled()) {
            Astro.font.drawString(this.hack.getName(), this.X + 3, this.Y + 4, new Color(255, 255, 255, 255).getRGB());
        } else {
            this.mc.fontRenderer.drawString(this.hack.getName(), this.X + 3, this.Y + 4, new Color(255, 255, 255, 255).getRGB());
        }
        if (this.opening) {
            ++this.showingModuleCount;
            if (this.showingModuleCount == this.buttons.size()) {
                this.opening = false;
                this.open = true;
            }
        }
        if (this.closing) {
            --this.showingModuleCount;
            if (this.showingModuleCount == 0) {
                this.closing = false;
                this.open = false;
            }
        }
        Gui.drawRect((int) this.X, (int) (this.Y + 15), (int) (this.X + this.W), (int) (this.Y + 16), (int) Astro.colorManager.getRGBA());
    }

    public void mouseDown(int mX, int mY, int mB) {
        if (RenderUtils.isHovered(this.X, this.Y, this.W, this.H - 1, mX, mY)) {
            if (mB == 0) {
                this.hack.toggle();
                if (this.hack.getName().equals("ClickGUI")) {
                    this.mc.displayGuiScreen(null);
                }
            } else if (mB == 1) {
                this.processRightClick();
            }
        }
        if (this.open) {
            for (OptionButton optionButton : this.buttons) {
                optionButton.mouseDown(mX, mY, mB);
            }
        }
    }

    public void mouseUp(int mX, int mY) {
        for (OptionButton optionButton : this.buttons) {
            optionButton.mouseUp(mX, mY);
        }
    }

    public void keyPress(int key) {
        for (OptionButton optionButton : this.buttons) {
            optionButton.keyPress(key);
        }
    }

    public void close() {
        for (OptionButton button : this.buttons) {
            button.close();
        }
    }

    public void setX(int x) {
        this.X = x;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public boolean isOpen() {
        return this.open;
    }

    public Hack getModule() {
        return this.hack;
    }

    public ArrayList<OptionButton> getButtons() {
        return this.buttons;
    }

    public int getShowingModuleCount() {
        return this.showingModuleCount;
    }

    public boolean isOpening() {
        return this.opening;
    }

    public boolean isClosing() {
        return this.closing;
    }

    public void processRightClick() {
        if (!this.open) {
            this.showingModuleCount = 0;
            this.opening = true;
        } else {
            this.showingModuleCount = this.buttons.size();
            this.closing = true;
        }
    }
}

