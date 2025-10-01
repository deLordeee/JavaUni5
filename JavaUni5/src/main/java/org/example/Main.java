package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.List;

/**
 * Main application class demonstrating the electrical appliance management system.
 * <p>
 * This console-based application shows the following operations:
 * <ul>
 *   <li>Creating various types of electrical appliances</li>
 *   <li>Adding appliances to a home</li>
 *   <li>Plugging in and turning on appliances</li>
 *   <li>Calculating total power consumption</li>
 *   <li>Sorting appliances by power capacity</li>
 *   <li>Searching for appliances by electromagnetic radiation range</li>
 *   <li>Handling exceptions for invalid operations</li>
 * </ul>
 * </p>
 *
 * @author Butkevych Yevhenii Oleksandrovych
 * @version 1.0
 * @since 2025-01-01
 */
public class Main {

    /**
     * Entry point of the application.
     * <p>
     * Demonstrates the creation and management of electrical appliances:
     * a refrigerator, television, and microwave. Shows how to control them,
     * calculate power consumption, and perform search and sort operations.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create home instance to manage appliances
        Home home = new Home();

        // Create various appliances with different characteristics
        Refrigerator fridge = new Refrigerator("fridge", "LG Fridge", 150.0, 0.8, 4.0);
        Television tv = new Television("tv", "Samsung TV", 120.0, 1.5);
        Microwave micro = new Microwave("microwave", "Panasonic Microwave", 1000.0, 5.0);

        // Add appliances to the home
        home.addAppliance(fridge);
        home.addAppliance(tv);
        home.addAppliance(micro);

        // Plug in and turn on some appliances
        try {
            home.plugInAndTurnOn("fridge");
            home.plugInAndTurnOn("tv");

            // Plug in microwave but don't turn it on
            micro.plugIn();
        } catch (ApplianceNotFoundException | IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Display total power consumption
        System.out.printf("Total power consumption: %.2f W\n", home.totalPowerConsumption());

        // Sort appliances by rated power (descending order)
        System.out.println("\nSorted by rated power (descending):");
        List<Appliance> sortedByPower = home.sortedByPowerConsumption(false);
        sortedByPower.forEach(System.out::println);

        // Find appliances within electromagnetic radiation range
        System.out.println("\nFind appliances with EM radiation in range [0.5, 2.0]:");
        List<Appliance> foundByEM = home.findByEmRange(0.5, 2.0);
        foundByEM.forEach(System.out::println);

        // Demonstrate exception handling
        try {
            home.removeApplianceById("nonexistent");
        } catch (ApplianceNotFoundException e) {
            System.err.println("\nHandled exception: " + e.getMessage());
        }
    }
}