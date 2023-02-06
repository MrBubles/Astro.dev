/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.opengl.Display
 */
package international.astro;

import com.mojang.realmsclient.gui.ChatFormatting;
import international.astro.Discord;
import international.astro.command.CommandManager;
import international.astro.events.ChatEvent;
import international.astro.events.KeyEvent;
import international.astro.graphics.clickgui.ClickGui;
import international.astro.hack.HackManager;
import international.astro.util.RenderUtils;
import international.astro.util.RotationManager;
import international.astro.util.color.ColorManager;
import international.astro.util.file.Config;
import international.astro.util.font.GlyphPageFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid = "astro", name = "Astro.dev", version = "2.2.5")
public class Astro {
    public static final Logger LOGGER = LogManager.getLogger((String) "Astro.dev");
    public static final String NAME = "Astro.dev";
    public static final String MODID = "astro";
    public static final String VERSION = "2.2.5";
    public static Minecraft mc = Minecraft.getMinecraft();
    public static CommandManager commandManager;
    public static ClickGui clickGui;
    public static HackManager hackManager;
    public static ColorManager colorManager;
    public static GlyphPageFontRenderer font;
    public static GlyphPageFontRenderer MenuFont;
    public static RotationManager rotationManager;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        Astro.log("Client PreInitialization");
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        Astro.log("Client initialization");
        Display.setTitle((String) "Astro.dev b2.2.5");
        Discord.startRPC();
        hackManager = new HackManager();
        commandManager = new CommandManager();
        font = GlyphPageFontRenderer.create("Segoe UI", 18, true, false, false);
        MenuFont = GlyphPageFontRenderer.create("Azonix", 20, true, false, false);
        colorManager = new ColorManager(120, 120, 255, 255);
        clickGui = new ClickGui();
        rotationManager = new RotationManager();
        Config.loadConfig();
        Runtime.getRuntime().addShutdownHook(new Config());
        RenderUtils.setWindowIcon();
        MinecraftForge.EVENT_BUS.register((Object) new KeyEvent());
        MinecraftForge.EVENT_BUS.register((Object) new ChatEvent());
    }

    public static void onShutdown() {
        Discord.stopRPC();
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        Astro.log("Client PostInitialization");
    }

    public static void log(String message) {
        LOGGER.info("[Astro.dev] " + message);
    }

    public static void sendMsg(String s) {
        Astro.mc.player.sendMessage((ITextComponent) new TextComponentString("[" + (Object) ChatFormatting.BLUE + NAME + (Object) ChatFormatting.WHITE + "] " + s));
    }
}

