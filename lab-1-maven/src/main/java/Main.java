import model.Engine;
import model.MyAirport;
import model.Plane;
import model.Type;
import comparators.FlightRangeComparator;
import filters.FuelConsumptionFilter;

import java.util.List;
import java.util.Random;

/**
 * A class that implements a demonstration of the created classes.
 *
 * @author Nikita Pozniak
 * @version 1.0 19.03.2021
 */
public class Main {

    /**
     * A method that randomly fills in the parameters of aircraft.
     *
     * @param count Number of aircraft.
     * @return Returns a full-fledged airport.
     */
    public static MyAirport airport(int count) {
        Random random = new Random();
        Plane[] planes = new Plane[count];

        for (int i = 0; i < count; i++) {
            Random randomType = new Random();
            Type type;
            if (randomType.nextInt(2) == 1) {
                type = Type.passenger;
            } else {
                type = Type.cargo;
            }
            Plane plane = new Plane(
                    Engine.aviation,
                    random.nextInt(100) + random.nextInt(232),
                    new String[]{"content1", "content2", "content3", "content4",},
                    random.nextInt(100) + random.nextInt(2),
                    type,
                    random.nextInt(1000) + 0.1,
                    "20.10.2021",
                    random.nextInt(3000) + 0.2,
                    random.nextInt(5000) + 0.5
            );
            planes[i] = plane;
        }
        return new MyAirport(
                "Airport TEST",
                "Minsk",
                new String[]{"content1", "content2", "content3", "content4"},
                3,
                11.2,
                planes
        );
    }

    public static void main(String[] args) {
        MyAirport airport = airport(10);

        System.out.println("Capacity :" + airport.capacity() + "\n");
        System.out.println("Load capacity : " + airport.loadCapacity() + "\n");

        airport.sort(new FlightRangeComparator());

        for (Plane plane : airport.getPlanes()) {
            System.out.println(plane);
        }
        System.out.println();

        List<Integer> list = airport.find(new FuelConsumptionFilter(0, 1000));

        System.out.print("Plane indexes:" + list + "\n");
    }
}
