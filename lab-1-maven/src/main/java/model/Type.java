package model;

/**
 * Listing plane types
 *
 * @author Nikita Pozniak
 * @version 1.0 19.03.2021
 */
public enum Type {
    passenger {
        public String getName() {
            return "Boeing 737-800";
        }

        public int comfort() {
            return 10;
        }
    },
    cargo {
        public String getName() {
            return "Boeing 747";
        }

        public int comfort() {
            return 0;
        }
    };

    /**
     * Abstract method that returns name of plane
     *
     * @return Returns name of plane
     */
    public abstract String getName();

    /**
     * Abstract method that returns comfort of the aircraft
     *
     * @return Returns comfort of the aircraft
     */
    public abstract int comfort();
}
