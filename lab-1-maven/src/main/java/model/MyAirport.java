package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * A class that implements the airport representation.
 *
 * @author Nikita Pozniak
 * @version 1.0 19.03.2021
 */
public class MyAirport extends Airport {
    /* The class inherits from the abstract Airport class.*/

    /**
     * Constructor of the MyAirport class.
     *
     * @param name     Airport Name
     * @param location Location airport
     * @param content  Materials used for airport construction
     * @param floors   Number of floors in the airport
     * @param square   Building area (NOT including runways and landing strips)
     * @param planes   Aircraft registered at the airport
     */
    public MyAirport(String name, String location, String[] content, int floors, double square,
                     Plane[] planes) {
        this.name = name;
        this.location = location;
        this.content = content;
        this.floors = floors;
        this.square = square;
        this.planes = planes;
    }

    /**
     * @return Returns the total capacity of the aircraft at the airport
     */
    @Override
    public int capacity() {
        int places = 0;
        for (Plane plane : getPlanes()) {
            places += plane.getCapacity();
        }
        return places;
    }

    /**
     * @return Returns the total aircraft load capacity at the airport
     */
    @Override
    public int loadCapacity() {
        int loadCapacity = 0;
        for (Plane plane : getPlanes()) {
            loadCapacity += plane.getLoadCapacity();
        }
        return loadCapacity;
    }

    /**
     * @param comparator Comparator used to sort an array of planes
     */
    @Override
    public void sort(Comparator<Plane> comparator) {
        Arrays.sort(getPlanes(), comparator);
    }

    /**
     * @param filter The filter used to determine suitable aircraft
     * @return Returns a list of indexes for the appropriate aircraft
     */
    @Override
    public List<Integer> find(Filter filter) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < getPlanes().length; i++) {
            if (filter.check(getPlanes()[i])) {
                list.add(i + 1);
            }
        }
        return list;
    }

    /**
     * @return Returns the string used for output
     */
    @Override
    public String toString() {
        return "MyAirport{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", content=" + Arrays.toString(content) +
                ", floors=" + floors +
                ", square=" + square +
                ", planes=" + Arrays.toString(planes) +
                '}';
    }
}
