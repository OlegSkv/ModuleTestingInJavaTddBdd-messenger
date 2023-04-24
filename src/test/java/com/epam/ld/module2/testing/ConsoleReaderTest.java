package com.epam.ld.module2.testing;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleReaderTest {

    @Test
    void getPlaceholdersByValuesShouldReadDataFromConsole() {
        PrintStream out = System.out;
        ByteArrayInputStream in =
                new ByteArrayInputStream("firstPlaceholder\nfirstValue\nn".getBytes(StandardCharsets.ISO_8859_1));
        ConsoleReader consoleReader = new ConsoleReader(in, out);

        Map<String, String> placeholdersByValues = consoleReader.getPlaceholdersByValues();

        assertTrue(placeholdersByValues.containsKey("#{firstPlaceholder}"));
        assertEquals(placeholdersByValues.get("#{firstPlaceholder}"), "firstValue");
    }
}