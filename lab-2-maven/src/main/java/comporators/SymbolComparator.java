package comporators;

import modul.Component;
import java.util.Comparator;

public class SymbolComparator implements Comparator<Component> {
    private char symbol;

    public SymbolComparator(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int compare(Component component1, Component component2) {
        if (component1.inclusion(symbol) == component2.inclusion(symbol)) {
            return component1.toString().compareTo(component2.toString());
        } else {
            return component1.inclusion(symbol) - component2.inclusion(symbol);
        }
    }
}