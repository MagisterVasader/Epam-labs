package com.lab3.xml;

import com.lab3.model.Plane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that describes the reader of an XML file.
 *
 * @author Nikita Pozniak
 * @version 1.0 02.03.2021
 */
public class PlaneXMLReader {
    public static final Logger LOGGER = LogManager.getLogger(PlaneXMLReader.class);

    /**
     * A method that splits an XML file by tags and generates an array of
     * necessary data about aircraft.
     *
     * @param fileName a string containing the file name.
     * @return array of planes read from the specified XML file.
     */
    public List<Plane> read(String fileName) throws XMLStreamException {
        XMLStreamReader xmlStreamReader = null;
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            List<Plane> planes = null;
            Plane plane = null;
            XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
            xmlStreamReader = xmlInputFactory.createXMLStreamReader(fileInputStream);

            while (xmlStreamReader.hasNext()) {
                int type = xmlStreamReader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    String tagName = xmlStreamReader.getLocalName();
                    switch (tagName) {
                        case "planes" -> planes = new ArrayList<>();
                        case "plane" -> {
                            plane = new Plane();
                            plane.setIdentifier(xmlStreamReader.getAttributeValue(null, "id"));
                        }
                        case "model" -> plane.setModel(xmlStreamReader.getElementText());
                        case "origin" -> plane.setOrigin(xmlStreamReader.getElementText());
                        case "chars" -> plane.setChars(new HashMap<>());
                        case "type" -> plane.getChars().put("type", "");
                        case "destroyer", "sniffer", "scout", "escort", "support" -> plane.getChars().put("type", tagName);
                        case "ammo" -> plane.getChars().put("ammo", xmlStreamReader.getAttributeValue(null, "weapon"));
                        case "AARocket", "AERocket", "capacity" -> plane.getChars().put(tagName, xmlStreamReader.getElementText());
                        case "parameter" -> {
                            Map<String, Double> parameters = new HashMap<>();
                            String length = xmlStreamReader.getAttributeValue(null, "length");
                            String weight = xmlStreamReader.getAttributeValue(null, "weight");
                            String height = xmlStreamReader.getAttributeValue(null, "height");
                            parameters.put("length", length != null ? Double.parseDouble(length) : 0);
                            parameters.put("weight", weight != null ? Double.parseDouble(weight) : 0);
                            parameters.put("height", height != null ? Double.parseDouble(height) : 0);
                            plane.setParameters(parameters);
                        }
                        case "price" -> plane.setPrice(Integer.parseInt(xmlStreamReader.getElementText()));
                        default -> LOGGER.error("Unknown tag: {}", tagName);
                    }
                } else if (type == XMLStreamConstants.END_ELEMENT) {
                    String tagName = xmlStreamReader.getLocalName();
                    if ("plane".equals(tagName)) {
                        planes.add(plane);
                    }
                }
            }
            return planes;
        } catch (XMLStreamException | IOException e) {
            LOGGER.error(e.getLocalizedMessage());
            return new ArrayList<>();
        } finally {
            assert xmlStreamReader != null;
            xmlStreamReader.close();
        }
    }
}