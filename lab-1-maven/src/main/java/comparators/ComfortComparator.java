package comparators;

import model.Plane;

import java.util.Comparator;

/**
 * A class that implements the comparator interface.
 *
 * @author Nikita Pozniak
 * @version 1.0 19.03.2021
 */
public class ComfortComparator implements Comparator<Plane> {

    /**
     * Overriding the compare method.
     *
     * @param plane1 The first plane to compare
     * @param plane2 The second plane for comparison
     * @return Returns 0 if the specified parameters match, a positive number if the first plane is
     * larger, otherwise a negative number
     */
    @Override
    public int compare(Plane plane1, Plane plane2) {
        return plane1.getType().comfort() - plane2.getType().comfort();
    }
}
