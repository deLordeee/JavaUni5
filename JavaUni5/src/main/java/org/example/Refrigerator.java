package org.example;

/**
 * Represents a refrigerator.
 * <p>
 * The refrigerator maintains an internal temperature and
 * consumes a fraction of its rated power when running.
 * </p>
 */
public class Refrigerator extends Appliance {
    private double temperatureCelsius;

    /**
     * Constructs a refrigerator.
     *
     * @param id               unique identifier
     * @param name             display name
     * @param powerCapacity    rated power in watts
     * @param emRadiation      electromagnetic radiation value
     * @param temperatureCelsius initial internal temperature in Celsius
     */
    public Refrigerator(String id, String name, double powerCapacity, double emRadiation, double temperatureCelsius) {
        super(id, name, powerCapacity, emRadiation);
        this.temperatureCelsius = temperatureCelsius;
    }

    /** @return current internal temperature in Celsius */
    public double getTemperatureCelsius() { return temperatureCelsius; }

    /** Sets the internal temperature. */
    public void setTemperatureCelsius(double temperatureCelsius) { this.temperatureCelsius = temperatureCelsius; }

    @Override
    public double currentPowerConsumption() {
        return isTurnedOn() ? getpowerCapacity() * 0.65 : 0.0; // simplified efficiency coefficient
    }
}
