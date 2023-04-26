package com.epam.ld.module2.testing.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FilePrinterTest {

    @Test
    void print() {
        String filePath =  "src/test/resources/output.txt";
        MessagePrinter filePrinter = new FilePrinter(filePath);
        filePrinter.print("user1@user", "message content" );

        String fileContent = getFileContent(filePath);

        Path path = Paths.get(filePath);

        assertTrue(Files.exists(path));
        assertTrue(fileContent.contains("user1@user"));
        assertTrue(fileContent.contains("message content"));
    }

    private String getFileContent(String filePath) {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.collect(Collectors.joining());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
