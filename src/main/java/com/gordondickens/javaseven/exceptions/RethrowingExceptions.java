package com.gordondickens.javaseven.exceptions;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;

public class RethrowingExceptions {

    public static void main(String[] args) {
        try {
            deleteFile(Paths.get(new URI("file:///tmp.txt")));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }

    }

    private static void deleteFile(Path path) throws NoSuchFileException, DirectoryNotEmptyException {
        try {
            Files.delete(path);
        } catch (IOException ex) {
            if (path.toFile().isDirectory()) {
                throw new DirectoryNotEmptyException(null);
            } else {
                throw new NoSuchFileException(null);
            }
        }
    }
}
