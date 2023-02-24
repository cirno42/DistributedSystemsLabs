package ru.nsu.nikolotov;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import ru.nsu.nikolotov.osmstat.OsmStatCollector;
import ru.nsu.nikolotov.printer.OsmStatPrinter;
import ru.nsu.nikolotov.unpacker.BZ2Unpacker;

import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.nio.file.Path;

@Slf4j
public class Main {

    public static final String FILE_PATH_OPTION = "f";

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption(FILE_PATH_OPTION, true, "Path to the OSM file");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (!cmd.hasOption(FILE_PATH_OPTION)) {
                log.error("No OSM file was specified");
                return;
            }

            String filename = cmd.getOptionValue(FILE_PATH_OPTION);
            Path path = Path.of(filename);
            try (InputStream in = BZ2Unpacker.unpackArchive(path)){
                OsmStatCollector osmStatCollector = new OsmStatCollector(in);
                var stats = osmStatCollector.collectStats();
                OsmStatPrinter statPrinter = new OsmStatPrinter(stats,
                        userStat -> log.info("User: " + userStat.getKey() + " changes: " + userStat.getValue()),
                        tagsPerKey -> log.info("Tag: " + tagsPerKey.getKey() + " nodes count: " + tagsPerKey.getValue()));

                statPrinter.printStats();
                log.info("Total tags amount: " + stats.getTagCountPerKey().size());
            } catch (RuntimeException | XMLStreamException e) {
                log.error("Error while reading file: ", e);
            } catch (IOException e) {
                log.error("Error while closing file: ", e);
            }
        } catch (ParseException e) {
            log.error("Error while parsing user input: ", e);
        }

    }
}