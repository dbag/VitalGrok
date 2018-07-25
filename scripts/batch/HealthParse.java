import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

class HealthParse {

    public static void main(String[] args) {
        System.out.println("Beginning parse...");

        parseXMLFile("data/export.xml");

        System.out.println("Complete.");
    }

    private static void parseXMLFile(String fileName) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        long counter = 0L;

        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));

            while(xmlEventReader.hasNext() && counter < 10) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                String elementName = null;

                switch(xmlEvent.getEventType()) {

                    case XMLEvent.START_ELEMENT:
                        StartElement startElement = xmlEvent.asStartElement();
                        elementName = startElement.getName().getLocalPart();
                        break;

                    case XMLEvent.END_ELEMENT:
                        EndElement endElement = xmlEvent.asEndElement();
                        elementName = endElement.getName().getLocalPart();
                        break;
                }

                System.out.println(String.format("Element name: %s", elementName));

                counter++;
            }

        }  catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

}