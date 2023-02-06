/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Mouse
 */
package international.astro.graphics.clickgui;

import international.astro.Astro;
import international.astro.graphics.clickgui.Box;
import international.astro.hack.Hack;
import international.astro.hack.hacks.client.ClickGuiMod;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

public class ClickGui
        extends GuiScreen {
    private final ArrayList<Box> windows = new ArrayList();

    public ClickGui() {
        int xOffset = 5;
        for (Hack.Category category : Hack.Category.values()) {
            Box window = new Box(category, xOffset, 25, 105, 15);
            this.windows.add(window);
            xOffset += 110;
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.doScroll();
        for (Box window : this.windows) {
            window.render(mouseX, mouseY);
        }
        if (OpenGlHelper.shadersSupported && this.mc.getRenderViewEntity() instanceof EntityPlayer && ClickGuiMod.blur.isEnabled()) {
            if (this.mc.entityRenderer.getShaderGroup() != null) {
                this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
            }
            this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (Box window : this.windows) {
            window.mouseDown(mouseX, mouseY, mouseButton);
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
        for (Box window : this.windows) {
            window.mouseUp(mouseX, mouseY);
        }
    }

    protected void keyTyped(char typedChar, int keyCode) {
        for (Box window : this.windows) {
            window.keyPress(keyCode);
        }
        if (keyCode == 1) {
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null) {
                this.mc.setIngameFocus();
            }
        }
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void onGuiClosed() {
        for (Box window : this.windows) {
            window.close();
        }
        Astro.hackManager.getHack("ClickGUI").toggle();
        if (this.mc.entityRenderer.getShaderGroup() != null) {
            this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
    }

    public void drawGradient(int left, int top, int right, int bottom, int startColor, int endColor) {
        this.drawGradientRect(left, top, right, bottom, startColor, endColor);
    }

    private void doScroll() {
        block3:
        {
            int w;
            block2:
            {
                w = Mouse.getDWheel();
                if (w >= 0) break block2;
                for (Box window : this.windows) {
                    window.setY(window.getY() - 8);
                }
                break block3;
            }
            if (w <= 0) break block3;
            for (Box window : this.windows) {
                window.setY(window.getY() + 8);
            }
        }
    }
}

