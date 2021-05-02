package com.lab3.model;

import java.util.Map;

public class Plane {
    private String identifier;
    private String model;
    private String origin;
    private Map<String, String> chars;
    private Map<String, Double> parameters;
    private Integer price;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Map<String, String> getChars() {
        return chars;
    }

    public void setChars(Map<String, String> chars) {
        this.chars = chars;
    }

    public Map<String, Double> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Double> parameters) {
        this.parameters = parameters;
    }

    public Integer getPrice() {
        return price;
    }

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
