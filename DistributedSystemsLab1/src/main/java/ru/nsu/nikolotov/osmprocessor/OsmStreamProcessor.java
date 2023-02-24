package ru.nsu.nikolotov.osmprocessor;

import ru.nsu.nikolotov.entities.OsmNode;
import ru.nsu.nikolotov.entities.OsmNodeTag;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

public class OsmStreamProcessor {

    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();

    private final XMLStreamReader reader;

    public OsmStreamProcessor(InputStream is) throws XMLStreamException {
        this.reader = FACTORY.createXMLStreamReader(is);
    }

    public boolean hasNextElement() throws XMLStreamException {
        return reader.hasNext();
    }

    public OsmNode readNode() throws XMLStreamException {
        while (reader.hasNext()) {
            if (reader.isStartElement() && "node".equals(reader.getLocalName())) {
                break;
            }
            reader.next();

        }
        return parseNode();
    }

    private OsmNode parseNode() throws XMLStreamException {
        if (reader.getEventType() != XMLStreamConstants.START_ELEMENT || !"node".equals(reader.getLocalName())) {
            return null;
        }
        OsmNode node = new OsmNode();
        for (int i = 0; i < reader.getAttributeCount(); i++) {
            String name = reader.getAttributeName(i).getLocalPart();
            String value = reader.getAttributeValue(i);
            switch (name) {
                case "id" -> node.setId(Long.parseLong(value));
                case "user" -> node.setUser(value);
            }
        }
        readTags(node);
        return node;
    }

    private void readTags(OsmNode node) throws XMLStreamException {
        reader.next();
        while (reader.hasNext()) {
            if (reader.isStartElement()) {
                if (!"tag".equals(reader.getLocalName())) {
                    break;
                }
                OsmNodeTag tag = new OsmNodeTag(reader.getAttributeValue(0), reader.getAttributeValue(1));
                node.getTags().add(tag);
            }
            reader.next();
        }
    }

}

