package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a household that contains and manages a collection of electrical appliances.
 * <p>
 * Provides operations for:
 * <ul>
 *   <li>Adding and removing appliances</li>
 *   <li>Plugging in and turning on appliances</li>
 *   <li>Calculating total power consumption</li>
 *   <li>Sorting appliances by rated power</li>
 *   <li>Searching appliances by electromagnetic radiation range</li>
 * </ul>
 * </p>
 *
 * @author
 * @version 1.0
 */
public class Home {
    private final List<Appliance> allElectroAppliances = new ArrayList<>();

    /**
     * Adds an appliance to the home.
     *
     * @param a the appliance to add (must not be {@code null})
     * @throws NullPointerException if {@code a} is null
     */
    public void addAppliance(Appliance a) {
        Objects.requireNonNull(a);
        this.allElectroAppliances.add(a);
    }

    /**
     * Removes an appliance by its unique ID.
     *
     * @param id the ID of the appliance to remove
     * @throws ApplianceNotFoundException if no appliance with the given ID exists
     */
    public void removeApplianceById(String id) throws ApplianceNotFoundException {
        boolean removed = allElectroAppliances.removeIf(a -> a.getId().equals(id));
        if (!removed) throw new ApplianceNotFoundException("Appliance with id '" + id + "' not found");
    }

    /**
     * Plugs in and turns on an appliance by its ID.
     *
     * @param id the ID of the appliance to activate
     * @throws ApplianceNotFoundException if no appliance with the given ID exists
     * @throws IllegalStateException      if the appliance cannot be turned on
     */
    public void plugInAndTurnOn(String id) throws ApplianceNotFoundException {
        Appliance a = findApplianceById(id);
        a.plugIn();
        a.turnOn();
    }

    /**
     * Finds an appliance by its unique ID.
     *
     * @param id the ID of the appliance
     * @return the appliance with the specified ID
     * @throws ApplianceNotFoundException if no appliance with the given ID exists
     */
    public Appliance findApplianceById(String id) throws ApplianceNotFoundException {
        return allElectroAppliances.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ApplianceNotFoundException("Appliance with id '" + id + "' not found"));
    }

    /**
     * Calculates the total power consumption of all turned-on appliances in the home.
     *
     * @return total power consumption in watts
     */
    public double totalPowerConsumption() {
        return allElectroAppliances.stream().mapToDouble(Appliance::currentPowerConsumption).sum();
    }

    /**
     * Returns a sorted list of appliances by their rated power.
     *
     * @param ascending {@code true} for ascending order, {@code false} for descending
     * @return a new sorted list of appliances
     */
    public List<Appliance> sortedByPowerConsumption(boolean ascending) {
        return allElectroAppliances.stream()
                .sorted(ascending ? Comparator.comparingDouble(Appliance::getpowerCapacity)
                        : Comparator.comparingDouble(Appliance::getpowerCapacity).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Finds appliances with electromagnetic radiation within the given range (inclusive).
     *
     * @param min minimum radiation value
     * @param max maximum radiation value
     * @return list of matching appliances
     * @throws IllegalArgumentException if {@code min > max}
     */
    public List<Appliance> findByEmRange(double min, double max) {
        if (min > max) throw new IllegalArgumentException("minInclusive > maxInclusive");
        return allElectroAppliances.stream()
                .filter(a -> a.getEmRadiation() >= min && a.getEmRadiation() <= max)
                .collect(Collectors.toList());
    }

    /**
     * Returns all appliances in the home.
     *
     * @return a copy of the list of appliances
     */
    public List<Appliance> getallElectroAppliances() {
        return new ArrayList<>(allElectroAppliances);
    }
}
