/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
 */
package international.astro.mixins;

import java.util.Map;
import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

public class MixinLoader
        implements IFMLLoadingPlugin {
    public MixinLoader() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.astro.json");
    }

    public String[] getASMTransformerClass() {
        return new String[0];
    }

    public String getAccessTransformerClass() {
        return null;
    }

    public String getModContainerClass() {
        return null;
    }

    @Nullable
    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> map) {
    }
}

