package model;

import java.util.Arrays;

/**
 * A class that implements the vehicle representation.
 *
 * @author Nikita Pozniak
 * @version 1.0 19.03.2021
 */
public class Transport {

    protected Engine engine;
    protected int capacity;
    protected String[] content;
    protected double weight;

    /**
     * Constructor of the transport class
     *
     * @param engine   Engine type
     * @param capacity Capacity of people per unit of transport
     * @param content  Materials used to create transport
     * @param weight   Vehicle weight
     */
    public Transport(Engine engine, int capacity, String[] content, double weight) {
        this.engine = engine;
        this.capacity = capacity;
        this.content = content;
        this.weight = weight;
    }

    /**
     * Getters.
     *
     * @return Returns the specified parameters
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Setters. Set the specified parameters
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String[] getContent() {
        return content;
    }

    public void setContent(String[] content) {
        this.content = content;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return Returns the string used for output
     */
    @Override
    public String toString() {
        return "Transport{" +
                "engine=" + engine +
                ", capacity=" + capacity +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
