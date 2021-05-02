package com.lab3.model;

import java.util.Map;

/**
 * The class that describes the military aircraft.
 *
 * @author Nikita Pozniak
 * @version 1.0 02.03.2021
 */
public class Plane {
    private String identifier;
    private String model;
    private String origin;
    private Map<String, String> chars;
    private Map<String, Double> parameters;
    private Integer price;

    /**
     * @return ID of the plane.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier ID of the plane.
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @return model of the plane.
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model model of the plane.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return country of production.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin country of production.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * @return a HashMap with the characteristics of the aircraft
     * (ammo?, (2 types of rockets)?, capacity).
     */
    public Map<String, String> getChars() {
        return chars;
    }

    /**
     * @param chars a HashMap with the characteristics of the aircraft
     *              (ammo?, (2 types of rockets)?, capacity).
     */
    public void setChars(Map<String, String> chars) {
        this.chars = chars;
    }

    /**
     * @return a HashMap with dimensions of the aircraft
     * ((length)?, (weight)?, (height)?).
     */
    public Map<String, Double> getParameters() {
        return parameters;
    }

    /**
     * @param parameters a HashMap with dimensions of the aircraft
     *                   ((length)?, (weight)?, (height)?).
     */
    public void setParameters(Map<String, Double> parameters) {
        this.parameters = parameters;
    }

    /**
     * @return price of the aircraft.
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @param price price of the aircraft (in thalers).
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "identifier='" + identifier + '\'' +
                ", model='" + model + '\'' +
                ", origin='" + origin + '\'' +
                ", chars=" + chars +
                ", parameters=" + parameters +
                ", price=" + price +
                '}';
    }
}
