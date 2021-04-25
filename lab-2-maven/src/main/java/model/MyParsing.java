package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that describing the word.
 *
 * @author Nikita Pozniak
 * @version 1.0 25.04.2021
 */
public class MyParsing implements Parsing {
    private String mailRegex = "([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}";
    private String phoneRegex = "\\+\\d{3}\\(\\d{2}\\)\\d{3}-\\d{2}-\\d{2}\\b";
    private String wordRegex = "\\b([a-zA-Zа-яА-ЯёЁ_']+)\\b";

    /**
     * Getters
     *
     * @return Returns the specified parameters
     */
    public String getMailRegex() {
        return mailRegex;
    }

    public String getPhoneRegex() {
        return phoneRegex;
    }

    public String getWordRegex() {
        return wordRegex;
    }

    /**
     * Setters. Set the specified parameters
     */
    public void setMailRegex(String mailRegex) {
        this.mailRegex = mailRegex;
    }

    public void setPhoneRegex(String phoneRegex) {
        this.phoneRegex = phoneRegex;
    }

    public void setWordRegex(String wordRegex) {
        this.wordRegex = wordRegex;
    }

    /**
     * A method that reads text from a file and splits it into words, phone numbers, and email.
     *
     * @param file - the object of class File
     * @return - returns a list of words in this text
     * @throws FileNotFoundException - throw an exception if the file is missing
     */
    @Override
    public List<Word> parse(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        List<Word> text = new ArrayList<>();

        Pattern patternMail = Pattern.compile(mailRegex);
        Pattern patternPhone = Pattern.compile(phoneRegex);
        Pattern patternWord = Pattern.compile(wordRegex);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Matcher matcherMail = patternMail.matcher(line);
            Matcher matcherPhone = patternPhone.matcher(line);
            Matcher matcherWord = patternWord.matcher(line);

            while (matcherMail.find()) {
                text.add(new Word(matcherMail.group()));
                line = line.replace(matcherMail.group(), "");
            }
            while (matcherPhone.find()) {
                text.add(new Word(matcherPhone.group()));
                line = line.replace(matcherPhone.group(), "");
            }
            while (matcherWord.find()) {
                text.add(new Word(matcherWord.group()));
            }

        }
        scanner.close();
        return text;
    }
}
