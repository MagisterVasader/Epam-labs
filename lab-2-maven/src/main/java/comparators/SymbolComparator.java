package comparators;

import modul.Word;

import java.util.Comparator;

/**
 * A class that implements the comparator interface.
 *
 * @author Nikita Pozniak
 * @version 1.0 25.04.2021
 */
public class SymbolComparator implements Comparator<Word> {
    private char symbol;

    /**
     * Constructor of the SymbolComparator class.
     *
     * @param symbol - the character that will be used to compare two words.
     */
    public SymbolComparator(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Getter
     *
     * @return Returns the specified parameters
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Setter. Set the specified parameters
     */
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Overriding the compare method.
     *
     * @param word1 - the first word to compare
     * @param word2 - the second word to compare
     * @return - returns a positive number if the first word has more occurrences of the specified
     * character than the second, if the opposite is true, then it is negative. If the number of
     * occurrences is the same, then they are compared in alphabetical order
     */
    public int compare(Word word1, Word word2) {
        if (word1.inclusion(symbol) == word2.inclusion(symbol)) {
            return word1.toString().compareTo(word2.toString());
        } else {
            return (int) (word1.inclusion(symbol) - word2.inclusion(symbol));
        }
    }
}