package org.example;

/**
 * Represents a television set.
 * <p>
 * A television has a channel number and brightness level (0–100%).
 * Power consumption depends on brightness.
 * </p>
 */
public class Television extends Appliance {
    private int channel;
    private int brightness; // 0..100

    /**
     * Constructs a television.
     *
     * @param id            unique identifier
     * @param name          display name
     * @param powerCapacity rated power in watts
     * @param emRadiation   electromagnetic radiation value
     */
    public Television(String id, String name, double powerCapacity, double emRadiation) {
        super(id, name, powerCapacity, emRadiation);
        this.brightness = 50;
    }

    /** @return current channel */
    public int getChannel() { return channel; }

    /** Sets the current channel. */
    public void setChannel(int channel) { this.channel = channel; }

    /** @return brightness level (0–100%) */
    public int getBrightness() { return brightness; }

    /**
     * Sets the brightness level.
     * Values outside the range are clamped.
     *
     * @param brightness new brightness (0–100)
     */
    public void setBrightness(int brightness) {
        this.brightness = Math.max(0, Math.min(100, brightness));
    }

    @Override
    public double currentPowerConsumption() {
        if (!isTurnedOn()) return 0.0;
        return getpowerCapacity() * (0.5 + brightness / 200.0);
    }
}
