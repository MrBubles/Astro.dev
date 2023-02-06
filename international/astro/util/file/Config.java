/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  com.google.gson.JsonPrimitive
 */
package international.astro.util.file;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import international.astro.Astro;
import international.astro.hack.Hack;
import international.astro.hack.option.Option;
import international.astro.hack.option.options.OBoolean;
import international.astro.hack.option.options.ODouble;
import international.astro.hack.option.options.OList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

public class Config
        extends Thread {
    private static final File abstractDir = new File("Astro");
    private static final String modulesPath = abstractDir.getAbsolutePath() + "/modules";

    public static void loadConfig() {
        Config.loadModules();
    }

    private static void loadModules() {
        for (Hack h : Astro.hackManager.getHacks()) {
            Config.loadHack(h);
        }
    }

    private static void loadHack(Hack hack) {
        try {
            Path path = Paths.get(modulesPath, hack.getName() + ".json");
            if (!path.toFile().exists()) {
                return;
            }
            String rawJson = Config.loadFile(path.toFile());
            JsonObject jsonObject = new JsonParser().parse(rawJson).getAsJsonObject();
            if (jsonObject.get("Enabled") != null) {
                if (jsonObject.get("Enabled").getAsBoolean()) {
                    hack.setEnabled(true);
                    hack.enable();
                } else {
                    hack.setEnabled(false);
                    hack.disable();
                }
            }
            if (jsonObject.get("Bind") != null) {
                hack.setBind(jsonObject.get("Bind").getAsInt());
            }
            for (Option o : hack.getOptions()) {
                if (o instanceof OList && jsonObject.get(o.getName()) != null) {
                    ((OList) o).setMode(jsonObject.get(o.getName()).getAsString());
                }
                if (o instanceof ODouble && jsonObject.get(o.getName()) != null) {
                    ((ODouble) o).setValue(jsonObject.get(o.getName()).getAsFloat());
                }
                if (!(o instanceof OBoolean) || jsonObject.get(o.getName()) == null) continue;
                ((OBoolean) o).setEnabled(jsonObject.get(o.getName()).getAsBoolean());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveConfig() {
        if (!abstractDir.exists() && !abstractDir.mkdirs()) {
            System.out.println("Failed to create config folder");
        }
        if (!new File(modulesPath).exists() && !new File(modulesPath).mkdirs()) {
            System.out.println("Failed to create modules folder");
        }
        Config.saveModules();
    }

    private static void saveModules() {
        for (Hack h : Astro.hackManager.getHacks()) {
            Config.saveHack(h);
        }
    }

    private static void saveHack(Hack h) {
        try {
            Path path = Paths.get(modulesPath, h.getName() + ".json");
            Config.createFile(path);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("Enabled", (JsonElement) new JsonPrimitive(Boolean.valueOf(h.isEnabled())));
            jsonObject.add("Bind", (JsonElement) new JsonPrimitive((Number) h.getBind()));
            for (Option o : h.getOptions()) {
                if (o instanceof OBoolean) {
                    jsonObject.add(o.getName(), (JsonElement) new JsonPrimitive(Boolean.valueOf(((OBoolean) o).isEnabled())));
                }
                if (o instanceof ODouble) {
                    jsonObject.add(o.getName(), (JsonElement) new JsonPrimitive((Number) ((ODouble) o).getValue()));
                }
                if (!(o instanceof OList)) continue;
                jsonObject.add(o.getName(), (JsonElement) new JsonPrimitive(((OList) o).getMode()));
            }
            Gson gson = new Gson();
            Files.write(path, gson.toJson(new JsonParser().parse(jsonObject.toString())).getBytes(), new OpenOption[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createFile(Path path) {
        if (Files.exists(path, new LinkOption[0])) {
            new File(path.normalize().toString()).delete();
        }
        try {
            Files.createFile(path, new FileAttribute[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadFile(File file) throws IOException {
        FileInputStream stream = new FileInputStream(file.getAbsolutePath());
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream));) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    @Override
    public void run() {
        Config.saveConfig();
    }
}

