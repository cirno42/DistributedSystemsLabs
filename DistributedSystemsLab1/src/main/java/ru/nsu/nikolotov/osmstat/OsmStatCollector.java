package ru.nsu.nikolotov.osmstat;

import ru.nsu.nikolotov.entities.OsmNode;
import ru.nsu.nikolotov.osmprocessor.OsmStreamProcessor;

import javax.xml.stream.XMLStreamException;
import java.io.InputStream;

public class OsmStatCollector {

    private final OsmStreamProcessor osmStreamProcessor;

    public OsmStatCollector(InputStream osmInputStream) throws XMLStreamException {
        this.osmStreamProcessor = new OsmStreamProcessor(osmInputStream);
    }

    public OsmStats collectStats() throws XMLStreamException {
        OsmStats stats = new OsmStats();
        while (osmStreamProcessor.hasNextElement()) {
            OsmNode node = osmStreamProcessor.readNode();
            if (node != null) {
                stats.incrementUserChanges(node.getUser());
                node.getTags().forEach(tag -> stats.incrementTagCount(tag.getKey()));
            }
        }
        return stats;
    }
}
