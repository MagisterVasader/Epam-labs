package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * An interface that implements the concept of a parser for text, in a given file/
 *
 * @author Nikita Pozniak
 * @version 1.0 25.04.2021
 */
public interface Parsing {

    /**
     * A method that reads text from a file and splits it into words, phone numbers, and email.
     *
     * @param file - the object of class File
     * @return - returns a list of words in this text
     * @throws FileNotFoundException - throw an exception if the file is missing
     */
    List<?> parse(File file) throws FileNotFoundException;
}
