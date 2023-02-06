/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 */
package international.astro.graphics.clickgui;

import international.astro.Astro;
import international.astro.graphics.clickgui.HackButton;
import international.astro.graphics.clickgui.OptionButton;
import international.astro.hack.Hack;
import international.astro.util.RenderUtils;

import java.awt.Color;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class Box {
    private final ArrayList<HackButton> buttons = new ArrayList();
    private final Hack.Category category;
    private final int W;
    private final int H;
    private final ArrayList<HackButton> buttonsBeforeClosing = new ArrayList();
    protected Minecraft mc = Minecraft.getMinecraft();
    ScaledResolution scaledRes = new ScaledResolution(this.mc);
    private int X;
    private int Y;
    private int dragX;
    private int dragY;
    private boolean open = true;
    private boolean dragging;
    private int showingButtonCount;
    private boolean opening;
    private boolean closing;

    public Box(Hack.Category category, int x, int y, int w, int h) {
        this.category = category;
        this.X = x;
        this.Y = y;
        this.W = w;
        this.H = h;
        int yOffset = this.Y + this.H;
        for (Hack hack : Astro.hackManager.getHacksInCategory(category)) {
            HackButton button = new HackButton(hack, this.X, yOffset, this.W, this.H);
            this.buttons.add(button);
            yOffset += this.H;
        }
        this.showingButtonCount = this.buttons.size();
    }

    public void render(int mX, int mY) {
        if (this.dragging) {
            this.X = this.dragX + mX;
            this.Y = this.dragY + mY;
        }
        Gui.drawRect((int) this.X, (int) (this.Y + 1), (int) (this.X + this.W), (int) (this.Y + this.H), (int) Astro.colorManager.getRGBA());
        Gui.drawRect((int) this.X, (int) (this.Y + 1), (int) (this.X + 1), (int) (this.Y + this.H), (int) Astro.colorManager.getRGBA());
        Gui.drawRect((int) (this.X + 104), (int) (this.Y + 1), (int) (this.X + this.W), (int) (this.Y + this.H), (int) Astro.colorManager.getRGBA());
        Gui.drawRect((int) this.X, (int) (this.Y + 1), (int) (this.X + this.W), (int) (this.Y + 2), (int) Astro.colorManager.getRGBA());
        Gui.drawRect((int) this.X, (int) (this.Y + 14), (int) (this.X + this.W), (int) (this.Y + 15), (int) Astro.colorManager.getRGBA());
        if (Astro.hackManager.getHack("CustomFont").isEnabled()) {
            Astro.font.drawString(this.category.getName(), this.X + this.W / 2 - this.mc.fontRenderer.getStringWidth(this.category.getName()) / 2, (float) ((double) this.Y + 2.5), new Color(255, 255, 255, 255).getRGB());
        } else {
            this.mc.fontRenderer.drawString(this.category.getName(), this.X + this.W / 2 - this.mc.fontRenderer.getStringWidth(this.category.getName()) / 2, this.Y + 2, new Color(255, 255, 255, 255).getRGB());
        }
        if (this.open || this.opening || this.closing) {
            int modY = this.Y + this.H;
            int moduleRenderCount = 0;
            for (HackButton moduleButton : this.buttons) {
                if (++moduleRenderCount >= this.showingButtonCount + 1) continue;
                moduleButton.setX(this.X);
                moduleButton.setY(modY);
                moduleButton.render(mX, mY);
                if (!moduleButton.isOpen() && this.opening && this.buttonsBeforeClosing.contains(moduleButton)) {
                    moduleButton.processRightClick();
                }
                modY += this.H;
                if (!moduleButton.isOpen() && !moduleButton.isOpening() && !moduleButton.isClosing()) continue;
                int settingRenderCount = 0;
                for (OptionButton optionButton : moduleButton.getButtons()) {
                    if (++settingRenderCount >= moduleButton.getShowingModuleCount() + 1) continue;
                    optionButton.setX(this.X);
                    optionButton.setY(modY);
                    optionButton.render(mX, mY);
                    modY += this.H;
                }
            }
        }
        if (this.opening) {
            ++this.showingButtonCount;
            if (this.showingButtonCount == this.buttons.size()) {
                this.opening = false;
                this.open = true;
                this.buttonsBeforeClosing.clear();
            }
        }
        if (this.closing) {
            --this.showingButtonCount;
            if (this.showingButtonCount == 0 || this.showingButtonCount == 1) {
                this.closing = false;
                this.open = false;
            }
        }
    }

    public void mouseDown(int mX, int mY, int mB) {
        if (RenderUtils.isHovered(this.X, this.Y, this.W, this.H, mX, mY) && mB == 0) {
            this.dragging = true;
            this.dragX = this.X - mX;
            this.dragY = this.Y - mY;
        }
        if (this.open) {
            for (HackButton button : this.buttons) {
                button.mouseDown(mX, mY, mB);
            }
        }
    }

    public void mouseUp(int mX, int mY) {
        this.dragging = false;
        if (this.open) {
            for (HackButton button : this.buttons) {
                button.mouseUp(mX, mY);
            }
        }
    }

    public void keyPress(int key) {
        if (this.open) {
            for (HackButton button : this.buttons) {
                button.keyPress(key);
            }
        }
    }

    public void close() {
        for (HackButton button : this.buttons) {
            button.close();
        }
    }

    public int getY() {
        return this.Y;
    }

    public void setY(int y) {
        this.Y = y;
    }
}

