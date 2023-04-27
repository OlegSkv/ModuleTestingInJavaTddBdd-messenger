package com.epam.ld.module2.testing.io;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @Test
    void getPlaceholdersToValuesShouldReadDataFromFile() {
        FileReader fileReader = new FileReader("src/test/resources/pholdersToVal.txt");
        Map<String, String> placeholdersToValues = fileReader.getPlaceholdersToValues();

        assertEquals(2, placeholdersToValues.size());
        assertEquals("value for the first placeholder", placeholdersToValues.get("#{first}"));
        assertEquals("second value", placeholdersToValues.get("#{second}"));
    }
}