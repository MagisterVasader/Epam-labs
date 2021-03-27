package model;

/**
 * An interface that implements the concept of a filter for selecting a transport that meets the
 * specified requirements
 *
 * @author Nikita Pozniak
 * @version 1.0 19.03.2021
 */
public interface Filter {

    /**
     * Method of checking for compliance with the specified condition
     *
     * @param plane The aircraft being checked
     * @return True, if it fits, false if it doesn't
     */
    boolean check(Plane plane);
}
