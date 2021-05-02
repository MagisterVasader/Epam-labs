package com.lab3.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * A class that describes the validator of an XML file that extends from DefaultHandler.
 *
 * @author Nikita Pozniak
 * @version 1.0 02.03.2021
 */
public class PlaneXMLValidator extends DefaultHandler {
    public static final Logger LOGGER = LogManager.getLogger(PlaneXMLValidator.class);
    public static final String SCHEMA_FILE_NAME = "src/main/resources/planes.xsd";

    private final StringBuilder error = new StringBuilder();
    private final String filename;

    /**
     * Constructor of the validator class.
     * @param filename a string containing the file name.
     */
    public PlaneXMLValidator(String filename) {
        this.filename = filename;
    }

    /**
     * Method that generates a string with information about the error.
     *
     * @param e exception information about which we want to view.
     * @return string representation of localized error information.
     */
    private StringBuilder getErrorInfo(SAXParseException e) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[').append(e.getLineNumber()).append(':').append(e.getColumnNumber()).append(']');
        stringBuilder.append('\n').append(e.getLocalizedMessage()).append('\n');
        return stringBuilder;
    }

    @Override
    public void warning(SAXParseException e) {
        error.append("Warning: ");
        error.append(getErrorInfo(e));
    }

    @Override
    public void error(SAXParseException e) {
        error.append("Error: ");
        error.append(getErrorInfo(e));
    }

    @Override
    public void fatalError(SAXParseException e) {
        error.append("Fatal error: ");
        error.append(getErrorInfo(e));
    }

    /**
     * @return string with errors, if any.
     */
    public String getError() {
        return (error.length() > 0) ? error.toString() : null;
    }

    /**
     * A method that checks whether a given file matches a given XSD schema.
     * @return true if the file has passed validation, otherwise false.
     */
    public boolean validate() throws IOException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(new File(SCHEMA_FILE_NAME));
            Validator validator = schema.newValidator();
            validator.setErrorHandler(this);
            validator.validate(new StreamSource(filename));
            return getError() == null;
        } catch (SAXException e) {
            LOGGER.error(e.getLocalizedMessage());
            return false;
        }
    }
}
