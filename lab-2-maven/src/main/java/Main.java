import comparators.SymbolComparator;

import model.MyParsing;
import model.Word;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * A class that demonstrates how to work with the developed classes.
 *
 * @author Nikita Pozniak
 * @version 1.0 25.04.2021
 */
public class Main {

    /**
     * A method that outputs 10 words per line of text to the console.
     *
     * @param text Text represented as an array of words.
     */
    public static void show(List<Word> text){
        int counter = 0;
        for (Word word: text) {
            if (counter == 10){
                counter = 0;
                System.out.println();
            }
            System.out.print(word + " ");
            counter++;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        MyParsing myParsing = new MyParsing();
        List<Word> text = myParsing.parse(new File("src\\main\\resources\\inputText.txt"));
        text.sort(new SymbolComparator('e'));
        show(text);
    }
}