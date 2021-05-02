package com.lab3.xml;

import com.lab3.model.Plane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * A class that describes how to write aircraft data to an XML file.
 *
 * @author Nikita Pozniak
 * @version 1.0 02.03.2021
 */
public class PlaneXMLWriter {
    public static final Logger LOGGER = LogManager.getLogger(PlaneXMLWriter.class);

    /**
     * A method that creates an XML file and writes information about aircraft to it.
     *
     * @param planes   Array of planes.
     * @param fileName a string containing the file name.
     */
    public void write(List<Plane> planes, String fileName) throws XMLStreamException {
        String constant1 = "AARocket";
        String constant2 = "AERocket";

        XMLStreamWriter xmlStreamWriter = null;
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(fileOutputStream, "UTF-8");

            xmlStreamWriter.writeStartDocument("UTF-8", "1.0");
            xmlStreamWriter.writeStartElement("planes");
            xmlStreamWriter.writeAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            xmlStreamWriter.writeAttribute("xmlns", "https://www.example.org/planes");
            xmlStreamWriter.writeAttribute("xsi:schemaLocation", "https://www.example.org/planes planes.xsd");

            for (Plane plane : planes) {
                xmlStreamWriter.writeStartElement("plane");
                xmlStreamWriter.writeAttribute("id", plane.getIdentifier());
                xmlStreamWriter.writeStartElement("model");
                xmlStreamWriter.writeCData(plane.getModel());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeStartElement("origin");
                xmlStreamWriter.writeCharacters(plane.getOrigin());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeStartElement("chars");
                xmlStreamWriter.writeStartElement("type");
                xmlStreamWriter.writeStartElement(plane.getChars().get("type"));
                if (plane.getChars().containsKey("ammo")) {
                    xmlStreamWriter.writeStartElement("ammo");
                    if (plane.getChars().get("ammo") != null) {
                        xmlStreamWriter.writeAttribute("weapon", plane.getChars().get("ammo"));
                    }
                    xmlStreamWriter.writeEndElement();
                }
                if (plane.getChars().containsKey(constant1)) {
                    xmlStreamWriter.writeStartElement(constant1);
                    xmlStreamWriter.writeCharacters(plane.getChars().get(constant1));
                    xmlStreamWriter.writeEndElement();
                }
                if (plane.getChars().containsKey(constant2)) {
                    xmlStreamWriter.writeStartElement(constant2);
                    xmlStreamWriter.writeCharacters(plane.getChars().get(constant2));
                    xmlStreamWriter.writeEndElement();
                }
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeStartElement("capacity");
                xmlStreamWriter.writeCharacters(plane.getChars().get("capacity"));
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeStartElement("parameter");
                xmlStreamWriter.writeAttribute("length", plane.getParameters().get("length").toString());
                xmlStreamWriter.writeAttribute("weight", plane.getParameters().get("weight").toString());
                xmlStreamWriter.writeAttribute("height", plane.getParameters().get("height").toString());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeStartElement("price");
                xmlStreamWriter.writeCharacters(plane.getPrice().toString());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeEndElement();
            }
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();
        } catch (IOException | XMLStreamException e) {
            LOGGER.error(e.getLocalizedMessage());
        } finally {
            assert xmlStreamWriter != null;
            xmlStreamWriter.close();
        }
    }
}
