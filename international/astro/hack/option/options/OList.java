/*
 * Decompiled with CFR 0.150.
 */
package international.astro.hack.option.options;

import international.astro.hack.option.Option;

import java.util.Arrays;
import java.util.List;

public class OList
        extends Option {
    private String mode;
    private List<String> modes;
    private int index;

    public OList(String name, String defVal, String... modes) {
        super(name);
        this.modes = Arrays.asList(modes);
        this.mode = defVal;
        this.index = this.modes.indexOf(defVal);
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
        this.index = this.modes.indexOf(mode);
    }

    public List<String> getModes() {
        return this.modes;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
        this.mode = this.modes.get(index);
    }

    public void cycle() {
        if (this.index < this.modes.size() - 1) {
            ++this.index;
            this.mode = this.modes.get(this.index);
        } else if (this.index >= this.modes.size() - 1) {
            this.index = 0;
            this.mode = this.modes.get(0);
        }
    }

    public boolean isMode(String mode) {
        return this.mode == mode;
    }
}

