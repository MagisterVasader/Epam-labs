package model;

import java.util.Comparator;
import java.util.List;

/**
 * Abstract airport class that implements the interface of a transport building. Used to create
 * different airports.
 *
 * @author Nikita Pozniak
 * @version 1.0 19.03.2021
 */
public abstract class Airport implements TransportBuilding {

    protected String name;
    protected String location;
    protected String[] content;
    protected int floors;
    protected double square;
    protected Plane[] planes;

    /**
     * A method that sorts vehicles belonging to this company by the selected parameter
     *
     * @param comparator A comparator that allows you to sort aircraft by the criteria entered by the
     *                   use
     */
    public abstract void sort(Comparator<Plane> comparator);

    /**
     * A method that allows you to find the indexes of the aircraft by the specified filter.
     *
     * @param filter A parameter that specifies by which criteria to select an aircraft
     * @return List of aircraft indexes
     */
    public abstract List<Integer> find(Filter filter);

    /**
     * Getters.
     *
     * @return Returns the specified parameters
     */
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getFloors() {
        return floors;
    }

    public double getSquare() {
        return square;
    }

    public Plane[] getPlanes() {
        return planes;
    }

    public String[] getContent() {
        return content;
    }

    /**
     * Setters. Set the specified parameters
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public void setPlanes(Plane[] planes) {
        this.planes = planes;
    }

    public void setContent(String[] content) {
        this.content = content;
    }


}
