package com.lab3;

import com.lab3.model.Plane;
import com.lab3.xml.PlaneXMLReader;
import com.lab3.xml.PlaneXMLValidator;
import com.lab3.xml.PlaneXMLWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * A class that demonstrates the use of the created classes.
 *
 * @author Nikita Pozniak
 * @version 1.0 02.03.2021
 */
public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException, XMLStreamException {
        PlaneXMLValidator planeXMLValidator = new PlaneXMLValidator("src/main/resources/planes.xml");
        if (planeXMLValidator.validate()) {
            PlaneXMLReader planeXMLReader = new PlaneXMLReader();
            List<Plane> planes = planeXMLReader.read("src/main/resources/planes.xml");

            LOGGER.info("Read:");
            for (Plane plane : planes) {
                LOGGER.info(plane);
            }

            planes.sort(Comparator.comparingInt(Plane::getPrice));

            LOGGER.info("Sort");
            for (Plane plane : planes) {
                LOGGER.info(plane);
            }

            PlaneXMLWriter planeXMLWriter = new PlaneXMLWriter();
            planeXMLWriter.write(planes, "src/main/resources/planes-test.xml");
            LOGGER.info("Writing done, look at the file planes-test.xml");
        } else {
            LOGGER.info("Error:{}", planeXMLValidator.getError());
        }
    }
}
