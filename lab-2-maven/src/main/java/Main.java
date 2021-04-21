import comparators.SymbolComparator;

import modul.Word;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that demonstrates how to work with the developed classes.
 *
 * @author Nikita Pozniak
 * @version 1.0 25.04.2021
 */
public class Main {

    /**
     * A method that reads text from a file and splits it into words, phone numbers, and email.
     *
     * @return - returns a list of words in this text
     * @throws FileNotFoundException - throw an exception if the file is missing
     */
    public static List<Word> parsingText() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./target/classes/inputText.txt"));
        List<Word> text = new ArrayList<>();

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();

            String mailRegex = "([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}";
            Pattern patternMail = Pattern.compile(mailRegex);
            Matcher matcherMail = patternMail.matcher(line);

            while (matcherMail.find()) {
                text.add(new Word(matcherMail.group()));
                line = line.replace(matcherMail.group(),"");
            }

            String phoneRegex = "\\+\\d{3}\\(\\d{2}\\)\\d{3}-\\d{2}-\\d{2}\\b";
            Pattern patternPhone = Pattern.compile(phoneRegex);
            Matcher matcherPhone = patternPhone.matcher(line);

            while (matcherPhone.find()) {
                text.add(new Word(matcherPhone.group()));
                line = line.replace(matcherPhone.group(),"");
            }

            String wordRegex = "\\b([a-zA-Zа-яА-ЯёЁ_']+)\\b";
            Pattern patternWord = Pattern.compile(wordRegex);
            Matcher matcherWord = patternWord.matcher(line);

            while (matcherWord.find()) {
                text.add(new Word(matcherWord.group()));
            }

        }
        scanner.close();
        return text;
    }

    /**
     *
     * @param text
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
        List<Word> text = parsingText();
        text.sort(new SymbolComparator(' '));
        show(text);
    }
}