package model;

/**
 * Listing engine types.
 *
 * @author Nikita Pozniak
 * @version 1.0 19.03.2021
 */
public enum Engine {
    electronic {
        public String getEngineInfo() {
            return "Electricity";
        }

        public double getVolume() {
            return 2650;
        }
    },
    internalCombustion {
        public String getEngineInfo() {
            return "petrol";
        }

        public double getVolume() {
            return 1.2;
        }
    },
    steam {
        public String getEngineInfo() {
            return "Methane";
        }

        public double getVolume() {
            return 140;
        }
    },
    aviation {
        public String getEngineInfo() {
            return "Aviation fuel";
        }

        public double getVolume() {
            return 2500;
        }
    };

    /**
     * Abstract method that returns information about the type of fuel used
     *
     * @return Returns type of fuel used
     */
    public abstract String getEngineInfo();

    /**
     * Abstract method that returns information about the engine volume
     *
     * @return Returns engine capacity
     */
    public abstract double getVolume();
}
