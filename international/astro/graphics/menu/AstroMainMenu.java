/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiMainMenu
 *  net.minecraft.client.gui.GuiMultiplayer
 *  net.minecraft.client.gui.GuiOptions
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiWorldSelection
 *  net.minecraft.client.gui.ScaledResolution
 */
package international.astro.graphics.menu;

import international.astro.Astro;
import international.astro.util.RenderUtils;

import java.awt.Color;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.gui.ScaledResolution;

public class AstroMainMenu
        extends GuiScreen {
    private final Button singlePlayerButton = new Button("Single", this.width / 2 - 75, this.height / 2, 75);
    private final Button multiPlayerButton;
    private final Button settingsButton;
    private final Button discordButton;
    private final Button quitButton;
    private final Button vanillaMenu;
    ArrayList<Button> buttons = new ArrayList();

    public AstroMainMenu() {
        this.buttons.add(this.singlePlayerButton);
        this.multiPlayerButton = new Button("Multi", this.width / 2 - 75, this.height / 2 + 25, 75);
        this.buttons.add(this.multiPlayerButton);
        this.settingsButton = new Button("Options", this.width / 2 - 125, this.height / 2, 75);
        this.buttons.add(this.settingsButton);
        this.quitButton = new Button("Quit", this.width / 2 - 125, this.height / 2 + 25, 75);
        this.buttons.add(this.quitButton);
        this.vanillaMenu = new Button("Vanilla Menu", this.width / 2 - 75, this.height / 2, 75);
        this.buttons.add(this.vanillaMenu);
        this.discordButton = new Button("Discord", this.width / 2 - 75, this.height / 2, 75);
        this.buttons.add(this.discordButton);
    }

    public void drawScreen(int p_drawScreen_1_, int p_drawScreen_2_, float p_drawScreen_3_) {
        ScaledResolution sr = new ScaledResolution(this.mc);
        RenderUtils.drawImage(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 0x40FFFFFF, "textures/background.png");
        RenderUtils.drawImage(sr.getScaledWidth() / 2 - 115, sr.getScaledHeight() / 2 - 240, 240, 240, 0x40FFFFFF, "textures/logo.png");
        if (Astro.hackManager.getHack("CustomFont").isEnabled()) {
            Astro.MenuFont.drawString("Copyright Mojang Studios. Do not distribute!", sr.getScaledWidth() - Astro.MenuFont.getStringWidth("Copyright Mojang Studios. Do not distribute!") - 2, sr.getScaledHeight() - Astro.MenuFont.getFontHeight(), -1);
            Astro.MenuFont.drawString("Astro.dev b2.2.5 By Logging4J, Nyaann and WMS :o)", 4.0f, sr.getScaledHeight() - Astro.MenuFont.getFontHeight(), -1);
        } else {
            this.mc.fontRenderer.drawString("Copyright Mojang Studios. Do not distribute!", sr.getScaledWidth() - Astro.MenuFont.getStringWidth("Copyright Mojang Studios. Do not distribute!") - 2, sr.getScaledHeight() - Astro.MenuFont.getFontHeight(), -1);
            this.mc.fontRenderer.drawString("Astro.dev b2.2.5 By Logging4J, Nyaann and WMS :o)", 4, sr.getScaledHeight() - Astro.MenuFont.getFontHeight(), -1);
        }
        this.settingsButton.renderButton(p_drawScreen_1_, p_drawScreen_2_, sr.getScaledWidth() / 2 + 5, sr.getScaledHeight() / 2);
        this.quitButton.renderButton(p_drawScreen_1_, p_drawScreen_2_, sr.getScaledWidth() / 2 + 5, sr.getScaledHeight() / 2 + 25);
        this.singlePlayerButton.renderButton(p_drawScreen_1_, p_drawScreen_2_, sr.getScaledWidth() / 2 - 80, sr.getScaledHeight() / 2);
        this.multiPlayerButton.renderButton(p_drawScreen_1_, p_drawScreen_2_, sr.getScaledWidth() / 2 - 80, sr.getScaledHeight() / 2 + 25);
        this.vanillaMenu.renderButton(p_drawScreen_1_, p_drawScreen_2_, 10, 10);
        this.discordButton.renderButton(p_drawScreen_1_, p_drawScreen_2_, 90, 10);
        super.drawScreen(p_drawScreen_1_, p_drawScreen_2_, p_drawScreen_3_);
    }

    protected void mouseClicked(int p_mouseClicked_1_, int p_mouseClicked_2_, int p_mouseClicked_3_) throws IOException {
        if (this.singlePlayerButton.isPressed(p_mouseClicked_1_, p_mouseClicked_2_)) {
            this.mc.displayGuiScreen((GuiScreen) new GuiWorldSelection((GuiScreen) this));
        } else if (this.multiPlayerButton.isPressed(p_mouseClicked_1_, p_mouseClicked_2_)) {
            this.mc.displayGuiScreen((GuiScreen) new GuiMultiplayer((GuiScreen) this));
        } else if (this.settingsButton.isPressed(p_mouseClicked_1_, p_mouseClicked_2_)) {
            this.mc.displayGuiScreen((GuiScreen) new GuiOptions((GuiScreen) this, this.mc.gameSettings));
        } else if (this.quitButton.isPressed(p_mouseClicked_1_, p_mouseClicked_2_)) {
            this.mc.shutdown();
        } else if (this.vanillaMenu.isPressed(p_mouseClicked_1_, p_mouseClicked_2_)) {
            this.mc.displayGuiScreen((GuiScreen) new GuiMainMenu());
        } else if (this.discordButton.isPressed(p_mouseClicked_1_, p_mouseClicked_2_)) {
            try {
                Desktop.getDesktop().browse(new URL("https://discord.gg/xADp2aVv79").toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_2_, p_mouseClicked_3_);
    }

    public class Button {
        private final String s;
        private final int w;
        private final int h;
        private final Color color;
        private final Color colorPressed;
        private int x;
        private int y;

        public Button(String s, int x, int y, int w) {
            this.s = s;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = 20;
            this.color = new Color(0, 0, 0, 30);
            this.colorPressed = new Color(0, 0, 0, 50);
        }

        public void renderButton(int mouseX, int mouseY, int x, int y) {
            this.x = x;
            this.y = y;
            RenderUtils.roundedRect(x, y, this.w, this.h, 3.0, this.color);
            RenderUtils.roundedRect(x, y, this.w, this.h, 3.0, RenderUtils.isHovered(x, y, this.w, this.h, mouseX, mouseY) ? this.colorPressed : this.color);
            if (Astro.hackManager.getHack("CustomFont").isEnabled()) {
                Astro.MenuFont.drawString(this.s, x + this.w / 2 - Astro.MenuFont.getStringWidth(this.s) / 2, y + this.h / 2 - 4, -1);
            } else {
                AstroMainMenu.this.mc.fontRenderer.drawString(this.s, x + this.w / 2 - Astro.MenuFont.getStringWidth(this.s) / 2, y + this.h / 2 - 4, -1);
            }
        }

        public boolean isPressed(int mouseX, int mouseY) {
            return RenderUtils.isHovered(this.getX(), this.getY(), this.getW(), this.getH(), mouseX, mouseY);
        }

        public int getW() {
            return this.w;
        }

        public int getH() {
            return this.h;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public String getString() {
            return this.s;
        }
    }
}

