package org.example;

/**
 * Represents a microwave oven.
 * <p>
 * A microwave has a configurable power level (0–100%) which affects
 * its current power consumption.
 * </p>
 */
public class Microwave extends Appliance {
    private int powerLevel; // 0..100

    /**
     * Constructs a microwave.
     *
     * @param id            unique identifier
     * @param name          display name
     * @param powerCapacity rated power in watts
     * @param emRadiation   electromagnetic radiation value
     */
    public Microwave(String id, String name, double powerCapacity, double emRadiation) {
        super(id, name, powerCapacity, emRadiation);
        this.powerLevel = 100;
    }

    /** @return current power level (0–100%) */
    public int getPowerLevel() { return powerLevel; }

    /**
     * Sets the microwave power level.
     * Values outside the range are clamped.
     *
     * @param powerLevel new power level (0–100)
     */
    public void setPowerLevel(int powerLevel) {
        this.powerLevel = Math.max(0, Math.min(100, powerLevel));
    }

    @Override
    public double currentPowerConsumption() {
        return isTurnedOn() ? getpowerCapacity() * powerLevel / 100.0 : 0.0;
    }
}
