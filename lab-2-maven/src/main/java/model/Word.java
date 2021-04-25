package model;

/**
 * A class that describing the word.
 *
 * @author Nikita Pozniak
 * @version 1.0 25.04.2021
 */
public class Word{
    private String word;

    /**
     * Constructor of the Word class.
     *
     * @param word - naming parameter
     */
    public Word(String word) {
        this.word = word;
    }

    /**
     * Getter
     *
     * @return Returns the specified parameters
     */
    public String getWord() {
        return word;
    }

    /**
     * Setter. Set the specified parameters
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Method that returns the number of occurrences of the specified character in the word.
     *
     * @param symbol - the character whose occurrence will be found.
     * @return - number of occurrences
     */
    public long inclusion(char symbol) {
        return word.chars().filter(ch -> ch ==symbol).count();
    }

    /**
     * @return Returns the string used for output
     */
    @Override
    public String toString() {
        return word;
    }
}