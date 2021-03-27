package model;

/**
 * An interface that implements a transport building intended for the transportation of goods or the
 * transportation of passengers.
 *
 * @author Nikita Pozniak
 * @version 1.0 19.03.2021
 */
public interface TransportBuilding {

    /**
     * Method that returns the maximum capacity of all vehicles belonging to this building.
     *
     * @return Returns maximum capacity
     */
    int capacity();

    /**
     * Method that returns the maximum load capacity of all vehicles belonging to this building.
     *
     * @return Returns maximum load capacity
     */
    int loadCapacity();
}