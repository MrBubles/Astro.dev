/*
 * Decompiled with CFR 0.150.
 */
package international.astro.hack.option.options;

import international.astro.hack.option.Option;

public class ODouble
        extends Option {
    public double min;
    public double max;
    public double increment;
    public double value;

    public ODouble(String name, double min, double max, double increment, double defVal) {
        super(name);
        this.min = min;
        this.max = max;
        this.increment = increment;
        this.value = defVal;
    }

    public static double clamp(double value, double min, double max) {
        if (value > max) {
            value = max;
        }
        if (value < min) {
            value = min;
        }
        return value;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        value = ODouble.clamp(value, this.min, this.max);
        this.value = value = (double) Math.round(value * (1.0 / this.increment)) / (1.0 / this.increment);
    }

    public float getFloatValue() {
        return (float) this.value;
    }

    public int getIntValue() {
        return (int) this.value;
    }

    public double getIncrement() {
        return this.increment;
    }

    public void increment(boolean positive) {
        if (positive) {
            this.setValue(this.getValue() + this.getIncrement());
        } else {
            this.setValue(this.getValue() - this.getIncrement());
        }
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }
}

