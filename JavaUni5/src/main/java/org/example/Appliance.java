package org.example;

/**
 * Abstract base class representing a generic electrical appliance.
 * <p>
 * Each appliance has:
 * <ul>
 *   <li>A unique ID</li>
 *   <li>A human-readable name</li>
 *   <li>Rated power capacity in watts</li>
 *   <li>Electromagnetic radiation value</li>
 *   <li>Plugged-in and turned-on states</li>
 * </ul>
 * </p>
 */
public abstract class Appliance {
    private final String id;
    private String name;
    private final double powerCapacity;
    private boolean pluggedIn = false;
    private boolean turnedOn = false;
    private final double emRadiation;

    /**
     * Constructs a new appliance.
     *
     * @param id            unique identifier
     * @param name          display name
     * @param powerCapacity rated power capacity in watts (must be ≥ 0)
     * @param emRadiation   electromagnetic radiation value (must be ≥ 0)
     * @throws IllegalArgumentException if {@code powerCapacity < 0} or {@code emRadiation < 0}
     */
    public Appliance(String id, String name, double powerCapacity, double emRadiation) {
        if (powerCapacity < 0) throw new IllegalArgumentException("powerCapacity must be >= 0");
        if (emRadiation < 0) throw new IllegalArgumentException("emRadiation must be >= 0");
        this.id = id;
        this.name = name;
        this.powerCapacity = powerCapacity;
        this.emRadiation = emRadiation;
    }

    /** @return unique identifier */
    public String getId() { return id; }

    /** @return display name */
    public String getName() { return name; }

    /** Sets the display name. */
    public void setName(String name) { this.name = name; }

    /** @return rated power capacity in watts */
    public double getpowerCapacity() { return powerCapacity; }

    /** @return true if the appliance is plugged in */
    public boolean isPluggedIn() { return pluggedIn; }

    /** @return true if the appliance is turned on */
    public boolean isTurnedOn() { return turnedOn; }

    /** @return electromagnetic radiation value */
    public double getEmRadiation() { return emRadiation; }

    /** Plugs the appliance into the socket. */
    public void plugIn() { this.pluggedIn = true; }

    /** Unplugs the appliance (also turns it off). */
    public void unplug() {
        this.turnedOn = false;
        this.pluggedIn = false;
    }

    /**
     * Turns on the appliance.
     *
     * @throws IllegalStateException if the appliance is not plugged in
     */
    public void turnOn() throws IllegalStateException {
        if (!pluggedIn) throw new IllegalStateException("Cannot turn on: device is not plugged in");
        this.turnedOn = true;
    }

    /** Turns off the appliance. */
    public void turnOff() { this.turnedOn = false; }

    /**
     * Returns the current power consumption in watts.
     * <p>
     * Subclasses may override this to provide device-specific behavior.
     * </p>
     *
     * @return current power consumption
     */
    public double currentPowerConsumption() { return turnedOn ? powerCapacity : 0.0; }

    @Override
    public String toString() {
        return String.format("%s[%s] power=%.1fW plugged=%b on=%b em=%.2f",
                this.getClass().getSimpleName(), id, powerCapacity, pluggedIn, turnedOn, emRadiation);
    }
}
