package ru.nsu.nikolotov.unpacker;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class BZ2Unpacker {

    public static InputStream unpackArchive(Path archivePath) {
        try {
            InputStream archiveInputStream = new BufferedInputStream(Files.newInputStream(archivePath));
            return new CompressorStreamFactory().createCompressorInputStream(archiveInputStream);
        } catch (IOException | CompressorException e) {
            log.error("Error while unpacking file: ", e);
            throw new RuntimeException(e);
        }
    }
}
