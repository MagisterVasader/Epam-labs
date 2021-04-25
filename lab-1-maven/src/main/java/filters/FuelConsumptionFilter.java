package filters;

import model.Filter;
import model.Plane;

/**
 * A class that implements the Filter interface.
 *
 * @author Nikita Pozniak
 * @version 1.0 19.03.2021
 */
public class FuelConsumptionFilter implements Filter {

    private double minFuelConsumption;
    private double maxFuelConsumption;

    /**
     * Class Constructor.
     *
     * @param minFuelConsumption Minimum value for comparison
     * @param maxFuelConsumption Maximum value for comparison
     */
    public FuelConsumptionFilter(double minFuelConsumption, double maxFuelConsumption) {
        this.minFuelConsumption = minFuelConsumption;
        this.maxFuelConsumption = maxFuelConsumption;
    }

    /**
     * Getters.
     *
     * @return Returns the specified parameters
     */
    public double getMinFuelConsumption() {
        return minFuelConsumption;
    }

    /**
     * Setters. Set the specified parameters
     */
    public void setMinFuelConsumption(double minFuelConsumption) {
        this.minFuelConsumption = minFuelConsumption;
    }

    public double getMaxFuelConsumption() {
        return maxFuelConsumption;
    }

    public void setMaxFuelConsumption(double maxFuelConsumption) {
        this.maxFuelConsumption = maxFuelConsumption;
    }

    /**
     * Overriding a method to perform object filtering.
     *
     * @param plane The aircraft being checked.
     * @return Returns true if suitable otherwise false.
     */
    @Override
    public boolean check(Plane plane) {
        return (minFuelConsumption <= plane.getFuelConsumption()) && (plane.getFuelConsumption()
                <= maxFuelConsumption);
    }

    /**
     * @return Returns the string used for output
     */
    @Override
    public String toString() {
        return "FuelConsumptionFilter{" +
                "minFuelConsumption=" + minFuelConsumption +
                ", maxFuelConsumption=" + maxFuelConsumption +
                '}';
    }
}
