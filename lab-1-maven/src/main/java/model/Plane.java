package model;

/**
 * The aircraft class inherited from the transport class is a set of characteristics inherent in the
 * aircraft.
 *
 * @author Nikita Pozniak
 * @version 1.0 19.03.2021
 */
public class Plane extends Transport {

    private Type type;
    private double loadCapacity;
    private String buildDate;
    private double fuelConsumption;
    private double flightRange;

    /**
     * Constructor of the airplane class.
     *
     * @param engine          Engine type
     * @param capacity        Aircraft capacity
     * @param content         Materials used to create the aircraft
     * @param weight          Aircraft weight
     * @param type            Type of aircraft (passenger, cargo)
     * @param loadCapacity    Aircraft load capacity
     * @param buildDate       Date of creation of the aircraft
     * @param fuelConsumption Aircraft fuel consumption
     * @param flightRange     Flight range
     */
    public Plane(Engine engine, int capacity, String[] content, double weight, Type type,
                 double loadCapacity, String buildDate, double fuelConsumption, double flightRange) {
        super(engine, capacity, content, weight);
        this.type = type;
        this.loadCapacity = loadCapacity;
        this.buildDate = buildDate;
        this.fuelConsumption = fuelConsumption;
        this.flightRange = flightRange;
    }

    /**
     * Getters.
     *
     * @return Returns the specified parameters
     */
    public double getFuelConsumption() {
        return fuelConsumption;
    }

    /**
     * Setters. Set the specified parameters
     */
    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getFlightRange() {
        return flightRange;
    }

    public void setFlightRange(double flightRange) {
        this.flightRange = flightRange;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    /**
     * @return Returns the string used for output
     */
    @Override
    public String toString() {
        return "Plane{" +
                "type = " + type +
                ", loadCapacity = " + loadCapacity +
                ", buildDate = '" + buildDate + '\'' +
                ", fuelConsumption = " + fuelConsumption +
                ", flightRange = " + flightRange +
                '}';
    }
}
