import comporators.SymbolComparator;
import modul.Component;
import modul.Composite;
import modul.Word;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static Composite parsingText() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./target/classes/inputText.txt"));
        Composite composite = new Composite(new ArrayList<>());
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();

            String mailRegex = "([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}";
            Pattern patternMail = Pattern.compile(mailRegex);
            Matcher matcherMail = patternMail.matcher(line);

            while (matcherMail.find()) {
                composite.add(new Word(matcherMail.group()));
                line = line.replace(matcherMail.group(),"");
            }

            String phoneRegex = "\\+\\d{3}\\(\\d{2}\\)\\d{3}-\\d{2}-\\d{2}";
            Pattern patternPhone = Pattern.compile(phoneRegex);
            Matcher matcherPhone = patternPhone.matcher(line);

            while (matcherPhone.find()) {
                composite.add(new Word(matcherPhone.group()));
                line = line.replace(matcherPhone.group(),"");
            }

            String wordRegex = "\\b([a-zA-Zа-яА-ЯёЁ_']+)\\b";
            Pattern patternWord = Pattern.compile(wordRegex);
            Matcher matcherWord = patternWord.matcher(line);

            while (matcherWord.find()) {
                composite.add(new Word(matcherWord.group()));
            }

        }
        scanner.close();
        return composite;
    }

    public static void main(String[] args) throws FileNotFoundException {
        char symbol = 'e';
        Composite composite = parsingText();
        composite.sort(new SymbolComparator(symbol));
        int counter = 0;
        for (Component component: composite.getText()) {
            if (counter == 10){
                counter = 0;
                System.out.println();
            }
            System.out.print(component);
            counter++;
        }
    }
}