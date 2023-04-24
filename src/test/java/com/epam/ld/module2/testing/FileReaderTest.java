package com.epam.ld.module2.testing;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @Test
    void getPlaceholdersToValuesShouldReadDataFromFile() {
        FileReader fileReader = new FileReader("/pholdersToVal.txt");
        Map<String, String> placeholdersToValues = fileReader.getPlaceholdersToValues();

        assertEquals(placeholdersToValues.get("#{first}"), "value for the first placeholder");
        assertEquals(placeholdersToValues.get("#{second}"), "second value");
    }
}